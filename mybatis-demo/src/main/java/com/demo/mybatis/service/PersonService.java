package com.demo.mybatis.service;

import com.demo.mybatis.api.person.GetPersonResponse;
import com.demo.mybatis.domain.Person;
import com.demo.mybatis.mapper.PersonMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {
    @Resource
    PersonMapper personMapper;

    public GetPersonResponse get(String id) {
        Person person = personMapper.get(id);
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
