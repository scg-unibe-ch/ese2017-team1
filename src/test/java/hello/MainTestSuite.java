package hello;
import hello.Controllers.*;
import hello.ProductOrder.ProductOrderTest;
import hello.Repositories.ClientRepositoryTest;
import hello.Services.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests all test classes listed.
 *
 * We have a lot of our logic implemented in the Service classes so we test these in the folder "Services". *
 * We did not write separate tests for the classes ClientService, DriverService and ProductService since they rely mainly on the Repository classes
 * where we used the CrudRepository from which we expect that it works since we did not change any of the implemented methods.
 *
 * We did a test for the ClientRepository but as mentioned we do not expect errors from the Repository classes
 * because we rely here only on the CrudRepository which we expect to work.
 *
 * We tested some of the methods of the ProductOrder class but we did not write separate tests for the rest since these
 * are very simple getter and setter methods. The same applies to the Product class, Role class, Tour class,
 * classes in the folder "Trucks", Client class, Driver class, Logistician class, User class, Password class.
 *
 * We test whether the Controllers are initialized which means that they are not null when the Application is run (SmokeTest).
 * Additionally we test whether some of the Controller methods are valid when they are requested. But since we
 * put most of the functionalities in the Service classes (which we tested) we did not write separate tests for the rest.
 *
 * In addition we test in the ApplicationTest whether the Application is started without an error.
 *
 * Finally we also expect the WebMvnConfig to work because it has only one method that returns a Bean depending on the BCryptPasswordEncoder.
 * We also expect the SecurityConfiguration to work since our part was only to give the right access to the right pages.
 * We test in the LoginRequestTest whether the request for '/' is redirected to the login page.
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
        ApplicationTest.class,
        ProductOrderServiceTest.class,
        TourServiceTest.class,
        RoleServiceTest.class,
        TrailerServiceTest.class,
        VehicleServiceTest.class,
        UserServiceImplTest.class,


})


public class MainTestSuite {
}