package hello.Controllers;

import hello.Users.User;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

/**
 * Created by angelakeller on 01.11.17.
 */
@Controller
public class DriverController extends WebMvcConfigurerAdapter {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @RequestMapping("/driver")
    public String driver() {
        return "driver";
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/driverTours")
    public String driverTours(Model model) {

        ArrayList<ProductOrder> matches = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();

        for(ProductOrder productOrder : productOrders){

            if(productOrder.getDriver().getId() == user.getId()){
                matches.add(productOrder);
            }
        }
        model.addAttribute("matches", matches);

        return "driverTours";
    }


    @RequestMapping(value="/driverTours/{productOrderId}/{accOrRej}")
    public String acceptedOrRejected(@PathVariable("productOrderId") Long productOrderId, @PathVariable("accOrRej") String accOrRej, Model model) {
        ProductOrder productOrder = this.productOrderRepository.findOne(productOrderId);

        productOrder.setAccOrRej(accOrRej);

        this.productOrderRepository.save(productOrder);
        model.addAttribute("productOrder", productOrder);

        return "acceptedOrRejected";
    }


}
