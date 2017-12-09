package hello.Services;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Tour.Tour;
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
 * Tests functionality of ProductOrderService class.
 * We do not test the methods findProductOrder(), save(), delete() since they only depend on the ProductOrderRepository
 * to work and do not have a unique complex logic.
 */
@RunWith(SpringRunner.class)
public class ProductOrderServiceTest {

    ArrayList<ProductOrder> accProducts;
    ArrayList<ProductOrder> products;
    ArrayList<ProductOrder> notAccProducts;
    ArrayList<ProductOrder> notAccNoTourProducts;
    ArrayList<ProductOrder> tourProductOrders;
    Long tourId;
    ProductOrder productOrderNoTour;
    Client client;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public ProductOrderService productOrderService() {
            return new ProductOrderService();
        }
    }

    @Autowired
    private ProductOrderService productOrderService;

    @MockBean
    private ProductOrderRepository productOrderRepository;

    @MockBean
    private TourService tourService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ProductService productService;

    @MockBean
    private Product product;


    @Before
    public void setup(){
        products = new ArrayList<>();
        accProducts = new ArrayList<>();
        notAccProducts = new ArrayList<>();
        notAccNoTourProducts = new ArrayList<>();
        tourProductOrders = new ArrayList<>();

        Long idAcc = Long.valueOf(1);
        Long idNotAcc = Long.valueOf(2);
        Long id = Long.valueOf(3);

        tourId = Long.valueOf(4);
        Long anotherTourId = Long.valueOf(20);
        Tour tour = new Tour();
        tour.setId(tourId);
        Tour anotherTour = new Tour();
        anotherTour.setId(anotherTourId);

        client = new Client();
        client.setId(id);

        // accepted ProductOrder with Tour
        ProductOrder productOrderAcc = new ProductOrder();
        productOrderAcc.setId(idAcc);
        productOrderAcc.setClient(client);
        productOrderAcc.setTour(tour);
        productOrderAcc.setProduct(product);
        productOrderAcc.setAccOrRej("akzeptiert");
        productOrderAcc.getTour().setId(tourId);
        products.add(productOrderAcc);

        // rejected ProductOrder with Tour
        ProductOrder productOrderNotAcc = new ProductOrder();
        productOrderNotAcc.setId(idNotAcc);
        productOrderNotAcc.setTour(tour);
        productOrderNotAcc.setClient(client);
        productOrderNotAcc.setProduct(product);
        productOrderNotAcc.setAccOrRej("abgelehnt");
        productOrderNotAcc.getTour().setId(tourId);
        products.add(productOrderNotAcc);

        // not yet accepted or rejected ProductOrder with tour
        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setTour(anotherTour);
        productOrder.setProduct(product);
        productOrder.setAccOrRej("keine Angabe");
        productOrder.getTour().setId(anotherTourId);
        products.add(productOrder);

        // not yet accepted or rejected ProductOrder without tour, id and client
        productOrderNoTour = new ProductOrder();
        productOrderNoTour.setProduct(product);
        productOrderNoTour.setAccOrRej("keine Angabe");
        products.add(productOrderNoTour);

        // accepted ProductOrders
        accProducts.add(productOrderAcc);

        // not accepted ProductOrders
        notAccProducts.add(productOrderNotAcc);
        notAccProducts.add(productOrder);
        notAccProducts.add(productOrderNoTour);

        // not accepted ProductOrders with no Tour
        notAccNoTourProducts.add(productOrderNoTour);

        // ProductOrders in Tour with tourID 4
        tourProductOrders.add(productOrderAcc);
        tourProductOrders.add(productOrderNotAcc);

        // mocking functionality of ProductOrderRepository
        Mockito.when(productOrderRepository.findAll())
                .thenReturn(products);

        Mockito.when(tourService.findTour(tourId))
                .thenReturn(tour);

        Mockito.when(productOrderRepository.findOne(idAcc))
                .thenReturn(productOrderAcc);

        Mockito.when(productOrderRepository.findOne(idNotAcc))
                .thenReturn(productOrderNotAcc);

        Mockito.when(productOrderRepository.findOne(id))
                .thenReturn(productOrder);

        Mockito.when(clientService.findClient(id))
                .thenReturn(client);
    }


    @Test
    public void listAllProductOrders() throws Exception {
        assertThat(productOrderService.listAllProductOrders()).isEqualTo(products);
    }

    @Test
    public void listAccProductOrders() throws Exception {
        assertThat(productOrderService.listAccProductOrders()).isEqualTo(accProducts);
    }

    @Test
    public void listNotAccProductOrders() throws Exception {
        assertThat(productOrderService.listNotAccProductOrders()).isEqualTo(notAccProducts);
    }

    @Test
    public void listNotAccNoTourProductOrders() throws Exception {
        assertThat(productOrderService.listNotAccNoTourProductOrders()).isEqualTo(notAccNoTourProducts);
    }

    @Test
    public void listTourProductOrders() throws Exception {
        assertThat(productOrderService.listTourProductOrders(tourId)).isEqualTo(tourProductOrders);
    }

    @Test
    public void checkId() throws Exception{
        assertThat(productOrderService.checkId(productOrderNoTour).getId()).isEqualTo(4);
    }

    @Test
    public void assignClient() throws Exception{
        productOrderService.assignClient(productOrderNoTour, client.getId());
        assertThat(productOrderNoTour.getClient()).isEqualTo(client);
    }

    @Test
    public void assignProduct() throws Exception{

    }

    @Test
    public void addresses() throws Exception{

    }

    @Test
    public void accOrRej() throws Exception{

    }

}


