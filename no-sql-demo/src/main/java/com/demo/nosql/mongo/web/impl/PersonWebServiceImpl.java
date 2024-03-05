package com.demo.nosql.mongo.web.impl;

import com.demo.nosql.mongo.domain.Address;
import com.demo.nosql.mongo.domain.Location;
import com.demo.nosql.mongo.web.PersonWebService;
import com.demo.nosql.mongo.web.person.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.nosql.mongo.domain.Person;
import com.demo.nosql.mongo.service.PersonService;

/**
 * @author steve
 */
@Component
public class PersonWebServiceImpl implements PersonWebService {

    @Autowired
    PersonService service;

    public CreatePersonResponse create(CreatePersonRequest request) {
        Person person = new Person();
        person.id = new ObjectId();
        person.name = request.name;
        person.birthday = request.birthday;
        person.email = request.email;

        if (request.address != null) {
            Location location = new Location();
            location.latitude = request.address.location.latitude;
            location.longitude = request.address.location.longitude;

            Address address = new Address();
            address.city = request.address.city;
            address.state = request.address.state;
            address.street = request.address.street;
            address.zipCode = request.address.zipCode;
            address.name = request.address.name;
            address.location = location;

            person.address = address;
        }
        Person createdPerson = service.create(person);
        String id = createdPerson.id.toString();
        CreatePersonResponse response = new CreatePersonResponse();
        response.id = id;
        return response;
    }

    public Person get(String id) {
        return service.get(id);
    }

    public void update(String id, UpdatePersonRequest request) {
        service.update(id, request);
    }

    public SearchPersonResponse search(SearchPersonRequest request) {
        return service.search(request);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
