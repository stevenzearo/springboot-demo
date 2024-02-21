package com.demo.mybatis.domain;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:20
 * Version  1.0
 */

public class Identity {
    public String id;

    public String name;

    public Integer age;

    public String createdBy;

    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "Identity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", createdBy='" + createdBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
