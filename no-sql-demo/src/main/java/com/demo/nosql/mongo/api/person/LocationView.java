package com.demo.nosql.mongo.web.person;

public class LocationView {

    public Double longitude;

    public Double latitude;

    public LocationView() {
    }

    public LocationView(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}