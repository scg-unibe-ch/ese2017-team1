package hello.Services;

import hello.Repositories.VehicleRepository;
import hello.Trucks.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests functionality of VehicleService class.
 * We do not test the methods listAllVehicles(), findVehicle() and save() since they only depend on the
 * VehicleRepository to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    ArrayList<Vehicle> allVehicles;
    ArrayList<Vehicle> freeVehicles;
    ArrayList<Vehicle> usedVehicles;

    @TestConfiguration
    static class VehicleServiceTestContextConfiguration {
        @Bean
        public VehicleService vehicleService() {
            return new VehicleService();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;


    @Before
    public void setup(){
        freeVehicles = new ArrayList<>();
        usedVehicles = new ArrayList<>();
        allVehicles = new ArrayList<>();

        Vehicle veh1 = new Vehicle();
        veh1.setFree(1);
        vehicleRepository.save(veh1);
        freeVehicles.add(veh1);
        allVehicles.add(veh1);

        Vehicle veh2 = new Vehicle();
        veh2.setFree(0);
        vehicleRepository.save(veh2);
        usedVehicles.add(veh2);
        allVehicles.add(veh2);

        Mockito.when(vehicleRepository.findAll()).thenReturn(allVehicles);
    }


    @Test
    public void listTrailers(){
        assertThat(vehicleService.listVehicles()).isEqualTo(freeVehicles);
    }

    @Test
    public void listUsedTrailers(){
        assertThat(vehicleService.listUsedVehicles()).isEqualTo(usedVehicles);
    }

}