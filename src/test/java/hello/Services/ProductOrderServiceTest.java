package hello.Services;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
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
public class ProductOrderServiceTest {


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


    @Test
    public void listAllProductOrders() throws Exception {

        ArrayList<ProductOrder> products = new ArrayList<>();
        Client client = new Client();
        Product product = new Product();
        Long id = Long.valueOf(20);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);

        products.add(productOrder);

        Mockito.when(productOrderRepository.findAll())
                .thenReturn(products);

        assertThat(productOrderService.listAllProductOrders()).isEqualTo(products);
    }

    @Test
    public void listAccProductOrders() throws Exception {

        ArrayList<ProductOrder> products = new ArrayList<>();
        Client clientAcc = new Client();
        Client clientNotAcc = new Client();
        Client client = new Client();
        Product product = new Product();
        Long idAcc = Long.valueOf(1);
        Long idNotAcc = Long.valueOf(2);
        Long id = Long.valueOf(3);

        ProductOrder productOrderAcc = new ProductOrder();
        productOrderAcc.setId(idAcc);
        productOrderAcc.setClient(clientAcc);
        productOrderAcc.setProduct(product);
        productOrderAcc.setAccOrRej("akzeptiert");
        products.add(productOrderAcc);

        ProductOrder productOrderNotAcc = new ProductOrder();
        productOrderNotAcc.setId(idNotAcc);
        productOrderNotAcc.setClient(clientNotAcc);
        productOrderNotAcc.setProduct(product);
        productOrderNotAcc.setAccOrRej("abgelehnt");
        products.add(productOrderNotAcc);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);
        productOrder.setAccOrRej("keine Angabe");
        products.add(productOrder);

        Mockito.when(productOrderRepository.findAll())
                .thenReturn(products);

        ArrayList<ProductOrder> accProducts = new ArrayList<>();
        accProducts.add(productOrderAcc);

        assertThat(productOrderService.listAccProductOrders()).isEqualTo(accProducts);
    }
}


