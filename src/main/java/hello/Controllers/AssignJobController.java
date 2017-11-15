package hello.Controllers;

import hello.Users.Driver.Driver;
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


    @RequestMapping(value="/jobToDriver/{productOrderId}")
    public String list(@ModelAttribute("driver") Driver driver, Model model, @PathVariable("productOrderId") Long productOrderId) {

        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        ProductOrder productOrder = this.productOrderRepository.findOne(productOrderId);
        model.addAttribute("productOrder", productOrder);
        return "jobToDriver";
    }


    @RequestMapping(value="/jobToDriver/{productOrderId}/{driverId}")
    public String assignedJob(@PathVariable("driverId") Long driverId, @PathVariable("productOrderId") Long productOrderId, Model model) {
        Driver driver = this.driverRepository.findOne(driverId);
        ProductOrder productOrder = this.productOrderRepository.findOne(productOrderId);
        productOrder.setDriver(driver);
        this.productOrderRepository.save(productOrder);
        model.addAttribute("productOrder", productOrder);
        model.addAttribute("driver", driver);
        return "assignedJob";
    }

}