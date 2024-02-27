package com.demo.mybatis.api;

import com.demo.mybatis.api.person.GetPersonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface PersonWebService {

    @GetMapping("/api/person/{id}")
    GetPersonResponse get(@PathVariable("id") String id);
}
