package hello.Services;

import hello.Application;
import hello.ProductOrders.ProductOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductOrderServiceTest {


    @Autowired
    private ProductOrderService productOrderService;

    @Test
    public void listAccProductOrders() throws Exception {

        ArrayList<ProductOrder> products = new ArrayList<>();

        ProductOrder productOrder = new ProductOrder();




    }
}


