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
}


