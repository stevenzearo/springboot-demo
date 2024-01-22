package steve.springboot.mybatisjpa.dao.jpa;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import steve.springboot.mybatisjpa.MybatisJpaApplicationTests;


/**
 * Author  ZLH
 * Date  2019/10/4
 * Time  16:14
 * Version  1.0
 */
@Disabled
public class UserDaoJpaTest extends MybatisJpaApplicationTests {
    @Autowired
    UserDaoJpa userDaoJpa;

    @Test
    @Transactional // keep session valid when method be invoked
    public void getUserById() {
        System.out.println(userDaoJpa.getUserById(1));
    }
}