package steve.springboot.nosql.mongo.spring.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author steve
 */
@RequestMapping("/test")
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        logger.warn(request.getRequestURI());
        return "hello, world!";
    }
}
