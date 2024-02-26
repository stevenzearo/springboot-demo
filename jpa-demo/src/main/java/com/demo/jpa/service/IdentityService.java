package com.demo.jpa.service;

import com.demo.jpa.api.identity.GetIdentityResponse;
import com.demo.jpa.domain.Identity;
import com.demo.jpa.dao.IdentityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {
    @Resource
    IdentityRepository identityRepository;

    public GetIdentityResponse get(String id) {
        Identity identity = identityRepository.getById(id);
        GetIdentityResponse response = new GetIdentityResponse();
        if (identity == null) {
            return response;
        }
        response.id = identity.id;
        response.age = identity.age;
        response.createdBy = identity.createdBy;
        response.createdTime = identity.createdTime;
        return response;
    }
}
