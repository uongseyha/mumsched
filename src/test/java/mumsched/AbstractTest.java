package mumsched;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The AbstractTest class is the parent of all JUnit test classes. This class
 * configures the test ApplicationContext and test runner environment.
 * 
 * @author Matt Warman
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest
public abstract class AbstractTest {

    /**
     * The Logger instance for all classes in the unit test framework.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
