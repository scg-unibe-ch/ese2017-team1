package hello.Services;

import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
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

/**
 * Tests functionality of TourService class.
 * We do not test the methods save() and deleteTour() since they only depend on the TourRepository
 * to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class TourServiceTest {

    ArrayList<Tour> allTours;
    ArrayList<Tour> tours;
    ArrayList<Tour> finishedTours;
    ArrayList<Tour> incompTours;
    ArrayList<ProductOrder> products;
    Long tourId;
    Long driverId;
    Tour tour;
    Long vehId;
    Vehicle veh;
    Long traiId;
    Trailer trai;
    Long prodId;
    ProductOrder prod;

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
        products = new ArrayList<>();

        driverId = (long) 8;

        vehId = (long) 1;
        veh = new Vehicle();
        veh.setId(vehId);
        veh.setFree(3);
        veh.setPalettesAmount(0);
        vehicleService.save(veh);

        traiId = (long) 1;
        trai = new Trailer();
        trai.setId(traiId);
        trai.setFree(3);
        trai.setPalettesAmount(30);
        trailerService.save(trai);

        tourId = (long) 5;
        tour = new Tour();
        tour.setId(tourId);

        prodId = (long) 7;
        prod = new ProductOrder();
        prod.setId(prodId);
        prod.setTour(null);
        prod.setAmount("4");
        Product product = new Product();
        product.setPalettes(3);
        prod.setProduct(product);

        // not finished tour
        Tour tour1 = new Tour();
        tour1.setId((long) 1);
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
        Mockito.when(tourRepository.findOne(tourId)).thenReturn(tour);
        Mockito.when(vehicle.getPalettesAmount()).thenReturn(1);
        Mockito.when(trailer.getPalettesAmount()).thenReturn(26);
        Mockito.when(vehicleService.findVehicle(vehId)).thenReturn(veh);
        Mockito.when(trailerService.findTrailer(traiId)).thenReturn(trai);
        Mockito.when(productOrderService.findProductOrder(prodId)).thenReturn(prod);
        Mockito.when(productOrderService.listTourProductOrders(tourId)).thenReturn(products);
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

    @Test
    public void findTour() {
        assertThat(tourService.findTour(tourId)).isEqualTo(tour);
    }

    @Test
    public void freePalettes(){
        assertThat(tour.getFreePalettes()).isEqualTo(null);
        tour.setTrailer(trailer);
        tour.setVehicle(vehicle);
        tourService.freePalettes(tour);
        assertThat(tour.getFreePalettes()).isEqualTo(27);
    }

    @Test
    public void assignVehicle() {
        tourService.assignVehicle(tourId,vehId);
        assertThat(tour.getVehicle().getId()).isEqualTo(vehId);
        assertThat(veh.getFree()).isEqualTo(2);
        assertThat(tour.getFreePalettes()).isEqualTo(veh.getPalettesAmount());
    }

    @Test
    public void assignTrailer() {
        tourService.assignTrailer(tourId,traiId);
        assertThat(tour.getTrailer().getId()).isEqualTo(traiId);
        assertThat(trai.getFree()).isEqualTo(2);
        assertThat(tour.getFreePalettes()).isEqualTo(trai.getPalettesAmount());
    }

    @Test
    public void addProduct(){
        tour.setFreePalettes(11);
        tourService.addProduct(tourId,prodId,(long) 1);
        assertThat(prod.getTour()).isEqualTo(null);
        assertThat(tour.getFreePalettes()).isEqualTo(11);
        tour.setFreePalettes(12);
        tourService.addProduct(tourId,prodId,(long) 1);
        assertThat(prod.getTour()).isEqualTo(tour);
        assertThat(tour.getFreePalettes()).isEqualTo(0);
        tourService.addProduct(tourId,prodId,(long) -1);
        assertThat(prod.getTour()).isEqualTo(null);
        assertThat(tour.getFreePalettes()).isEqualTo(12);
    }

    @Test
    public void cleanTour(){
        tour.setVehicle(veh);
        tour.setTrailer(trai);
        prod.setTour(tour);
        products.add(prod);
        tourService.cleanTour(tourId);
        assertThat(prod.getTour()).isEqualTo(null);
        assertThat(trai.getFree()).isEqualTo(4);
        assertThat(veh.getFree()).isEqualTo(4);
    }

    @Test
    public void finishTour(){
        tour.setVehicle(veh);
        tour.setTrailer(trai);
        prod.setTour(tour);
        products.add(prod);
        tourService.finishTour(tourId);
        assertThat(tour.getFinished()).isEqualTo(1);
    }
}


