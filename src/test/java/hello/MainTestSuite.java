package hello;
import hello.Controllers.*;
import hello.ProductOrder.ProductOrderTest;
import hello.Services.SomeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        ClientRequestTest.class,
        DriverRequestTest.class,
        LoginRequestTest.class,
        LogisticianRequestTest.class,
        SmokeTest.class,
        ProductOrderTest.class,
        ClientRequestTest.class,
        SomeTest.class,
        ApplicationTest.class,


})


public class MainTestSuite {
}