package com.demo.mybatis.api.identity;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class GetIdentityResponse {
    public String id;

    public String name;

    public Integer age;

    public String createdBy;

    public ZonedDateTime createdTime;
}
