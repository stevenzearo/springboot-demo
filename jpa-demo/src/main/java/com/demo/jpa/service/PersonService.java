package com.demo.jpa.service;

import com.demo.jpa.api.person.GetPersonResponse;
import com.demo.jpa.domain.Person;
import com.demo.jpa.dao.PersonRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {
    @Resource
    PersonRepository personRepository;

    public GetPersonResponse get(String id) {
        Person person = personRepository.getById(id);
        GetPersonResponse response = new GetPersonResponse();
        if (person == null) {
            return response;
        }
        response.id = person.id;
        response.name = person.name;
        if (person.birthday != null) {
            response.age = ZonedDateTime.now().getYear() - person.birthday.getYear();
        }
        response.createdBy = person.createdBy;
        response.createdTime = person.createdTime;
        return response;
    }
}
