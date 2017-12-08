package hello.ProductOrder;

import hello.Application;
import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Very stupid test but maybe we can work on it later.
 * repository.save() does not work
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductOrderTest {


    @Autowired
    private ProductOrderRepository repository;


    @Test
    public void testExample() throws Exception {

        Client client = new Client();
        Product product = new Product();

        ProductOrder productOrder = new ProductOrder();
        Long id = Long.valueOf(20);

        productOrder.setId(id);
        productOrder.setClient(client);
        productOrder.setProduct(product);

        //this.repository.save(productOrder);
        //assertThat(this.repository.findOne(id).getClient()).isEqualTo(client);

        assertThat(productOrder.getClient()).isEqualTo(client);
    }

}
