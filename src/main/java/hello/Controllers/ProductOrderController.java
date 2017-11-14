package hello.Controllers;

import hello.Client.Client;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ClientRepository;
import hello.Repositories.ProductOrderRepository;
import hello.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class ProductOrderController extends WebMvcConfigurerAdapter {


    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;


    @RequestMapping("/showJobs")
    public String listJobs(@ModelAttribute("productOrder") ProductOrder productOrder, Model model) {

        Iterable<ProductOrder> products = this.productOrderRepository.findAll();
        model.addAttribute("products", products);
        return "showJobs";
    }

    @GetMapping("/orderForm")
    public String productForm(Model model) {
        model.addAttribute("productOrder", new ProductOrder());
        model.addAttribute("products", this.productRepository.findAll());
        return "orderForm";
    }


    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder,@RequestParam("productId") Long productId, @RequestParam("id") Long clientId) {
        Client client = this.clientRepository.findOne(clientId);
        productOrder.setClient(client);

        Product product = this.productRepository.findOne(productId);
        productOrder.setProduct(product);

        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }

}
