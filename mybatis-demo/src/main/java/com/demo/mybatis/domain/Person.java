package com.demo.mybatis.domain;

import java.time.ZonedDateTime;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:20
 * Version  1.0
 */

public class Person {
    public String id;

    public String name;

    public ZonedDateTime birthday;

    public String createdBy;

    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "Person{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", birthday=" + birthday +
            ", createdBy='" + createdBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
