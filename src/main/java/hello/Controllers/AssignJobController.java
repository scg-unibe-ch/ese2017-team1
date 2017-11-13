package hello.Controllers;

import hello.Driver.Driver;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.DriverRepository;
import hello.Repositories.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class AssignJobController extends WebMvcConfigurerAdapter {


    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private DriverRepository driverRepository;


    @RequestMapping("/jobToDriver")
    public String list(@ModelAttribute("driver") Driver driver, Model model) {

        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        return "jobToDriver";
    }

/**    @RequestMapping(value = "/jobToDriver", params = "id")
    public String product(@RequestParam Long id, Model model) {
        ProductOrder productOrder = this.productOrderRepository.findOne(id);
        model.addAttribute("productOrder", productOrder);
        return "jobToDriver";
    }**/

/**    @PostMapping("/jobToDriver")
    public String submit(@ModelAttribute("productOrder") ProductOrder productOrder, @RequestParam("id") Long id) {
    Driver driver = this.driverRepository.findOne(id);
    productOrder.setDriver(driver);
    this.productOrderRepository.save(productOrder);
    return "assignedJob";
    }**/

    @RequestMapping("/assignedJob")
    public String assignedJob() {
        return "assignedJob";
    }

}