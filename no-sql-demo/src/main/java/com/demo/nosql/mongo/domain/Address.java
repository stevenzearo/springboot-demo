package com.demo.nosql.mongo.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address {
    public String name;

    public String street;

    public String city;

    public String state;

    @Field(name = "zip_code")
    public String zipCode;

    @GeoSpatialIndexed(name = "location", type = GeoSpatialIndexType.GEO_2DSPHERE)
    public GeoJsonPoint location;
}
