package steve.zookeeper.app.zookeeper;

import steve.zookeeper.app.zookeeper.management.BeanFactory;
import steve.zookeeper.app.zookeeper.service.CourseService;
import steve.zookeeper.app.zookeeper.service.CourseServiceImpl;
import steve.zookeeper.app.zookeeper.service.PersonService;
import steve.zookeeper.app.zookeeper.service.PersonServiceImpl;

import java.util.Scanner;

/**
 * @author steve
 */
public class RegistryTest {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        CourseServiceImpl instance = new CourseServiceImpl();
        String canonicalName = CourseService.class.getCanonicalName();
        System.out.println(canonicalName);
        beanFactory.addBean(CourseService.class, instance);
        beanFactory.addBean(PersonService.class, new PersonServiceImpl());
        CourseService instance2 = beanFactory.getBean(CourseService.class);
        assert instance == instance2;
        String courseName = instance2.getCourseName();
        System.out.println(courseName);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        beanFactory.removeBean(CourseService.class);
        PersonService personService = beanFactory.getBean(PersonService.class);
        personService.say("hello, world!");
        String courseName1 = instance2.getCourseName();
        System.out.println(courseName1);
        scanner.nextLine();
    }
}
