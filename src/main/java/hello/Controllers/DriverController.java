package hello.Controllers;

import hello.Services.ProductOrderService;
import hello.Services.TourService;
import hello.Users.User;
import hello.ProductOrders.ProductOrder;
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
 * Controller which is responsible for the interaction of a Driver with the programm.
 * A Driver can display the tours he is assigned to, set the productOrders in that tour to either "akzeptiert"
 * or "abgelehnt" and finish a tour.
 */
@Controller
public class DriverController extends WebMvcConfigurerAdapter {

    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;

    /**
     * "Hopepage" of Driver
     * Possibility to go to tours planned for that driver
     */
    @RequestMapping("/driver")
    public String driver() {
        return "driver";
    }

    /**
     * Shows Tours that the logged in Driver should work on
     */
    @RequestMapping("/showToursDriver")
    public String driverTours(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("matches", tourService.listDriverTours(user));
        return "showToursDriver";
    }

    /**
     * Shows the tour that was selected by the logged in Driver to display.
     * @param tourId TourID from the tour that was selected in the previous page
     */
    @RequestMapping(value="/driverTours/{tourId}")
    public String driverTours(@PathVariable("tourId") Long tourId, Model model) {
        ArrayList<String> addresses = productOrderService.addresses(tourId);
        ArrayList<ProductOrder> productOrders = productOrderService.listTourProductOrders(tourId);

        for(ProductOrder productOrder : productOrders){
            if(productOrder.getAccOrRej().equalsIgnoreCase("keine Angabe"))
                model.addAttribute("error",true);
            else
                model.addAttribute("noError",true);
        }

        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("matches", productOrders);
        model.addAttribute("addresses", addresses.toArray());

        return "driverTours";
    }

    @RequestMapping(value="/driverProductOrder/{tourId}/{prodId}")
    public String driverProductOrder(@PathVariable("tourId") Long tourId, @PathVariable("prodId") Long prodId, Model model) {
        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("prod", productOrderService.findProductOrder(prodId));
        return "driverProductOrder";
    }

    /**
     * This page allows the logged in Driver to set a ProductOrder to "accepted" or "rejected"
     * The status of the ProductOrder which is given by the ID as a param is set to either "akzepiert" or "abgelehnt"
     * which is also given as a param to the method.
     * The two parameters depend on the selection on the last page.
     * The user selects either "akzeptiert" or "abgelehnt" at the row of a specific ProductOrder.
     * @param prodId the productOrder from the tour that was selected
     * @param accOrRej the value selected for the productOrder ("akzeptiert" or "abgelehnt")
     */
    @RequestMapping(value="/driverProductOrder/{tourId}/{prodId}/{accOrRej}")
    public String acceptedOrRejected(@PathVariable("tourId") Long tourId, @PathVariable("prodId") Long prodId, @PathVariable("accOrRej") String accOrRej, Model model) {
        productOrderService.accOrRej(prodId, accOrRej);
        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("productOrder", productOrderService.findProductOrder(prodId));
        return "acceptedOrRejected";
    }

    /**
     * Driver can finish Tour but only when every productOrder in the Tour is either set to "akzepiert" or "abgelehnt".
     * The default status of AccOrRej of a ProductOrder is "keine Angabe".
     * After this method Driver is asked whether he really wants to finish that tour (endTourCheck)
     * before tour is really marked as finished.
     *
     *
     * @param tourId TourID of the current Tour which should be marked as finished
     * @return endTourFailed if not every ProductOrder in the Tour is marked as "akzeptiert" or "abgelehnt"
     *         endTourCheck otherwise
     */
    @RequestMapping(value="/endTourCheck/{tourId}")
    public String endTourCheck(@PathVariable("tourId") Long tourId, Model model) {
        ArrayList<ProductOrder> productOrders = productOrderService.listTourProductOrders(tourId);
        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("productOrders", productOrders);
        return "endTourCheck";
    }

    /**
     * Sets tour status to finished and sets TourID of each ProductOrder which was in that tour to null.
     * Which means that the ProductOrders are now no longer assigned to a tour.
     *
     * @param tourId TourID of tour which is to be marked as finished
     */
    @RequestMapping(value="/endTour/{tourId}")
    public String endTour(@PathVariable("tourId") Long tourId, Model model) {
        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("productOrders", productOrderService.listTourProductOrders(tourId));

        tourService.finishTour(tourId);

        return "endTour";
    }
}
