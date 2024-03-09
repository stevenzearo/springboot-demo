package com.demo.nosql.mongo.api.impl;

import com.demo.nosql.mongo.domain.Address;
import com.demo.nosql.mongo.domain.Location;
import com.demo.nosql.mongo.api.PersonWebService;
import com.demo.nosql.mongo.api.person.CreatePersonRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.nosql.mongo.domain.Person;
import com.demo.nosql.mongo.service.PersonService;

import java.util.List;

/**
 * @author steve
 */
@Component
public class PersonWebServiceImpl implements PersonWebService {

    @Autowired
    PersonService service;

    public Person create(CreatePersonRequest request) {
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
        return service.create(person);
    }

    public Person get(String id) {
        return service.get(id);
    }

    public void update(String id, Person person) {
        person.id = new ObjectId(id);
        service.update(person);
    }

    public List<Person> search(String name) {
        return service.searchByName(name);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
