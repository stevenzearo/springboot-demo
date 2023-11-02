package steve.springboot.mybatisjpa.dao.mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import steve.springboot.mybatisjpa.MybatisJpaApplicationTests;
import steve.springboot.mybatisjpa.domain.User;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:36
 * Version  1.0
 */
public class UserDaoTest extends MybatisJpaApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void getTest() {
        User user = userDao.get(1);
        System.out.println(user);
    }
}
