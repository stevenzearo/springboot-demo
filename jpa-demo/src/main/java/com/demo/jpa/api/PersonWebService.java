package com.demo.jpa.api;

import com.demo.jpa.api.person.GetPersonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface PersonWebService {

    @GetMapping("/api/identity/{id}")
    GetPersonResponse get(@PathVariable("id") String id);
}
