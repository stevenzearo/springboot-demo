package com.demo.mybatis.api.impl;

import com.demo.mybatis.api.PersonWebService;
import com.demo.mybatis.api.person.GetPersonResponse;
import com.demo.mybatis.service.PersonService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonWebServiceImpl implements PersonWebService {
    @Resource
    PersonService personService;

    public GetPersonResponse get(String id) {
        return personService.get(id);
    }
}
