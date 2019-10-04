package steve.springboot.mybatisjpa.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steve.springboot.mybatisjpa.domain.User;

/**
 * Author  ZLH
 * Date  2019/10/4
 * Time  15:42
 * Version  1.0
 */
public interface UserDaoJpa extends JpaRepository<User, Integer> {
    User getUserById(Integer id);
}
