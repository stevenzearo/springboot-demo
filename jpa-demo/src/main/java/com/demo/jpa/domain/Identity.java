package com.demo.jpa.domain;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:20
 * Version  1.0
 */
@Entity
@Table(name = "identities")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "age")
    public Integer age;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
