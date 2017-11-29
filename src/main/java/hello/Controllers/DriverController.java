package hello.Controllers;

import hello.Repositories.TourRepository;
import hello.Tour.Tour;
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

    @Autowired
    private UserService userService;

    @Autowired
    private TourRepository tourRepository;

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

        ArrayList<Tour> matches = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Iterable<Tour> tours = this.tourRepository.findAll();

        /**
         * adds tours to the List that are not done yet
         * and are supposed to be executed by the driver logged in
         */
        for(Tour tour : tours){
            if (tour.getFinished() == null) {
                if(tour.getDriver().getId() == user.getId()){
                    matches.add(tour);
                }
            }
        }
        model.addAttribute("matches", matches);
        return "showToursDriver";
    }

    /**
     * Shows the tour that was selected by the logged in Driver to display.
     * @param tourId TourID from the tour that was selected in the previous page
     */
    @RequestMapping(value="/driverTours/{tourId}")
    public String driverTours(@PathVariable("tourId") Long tourId, Model model) {

        ArrayList<ProductOrder> matches = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();

        Tour tour = tourRepository.findOne(tourId);
        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();

        for(ProductOrder productOrder : productOrders){
            if(productOrder.getTour() != null){
                if(productOrder.getTour().getId() == tourId){
                    matches.add(productOrder);
                    addresses.add(productOrder.getClient().getStreet() +"," +productOrder.getClient().getCity() + ","+productOrder.getClient().getLand());
                }
            }
        }
        model.addAttribute("tour", tour);
        model.addAttribute("matches", matches);
        model.addAttribute("addresses", addresses.toArray());

        return "driverTours";
    }

    /**
     * This page allows the logged in Driver to set a ProductOrder to "accepted" or "rejected"
     * The status of the ProductOrder which is given by the ID as a param is set to either "akzepiert" or "abgelehnt"
     * which is also given as a param to the method.
     * The two parameters depend on the selection on the last page.
     * The user selects either "akzeptiert" or "abgelehnt" at the row of a specific ProductOrder.
     * @param productOrderId the productOrder from the tour that was selected
     * @param accOrRej the value selected for the productOrder ("akzeptiert" or "abgelehnt")
     */
    @RequestMapping(value="/driverTours/{productOrderId}/{accOrRej}")
    public String acceptedOrRejected(@PathVariable("productOrderId") Long productOrderId, @PathVariable("accOrRej") String accOrRej, Model model) {
        ProductOrder productOrder = this.productOrderRepository.findOne(productOrderId);

        productOrder.setAccOrRej(accOrRej);

        this.productOrderRepository.save(productOrder);
        model.addAttribute("productOrder", productOrder);

        return "acceptedOrRejected";
    }

    @RequestMapping(value="/endTourCheck/{tourId}")
    public String endTourCheck(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = tourRepository.findOne(tourId);
        Iterable<ProductOrder> allProductOrders = this.productOrderRepository.findAll();

        ArrayList<ProductOrder> productOrders = new ArrayList<>();
        for(ProductOrder productOrder : allProductOrders){
            if(productOrder.getTour() != null){
                if(productOrder.getTour().getId() == tourId){
                    if(productOrder.getAccOrRej().equalsIgnoreCase("keine Angabe")){
                        return "endTourFailed";
                    }
                    productOrders.add(productOrder);
                }
            }
        }
        model.addAttribute("tour", tour);
        model.addAttribute("productOrders", productOrders);
        return "endTourCheck";
    }

    @RequestMapping(value="/endTour/{tourId}")
    public String endTour(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = tourRepository.findOne(tourId);
        Iterable<ProductOrder> allProductOrders = this.productOrderRepository.findAll();

        ArrayList<ProductOrder> productOrders = new ArrayList<>();
        for(ProductOrder productOrder : allProductOrders){
            if(productOrder.getTour() != null){
                if(productOrder.getTour().getId() == tourId){
                    productOrders.add(productOrder);
                    productOrder.setTour(null);
                    this.productOrderRepository.save(productOrder);
                }
            }
        }
        tour.setFinished(1);
        this.tourRepository.save(tour);

        model.addAttribute("tour", tour);
        model.addAttribute("productOrders", productOrders);

        return "endTour";
    }
}
