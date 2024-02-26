package com.demo.jpa.api;

import com.demo.jpa.api.identity.GetIdentityResponse;
import com.demo.jpa.service.IdentityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdentityWebService {
    @Resource
    IdentityService identityService;

    @GetMapping("/api/identity/{id}")
    public GetIdentityResponse get(@PathVariable("id") String id) {
        return identityService.get(id);
    }
}
