package hello.ProductOrder;

import hello.Application;
import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests methods of the ProductOrder class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductOrderTest {

    @Test
    public void testExample() throws Exception {

        Client client = new Client();
        Product product = new Product();

        ProductOrder productOrder = new ProductOrder();
        Long id = Long.valueOf(20);

        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);

        assertThat(productOrder.getClient()).isEqualTo(client);
        assertThat(productOrder.getId()).isEqualTo(id);
        assertThat(productOrder.getProduct()).isEqualTo(product);
    }

}
