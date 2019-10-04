package steve.springboot.mybatisjpa.dao.mybatis;

import org.apache.ibatis.annotations.*;
import steve.springboot.mybatisjpa.domain.User;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:25
 * Version  1.0
 */
@Mapper
public interface UserDao {
    @Select("select id, name, age from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id")
            , @Result(column = "name", property = "name")
            , @Result(column = "age", property = "age")
    })
    User get(@Param("id") Integer id);
}
