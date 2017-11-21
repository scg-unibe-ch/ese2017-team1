package hello.Controllers;

import hello.Client.Client;
import hello.Users.Driver.Driver;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ClientRepository;
import hello.Repositories.DriverRepository;
import hello.Repositories.ProductOrderRepository;
import hello.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Controller for the orderForm and the showJobs
 */
@Controller
public class ProductOrderController extends WebMvcConfigurerAdapter {


    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DriverRepository driverRepository;


    @RequestMapping("/showJobs")
    public String listJobs(@ModelAttribute("productOrder") ProductOrder productOrder, Model model) {

        Iterable<ProductOrder> products = this.productOrderRepository.findAll();
        model.addAttribute("products", products);
        return "showJobs";
    }

    @GetMapping("/orderForm")
    public String productForm(Model model) {

        ProductOrder productOrder = new ProductOrder();

        /**
         * Sets ID of new ProductOrder equal to the highest already existing ID + 1
         * (should have done this in HTML File but it did not work)
         */
        Long i = Long.valueOf(1);
        while(this.productOrderRepository.findOne(i)!=null){

            i++;
        }
        productOrder.setId(i);

        model.addAttribute("productOrder", productOrder);
        model.addAttribute("products", this.productRepository.findAll());
        return "orderForm";
    }


    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder, @RequestParam("id") Long clientId, @RequestParam("productId") Long productId) {
        Client client = this.clientRepository.findOne(clientId);
        productOrder.setClient(client);

        Product product = this.productRepository.findOne(productId);
        productOrder.setProduct(product);

        Driver driver = this.driverRepository.findOne((long) 10);
        productOrder.setDriver(driver);

        productOrder.setAccOrRej("keine Angabe");


        /**
         * Sets ID of new ProductOrder equal to the highest already existing ID + 1
         * (should have done this in HTML File but it did not work)
         */
        Long i = Long.valueOf(1);
        while (this.productOrderRepository.findOne(i) != null && this.productOrderRepository.findOne(i) != productOrder) {

            i++;
        }
        productOrder.setId(i);

        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }

}
