package com.demo.jpa.api.impl;

import com.demo.jpa.api.PersonWebService;
import com.demo.jpa.api.person.GetPersonResponse;
import com.demo.jpa.service.PersonService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonWebServiceImpl implements PersonWebService {
    @Resource
    PersonService personService;

    @Override
    public GetPersonResponse get(String id) {
        return personService.get(id);
    }
}
