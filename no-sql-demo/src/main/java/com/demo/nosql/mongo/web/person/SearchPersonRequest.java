package com.demo.nosql.mongo.web.person;

public class SearchPersonRequest {
    public String name;
    public NearCondition near;


    public static class NearCondition {
        public Double maxKm;
        public Double minKm;
        public LocationView location;
    }
}
