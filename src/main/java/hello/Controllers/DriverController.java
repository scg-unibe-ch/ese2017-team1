package hello.Controllers;

import hello.Login.User;
import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Repositories.ProductOrderRepository;
import hello.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

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




}
