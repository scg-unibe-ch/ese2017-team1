package hello.Controllers;

import hello.ProductOrders.ProductOrder;
import hello.ProductOrders.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class ProductOrderController extends WebMvcConfigurerAdapter {


    @Autowired
    private ProductOrderRepository productOrderRepository;
    private ProductOrder productOrder;


    @RequestMapping("/showJobs")
    public String listJobs(@ModelAttribute("productOrder") ProductOrder productOrder, Model model) {

        Iterable<ProductOrder> products = this.productOrderRepository.findAll();
        model.addAttribute("products", products);
        return "showJobs";
    }


    @GetMapping("/orderForm")
    public String productForm(Model model) {
        model.addAttribute("productOrder", new ProductOrder());
        return "orderForm";
    }


    @PostMapping("/orderForm")
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder) {
        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }

}
