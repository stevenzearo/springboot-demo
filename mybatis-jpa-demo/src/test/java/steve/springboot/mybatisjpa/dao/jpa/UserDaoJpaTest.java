package steve.springboot.mybatisjpa.dao.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import steve.springboot.mybatisjpa.MybatisJpaApplicationTests;

import javax.transaction.Transactional;

/**
 * Author  ZLH
 * Date  2019/10/4
 * Time  16:14
 * Version  1.0
 */
public class UserDaoJpaTest extends MybatisJpaApplicationTests {
    @Autowired
    UserDaoJpa userDaoJpa;

    @Test
    @Transactional // keep session valid when method be invoked
    public void getUserById() {
        System.out.println(userDaoJpa.getUserById(1));
        System.out.println(userDaoJpa.getOne(1));
    }
}