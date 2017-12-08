package hello;
import hello.Controllers.*;
import hello.ProductOrder.ProductOrderTest;
import hello.Repositories.ClientRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests all test classes listed.
 * Commented out tests do not work yet.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({

        ClientRequestTest.class,
        DriverRequestTest.class,
        LoginRequestTest.class,
        LogisticianRequestTest.class,
        SmokeTest.class,
        //ProductOrderTest.class,
        ClientRequestTest.class,
        ClientRepositoryTest.class,
        ApplicationTest.class,


})


public class MainTestSuite {
}