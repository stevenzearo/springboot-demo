package com.demo.nosql.mongo.api.person;

import java.time.ZonedDateTime;

public class CreatePersonRequest {
    public ZonedDateTime birthday;

    public String name;

    public String email;

    public Address address;

    public static class Address {
        public String name;

        public String street;

        public String city;

        public String state;

        public String zipCode;

        public Location location;
    }


    public static class Location {

        public Double longitude;

        public Double latitude;
    }
}
