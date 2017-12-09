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
    ArrayList<String> addresses;
    Long tourId, id;
    ProductOrder productOrderNoTour, productOrder, productOrderAcc;
    Client client, clientA;
    Long productId;

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
        addresses = new ArrayList<>();

        Long idAcc = Long.valueOf(1);
        Long idNotAcc = Long.valueOf(2);
        id = Long.valueOf(3);
        productId = Long.valueOf(20);

        tourId = Long.valueOf(4);
        Long anotherTourId = Long.valueOf(20);
        Tour tour = new Tour();
        tour.setId(tourId);
        Tour anotherTour = new Tour();
        anotherTour.setId(anotherTourId);

        client = new Client();
        client.setId(id);
        client.setStreet("ExWi 21");
        client.setLand("CH");
        client.setCity("Bern");

        clientA = new Client();
        clientA.setId(id);
        clientA.setStreet("Musterstrasse 13");
        clientA.setLand("Frankreich");
        clientA.setCity("Paris");

        // accepted ProductOrder with Tour
        productOrderAcc = new ProductOrder();
        productOrderAcc.setId(idAcc);
        productOrderAcc.setTour(tour);
        productOrderAcc.setClient(client);
        productOrderAcc.setAccOrRej("akzeptiert");
        productOrderAcc.getTour().setId(tourId);
        products.add(productOrderAcc);

        // rejected ProductOrder with Tour
        ProductOrder productOrderNotAcc = new ProductOrder();
        productOrderNotAcc.setId(idNotAcc);
        productOrderNotAcc.setTour(tour);
        productOrderNotAcc.setClient(clientA);
        productOrderNotAcc.setAccOrRej("abgelehnt");
        productOrderNotAcc.getTour().setId(tourId);
        products.add(productOrderNotAcc);

        // not yet accepted or rejected ProductOrder with tour
        productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setTour(anotherTour);
        productOrder.setAccOrRej("keine Angabe");
        productOrder.getTour().setId(anotherTourId);
        products.add(productOrder);

        // not yet accepted or rejected ProductOrder without tour, id, product and client
        productOrderNoTour = new ProductOrder();
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

        // address of clients in Tour with tourID 4
        addresses.add("ExWi 21,Bern,CH");
        addresses.add("Musterstrasse 13,Paris,Frankreich");

        // mocking functionality of ProductOrderRepository and ClientService
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

        Mockito.when(productService.findProduct(productId))
                .thenReturn(product);
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
        productOrderService.assignProduct(productOrderNoTour, productId);
        assertThat(productOrderNoTour.getProduct()).isEqualTo(product);
    }

    @Test
    public void addresses() throws Exception{
        assertThat(productOrderService.addresses(tourId)).isEqualTo(addresses);
    }

    @Test
    public void accOrRej() throws Exception{
        productOrderService.accOrRej(id, "akzeptiert");
        assertThat(productOrder.getAccOrRej()).isEqualTo("akzeptiert");
    }
}


