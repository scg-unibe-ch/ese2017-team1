package hello.Controllers;

import hello.Client.Client;
import hello.Driver.Driver;
import hello.Repositories.ClientRepository;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.DriverRepository;
import hello.Repositories.ProductOrderRepository;
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
    private DriverRepository driverRepository;
    private Driver driver;
    @Autowired
    private ClientRepository clientRepository;
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
    public String productSubmit(@ModelAttribute("productOrder") ProductOrder productOrder, @RequestParam("id") Long id) {
        Client client = this.clientRepository.findOne(id);
        productOrder.setClient(client);
        this.productOrderRepository.save(productOrder);
        return "addedProduct";
    }

/**    @GetMapping("/jobToDriver")
    public String product(Model model) {
        model.addAttribute("productOrder", new ProductOrder());
        return "jobToDriver";
    }*/

    @RequestMapping("/jobToDriver")
    public String listDriver(@ModelAttribute("driver") Driver driver, Model model) {
        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);

        return "jobToDriver";
    }

    @GetMapping("/assignedJob")
    public String driver(Model model) {
        model.addAttribute("driver", new Driver());
        return "assignedJob";
    }

}
