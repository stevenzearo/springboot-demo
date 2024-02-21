package com.demo.mybatis.mapper;

import com.demo.mybatis.domain.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.time.ZonedDateTime;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:25
 * Version  1.0
 */
@Mapper
public interface IdentityMapper {
    @Select("select id, name, age, created_by, created_time from identities where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id")
            , @Result(column = "name", property = "name")
            , @Result(column = "age", property = "age")
            , @Result(column = "created_by", property = "createdBy")
            , @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP, javaType = ZonedDateTime.class)
    })
    Identity get(@Param("id") String id);
}
