package com.demo.nosql.mongo.domain;

public class Location {
    public double longitude;
    public double latitude;

    public Location() {
    }

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
