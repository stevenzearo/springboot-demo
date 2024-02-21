package com.demo.mybatis.service;

import com.demo.mybatis.api.identity.GetIdentityResponse;
import com.demo.mybatis.domain.Identity;
import com.demo.mybatis.mapper.IdentityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class IdentityService {
    @Resource
    IdentityMapper identityMapper;
    public GetIdentityResponse get(String id) {
        Identity identity = identityMapper.get(id);
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
