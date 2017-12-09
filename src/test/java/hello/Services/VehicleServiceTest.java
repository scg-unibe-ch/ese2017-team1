package hello.Services;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Corina on 09.12.2017.
 */
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    @TestConfiguration
    static class VehicleServiceTestContextConfiguration {
        @Bean
        public VehicleService vehicleService() {
            return new VehicleService();
        }
    }

    @Autowired
    private VehicleService vehicleService;



}
