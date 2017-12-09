package hello.Services;

import hello.Application;
import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductOrderServiceTest {


    @Autowired
    private ProductOrderService productOrderService;

    @MockBean
    private ProductOrderRepository repository;

    @Before
    public void setup(){

        Client client = new Client();
        Product product = new Product();
        Long id = Long.valueOf(20);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);

        this.productOrderService.save(productOrder);
    }


    @Test
    public void listAccProductOrders() throws Exception {

        ArrayList<ProductOrder> products = new ArrayList<>();

        Client client = new Client();
        Product product = new Product();
        Long id = Long.valueOf(20);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);

        products.add(productOrder);

        assertThat(productOrderService.listAllProductOrders()).isEqualTo(products);

    }
}


