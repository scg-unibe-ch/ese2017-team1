package hello.Services;

import hello.Repositories.ProductOrderRepository;
import hello.Repositories.TourRepository;
import hello.Tour.Tour;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import hello.Users.Driver.Driver;
import hello.Users.User;
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

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class TourServiceTest {

    ArrayList<Tour> allTours;
    ArrayList<Tour> tours;
    ArrayList<Tour> finishedTours;
    ArrayList<Tour> incompTours;
    Long tourId;
    Long driverId;

    @TestConfiguration
    static class TourServiceTestContextConfiguration {
        @Bean
        public TourService tourService() {
            return new TourService();
        }
    }

    @Autowired
    private TourService tourService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private TrailerService trailerService;

    @MockBean
    private TourRepository tourRepository;

    @MockBean
    private ProductOrderService productOrderService;

    @MockBean
    private User user;

    @MockBean
    private Driver driver;

    @MockBean
    private Vehicle vehicle;

    @MockBean
    private Trailer trailer;


    @Before
    public void setup() {
        allTours = new ArrayList<>();
        tours = new ArrayList<>();
        finishedTours = new ArrayList<>();
        incompTours = new ArrayList<>();

        driverId = (long) 8;

        // not finished tour
        tourId = (long) 1;
        Tour tour1 = new Tour();
        tour1.setId(tourId);
        tour1.setDriver(driver);
        tour1.setVehicle(vehicle);
        tour1.setTrailer(trailer);
        tour1.setFreePalettes(6);
        tour1.setFinished(null);
        allTours.add(tour1);
        tours.add(tour1);

        // finished tour
        Tour tour2 = new Tour();
        tour2.setId((long) 2);
        tour2.setDriver(driver);
        tour2.setVehicle(vehicle);
        tour2.setTrailer(trailer);
        tour2.setFreePalettes(14);
        tour2.setFinished(1);
        allTours.add(tour2);
        finishedTours.add(tour2);

        // incomplete tour
        Tour tour3 = new Tour();
        tour3.setId((long) 3);
        tour3.setVehicle(vehicle);
        tour3.setFinished(null);
        allTours.add(tour3);
        incompTours.add(tour3);

        Mockito.when(tourRepository.findAll()).thenReturn(allTours);
        Mockito.when(tourService.findTour(tourId)).thenReturn(tour1);
        Mockito.when(driver.getUserId()).thenReturn(driverId);
        Mockito.when(user.getId()).thenReturn(driverId);
    }


    @Test
    public void listAllTours() throws Exception {
        assertThat(tourService.listAllTours()).isEqualTo(allTours);
    }

    @Test
    public void listTours() throws Exception {
        assertThat(tourService.listTours()).isEqualTo(tours);
    }

    @Test
    public void listDriverTours() throws Exception {
        assertThat(tourService.listDriverTours(user)).isEqualTo(tours);
    }

    @Test
    public void listIncompTours() throws Exception {
        assertThat(tourService.listIncompTours()).isEqualTo(incompTours);
    }

    @Test
    public void listFinishedTours() throws Exception {
        assertThat(tourService.listFinishedTours()).isEqualTo(finishedTours);
    }
}


