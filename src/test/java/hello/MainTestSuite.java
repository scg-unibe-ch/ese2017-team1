package hello;
import hello.Controllers.*;
import hello.ProductOrder.ProductOrderTest;
import hello.Repositories.ClientRepositoryTest;
import hello.Services.ProductOrderServiceTest;
import hello.Services.RoleServiceTest;
import hello.Services.TourServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests all test classes listed.
 * Commented out tests do not work yet.
 *
 * We did not test the classes ClientService, DriverService and ProductService
 * since they rely mainly on the Repository classes which we test separately.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({

        ClientRequestTest.class,
        DriverRequestTest.class,
   //     LoginRequestTest.class,
        LogisticianRequestTest.class,
     //   SmokeTest.class,
        ProductOrderTest.class,
        ClientRequestTest.class,
        ClientRepositoryTest.class,
        ApplicationTest.class,
        ProductOrderServiceTest.class,
        TourServiceTest.class,
        RoleServiceTest.class,


})


public class MainTestSuite {
}