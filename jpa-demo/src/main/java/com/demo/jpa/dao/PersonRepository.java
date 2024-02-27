package com.demo.jpa.dao;


import com.demo.jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author  ZLH
 * Date  2019/10/4
 * Time  15:42
 * Version  1.0
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person getById(String id);
}
