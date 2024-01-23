package com.demo.jpa.dao;

import com.demo.jpa.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author  ZLH
 * Date  2019/10/4
 * Time  15:42
 * Version  1.0
 */
public interface IdentityRepository extends JpaRepository<Identity, Integer> {
    Identity getUserById(Integer id);
}
