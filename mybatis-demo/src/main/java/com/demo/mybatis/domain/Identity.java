package com.demo.mybatis.domain;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:20
 * Version  1.0
 */

public class Identity {
    public Integer id;

    public String name;

    public Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
