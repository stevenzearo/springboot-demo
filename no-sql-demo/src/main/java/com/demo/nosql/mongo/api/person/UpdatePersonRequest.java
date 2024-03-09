package com.demo.nosql.mongo.api.person;

import java.time.ZonedDateTime;

public class UpdatePersonRequest {
    public String name;

    public String email;

    public ZonedDateTime birthday;

    public AddressView address;
}
