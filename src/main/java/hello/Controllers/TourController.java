package hello.Controllers;

import hello.Product.Product;
import hello.ProductOrders.ProductOrder;
import hello.Services.*;
import hello.Tour.Tour;
import hello.Users.Driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Controller that is responsible for handling tours.
 * A tour is a set of Driver, Vehicle, Trailer and some Productorders that need to be delivered.
 * Every Logistician can create, edit or delete Tours.
 */
@Controller
public class TourController extends WebMvcConfigurerAdapter {

    @Autowired
    private TourService tourService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private TrailerService trailerService;
    @Autowired
    private ProductOrderService productOrderService;


    /**
     * Lists all the tours that are already planned or in the process of planning.
     */
    @RequestMapping(value="/showTours")
    public String tours(@ModelAttribute("tour") Tour tour, Model model){
        model.addAttribute("tours", tourService.listTours());
        model.addAttribute("incompTours", tourService.listIncompTours());
        return "showTours";
    }

    /**
     * Lists all the tours that are already finished.
     * A driver can put a tour on "finished" if he has delivered all the productOrders within that tour
     * successfully or unsuccessfully.
     */
    @RequestMapping(value="/showToursOld")
    public String listToursOld(@ModelAttribute("tour") Tour tour, Model model){
        model.addAttribute("tours", tourService.listFinishedTours());
        return "showToursOld";
    }

    /**
     * Create a new tour
     */
    @RequestMapping(value="/newTourCreate")
    public String newTour(@ModelAttribute("driver") Driver driver, Model model){
        Tour tour = new Tour();
        tourService.save(tour);
        model.addAttribute("tour", tour);
        return "newTourCreate";
    }

    /**
     * The logistician can choose a driver he/she wants to assign the tour to.
     * @param tourId Id of tour the logistician is currently creating or editing.
     */
    @RequestMapping(value="/newTour/{tourId}")
    public String Driver(@PathVariable("tourId") Long tourId, @ModelAttribute("driver") Driver driver, Model model){
        model.addAttribute("drivers", driverService.listDrivers());
        model.addAttribute("tour", tourService.findTour(tourId));
        return "newTour";
    }

    /**
     * Choose a vehicle for the tour. Driver chosen in newTour is assigned to tour.
     * @param tourId Id of tour the logistician is currently creating or editing
     * @param driverId Id of driver chosen in newTour
     */
    @RequestMapping(value="/newTourTruck/{tourId}/{driverId}")
    public String tourDriver(@PathVariable("tourId") Long tourId, @PathVariable("driverId") Long driverId, Model model) {

        model.addAttribute("vehicles", vehicleService.listVehicles());
        model.addAttribute("usedVehicles", vehicleService.listUsedVehicles());

        // Assigns driver to tour
        Driver driver = driverService.findDriver(driverId);
        model.addAttribute("driver", driver);
        Tour tour = tourService.findTour(tourId);
        tour.setDriver(driver);
        tourService.save(tour);
        model.addAttribute("tour", tour);
        return "newTourTruck";
    }

    /**
     * Choose a trailer for the tour. Vehicle chosen in newTourTruck is assigned to tour.
     * @param tourId Id of tour the logistician is currently creating or editing
     * @param vehId Id of vehicle chosen in newTourTruck
     */
    @RequestMapping(value="/newTourTrailer/{tourId}/{vehId}")
    public String tourTruck(@PathVariable("tourId") Long tourId, @PathVariable("vehId") Long vehId, Model model) {
        model.addAttribute("trailers", trailerService.listTrailers());
        model.addAttribute("usedTrailers", trailerService.listUsedTrailers());

        tourService.assignVehicle(tourId,vehId);

        model.addAttribute("tour", tourService.findTour(tourId));
        return "newTourTrailer";
    }

    /**
     * Edit tour: Lists all productOrders in your tour and the ones that are not assigned to a tour yet, so logistician
     * can change productOrders in the tour.
     * @param tourId Id of tour the logistician is currently editing
     */
    @RequestMapping(value="/newTourProductOrders/{tourId}")
    public String tourProducts(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = tourService.findTour(tourId);
        model.addAttribute("tour",tour);
        model.addAttribute("products", productOrderService.listNotAccNoTourProductOrders());
        model.addAttribute("tourProducts", productOrderService.listTourProductOrders(tourId));
        model.addAttribute("addresses", productOrderService.addresses(tourId));
        return "newTourProductOrders";
    }

    /**
     * Lists all productOrders in your tour and the ones that are not assigned to a tour yet, so logistician
     * can set/change productOrders in the tour.
     * Trailer chosen in newTourTrailer is assigned to tour.
     * @param tourId Id of tour the logistician is currently creating or editing
     * @param trailerId Id of trailer chosen in newTourTrailer
     */
    @RequestMapping(value="/newTourProductOrders/{tourId}/{trailerId}")
    public String tourTrailer(@PathVariable("tourId") Long tourId, @PathVariable("trailerId") Long trailerId, Model model) {
        tourService.assignTrailer(tourId,trailerId);
        model.addAttribute("tour", tourService.findTour(tourId));
        model.addAttribute("products", productOrderService.listNotAccNoTourProductOrders());
        model.addAttribute("tourProducts", productOrderService.listTourProductOrders(tourId));
        model.addAttribute("addresses", productOrderService.addresses(tourId));
        return "newTourProductOrders";
    }

    /**
     * Lists all productOrders in your tour and the ones that are not assigned to a tour yet, so logistician
     * can set/change productOrders in the tour.
     * Adds or deletes productOrder to/from tour.
     * @param tourId Id of tour the logistician is currently creating or editing
     * @param prodId Id of productOrder that is added or deleted
     * @param add 1 if productOrder is added to tour, -1 if productOrder is deleted from tour
     */
    @RequestMapping(value="/newTourProductOrders/{tourId}/{prodId}/{add}")
    public String tourProduct(@PathVariable("tourId") Long tourId, @PathVariable("prodId") Long prodId, @PathVariable ("add") Long add, Model model) {
        Tour tour = tourService.findTour(tourId);
        ProductOrder product = productOrderService.findProductOrder(prodId);
        if (add > 0){
            Integer free = tour.getFreePalettes() - (product.getProduct().getPalettes()*Integer.parseInt(product.getAmount()));
            if(free < 0){
                model.addAttribute("error", true);
            }
        }
        tourService.addProduct(tourId, prodId, add);
        model.addAttribute("tour", tour);
        model.addAttribute("products", productOrderService.listNotAccNoTourProductOrders());
        model.addAttribute("tourProducts", productOrderService.listTourProductOrders(tourId));
        model.addAttribute("addresses", productOrderService.addresses(tourId));
        return "newTourProductOrders";
    }

    /**
     * Asks again if logistician really wants to delete tour.
     * @param tourId Id of tour that would be deleted
     */
    @RequestMapping(value="/deleteTourCheck/{tourId}")
    public String checkDeleteTour(@PathVariable("tourId") Long tourId, Model model) {
        model.addAttribute("products", productOrderService.listTourProductOrders(tourId));
        model.addAttribute("tour", tourService.findTour(tourId));
        return "deleteTourCheck";
    }

    /**
     * Deletes tour and shows tour that has been deleted.
     * @param tourId Id of tour that has been deleted
     */
    @RequestMapping(value="/deleteTour/{tourId}")
    public String deleteTour(@PathVariable("tourId") Long tourId, Model model) {
        model.addAttribute("products", productOrderService.listTourProductOrders(tourId));
        model.addAttribute("tour", tourService.findTour(tourId));
        tourService.deleteTour(tourId);
        return "deleteTour";
    }

}