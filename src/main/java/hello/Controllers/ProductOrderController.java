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

import java.util.ArrayList;

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

        Iterable<ProductOrder> allProductOrders = this.productOrderRepository.findAll();
        ArrayList<ProductOrder> products = new ArrayList<>();
        ArrayList<ProductOrder> accProducts = new ArrayList<>();
        for(ProductOrder product : allProductOrders){
            if(product.getAccOrRej().equalsIgnoreCase("akzeptiert")){
                accProducts.add(product);
            }
            else{
                products.add(product);
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("accProducts", accProducts);
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

    @RequestMapping("/deleteJobCheck/{prodId}")
    public String checkDeleteJob(@ModelAttribute("productOrder") ProductOrder productOrder, @PathVariable("prodId") Long prodId, Model model) {
        ProductOrder prod = productOrderRepository.findOne(prodId);
        model.addAttribute("prod", prod);
        return "deleteJobCheck";
    }

    @RequestMapping("/deleteJob/{prodId}")
    public String deleteJob(@ModelAttribute("productOrder") ProductOrder productOrder, @PathVariable("prodId") Long prodId, Model model) {
        ProductOrder prod = productOrderRepository.findOne(prodId);
        this.productOrderRepository.delete(prod);
        model.addAttribute("prod", prod);
        return "deleteJob";
    }

}
