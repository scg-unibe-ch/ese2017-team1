package hello;
import hello.Controllers.*;
import hello.ProductOrder.ProductOrderTest;
import hello.Repositories.ClientRepositoryTest;
import hello.Services.ClientServiceTest;
import hello.Services.ProductOrderServiceTest;
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
        ProductOrderTest.class,
        ClientRequestTest.class,
        ClientRepositoryTest.class,
        ClientServiceTest.class,
        ApplicationTest.class,
        ProductOrderServiceTest.class,


})


public class MainTestSuite {
}