package com.demo.zookeeper.registry;

import com.demo.zookeeper.service.BeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.demo.zookeeper.service.CourseService;
import com.demo.zookeeper.service.CourseServiceImpl;
import com.demo.zookeeper.service.PersonService;
import com.demo.zookeeper.service.PersonServiceImpl;

@Disabled
class RegistryTest {

    @Test
    void test() throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        CourseServiceImpl instance = new CourseServiceImpl();
        beanFactory.addBean(CourseService.class, instance);

        CourseService instance2 = beanFactory.getBean(CourseService.class);
        Assertions.assertNotEquals(instance, instance2);

        String courseName = instance2.getCourseName();
        Assertions.assertEquals("computer science", courseName);

        beanFactory.removeBean(CourseService.class);
        Assertions.assertThrows(Exception.class, instance2::getCourseName);

        beanFactory.addBean(PersonService.class, new PersonServiceImpl());
        PersonService personService = beanFactory.getBean(PersonService.class);
        Assertions.assertDoesNotThrow(() -> personService.say("hello, world!"));
    }
}