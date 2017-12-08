package hello.ProductOrder;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;


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


        ProductOrder user = this.repository.findOne(id);
        assertThat(productOrder.getClient()).isEqualTo(client);
    }

}
