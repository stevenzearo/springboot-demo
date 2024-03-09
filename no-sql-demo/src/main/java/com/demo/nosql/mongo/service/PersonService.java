package com.demo.nosql.mongo.service;

import com.demo.nosql.common.Util;
import com.demo.nosql.mongo.domain.Address;
import com.demo.nosql.mongo.domain.Person;
import com.demo.nosql.mongo.web.person.AddressView;
import com.demo.nosql.mongo.web.person.LocationView;
import com.demo.nosql.mongo.web.person.PersonView;
import com.demo.nosql.mongo.web.person.SearchPersonRequest;
import com.demo.nosql.mongo.web.person.SearchPersonResponse;
import com.demo.nosql.mongo.web.person.UpdatePersonRequest;
import jakarta.annotation.Resource;
import org.bson.types.ObjectId;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class PersonService {
    @Resource
    MongoTemplate template;

    public Person create(Person person) {
        return template.insert(person);
    }

    public Person get(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return template.findOne(query, Person.class);
    }


    public SearchPersonResponse search(SearchPersonRequest request) {

        Query query = new Query();
        if (request.name != null && !request.name.isEmpty()) {
            Criteria byName = Criteria.where("name").regex(request.name);
            query.addCriteria(byName);
        }
        if (request.near != null) {
            Point point = new Point(request.near.location.longitude, request.near.location.latitude);
            // For GeoJSON point objects, specify the distance in meters, not radians. ref: https://www.mongodb.com/docs/manual/reference/operator/query/maxDistance/
            Criteria addressNear = Criteria.where("address.location").nearSphere(point)
                                        .minDistance(Util.meterToKM(request.near.minKm))
                                        .maxDistance(Util.meterToKM(request.near.maxKm));
            query.addCriteria(addressNear);
        }
        long total = template.count(query, Person.class);
        List<Person> personList = template.find(query, Person.class);
        SearchPersonResponse response = new SearchPersonResponse();
        response.total = total;
        response.persons = personList.stream().map(this::buildPersonView).collect(Collectors.toList());
        return response;
    }

    private PersonView buildPersonView(Person p) {
        PersonView personView = new PersonView();
        personView.id = p.id.toString();
        personView.name = p.name;
        personView.email = p.email;
        if (p.birthday != null) {
            personView.age = ZonedDateTime.now().getYear() - p.birthday.getYear();
        }
        if (p.address != null) {
            AddressView address = new AddressView();
            address.name = p.address.name;
            address.street = p.address.street;
            address.city = p.address.city;
            address.state = p.address.state;
            address.zipCode = p.address.zipCode;
            if (p.address.location != null) {
                address.location = new LocationView(p.address.location.getX(), p.address.location.getY());
            }
            personView.address = address;
        }
        return personView;
    }

    public void update(String id, UpdatePersonRequest request) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        Address address = null;
        if (request.address != null) {
            address = new Address();
            address.name = request.address.name;
            address.street = request.address.street;
            address.city = request.address.city;
            address.state = request.address.state;
            address.zipCode = request.address.zipCode;
            if (request.address.location != null) {
                address.location = new GeoJsonPoint(request.address.location.longitude, request.address.location.latitude);
            }
        }

        update.set("name", request.name)
              .set("email", request.email)
              .set("address", address)
              .set("birthday", request.birthday);
        template.updateFirst(query, update, Person.class);
    }

    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        template.remove(query, Person.class);
    }
}