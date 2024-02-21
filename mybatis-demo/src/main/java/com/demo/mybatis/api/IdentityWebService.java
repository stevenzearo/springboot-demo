package com.demo.mybatis.api;

import com.demo.mybatis.api.identity.GetIdentityResponse;
import com.demo.mybatis.domain.Identity;
import com.demo.mybatis.service.IdentityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IdentityWebService {
    @Resource
    IdentityService identityService;

    @GetMapping("/api/identity/{id}")
    public GetIdentityResponse get(@PathVariable("id") String id) {
        return identityService.get(id);
    }
}
