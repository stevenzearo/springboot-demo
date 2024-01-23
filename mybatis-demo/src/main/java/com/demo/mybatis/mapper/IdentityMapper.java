package com.demo.mybatis.mapper;

import com.demo.mybatis.domain.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:25
 * Version  1.0
 */
@Mapper
public interface IdentityMapper {
    @Select("select id, name, age from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id")
            , @Result(column = "name", property = "name")
            , @Result(column = "age", property = "age")
    })
    Identity get(@Param("id") Integer id);
}