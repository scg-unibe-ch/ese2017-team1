package hello.Controllers;

import hello.ProductOrders.ProductOrder;
import hello.Repositories.*;
import hello.Services.*;
import hello.Tour.Tour;
import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import hello.Users.Driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.sql.*;

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

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private TrailerRepository trailerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

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

        // Assigns vehicle to tour
        Tour tour = tourService.findTour(tourId);
        Vehicle vehicle = vehicleService.findVehicle(vehId);
        vehicle.setFree(vehicle.getFree() - 1);
        tour.setVehicle(vehicle);
        tourService.save(tour);
        vehicleService.save(vehicle);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("tour", tour);
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
        Tour tour = this.tourRepository.findOne(tourId);
        Trailer trailer = this.trailerRepository.findOne(trailerId);
        trailer.setFree(trailer.getFree() - 1);
        tour.setTrailer(trailer);
        this.trailerRepository.save(trailer);

        Integer palettes = tour.getVehicle().getPalettesAmount() + tour.getTrailer().getPalettesAmount();
        tour.setFreePalettes(palettes);
        this.tourRepository.save(tour);
        model.addAttribute("trailer", trailer);
        model.addAttribute("tour", tour);

        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();

        ArrayList<ProductOrder> products = new ArrayList<>();
        for(ProductOrder productOrder : productOrders){
            if(productOrder.getAccOrRej().equalsIgnoreCase("akzeptiert")) {}
            else {
                if(productOrder.getTour() == null){
                    products.add(productOrder);
                }
            }
        }
        model.addAttribute("products", products);

        ArrayList<ProductOrder> tourProducts = new ArrayList<>();
        for (ProductOrder productOrder : productOrders){
            if(productOrder.getTour() == tour){
                tourProducts.add(productOrder);
            }
        }
        model.addAttribute("tourProducts", tourProducts);
        return "newTourProductOrders";
    }

    @RequestMapping(value="/newTourProductOrders/{tourId}/{prodId}/{add}")
    public String tourProduct(@PathVariable("tourId") Long tourId, @PathVariable("prodId") Long prodId, @PathVariable ("add") Long add, Model model) {
        Tour tour = this.tourRepository.findOne(tourId);
        ProductOrder product = this.productOrderRepository.findOne(prodId);
        if(add==1){
            if(product.getTour()==null){
                Integer free = tour.getFreePalettes()-(product.getProduct().getPalettes()*Integer.parseInt(product.getAmount()));
                if(free >= 0){
                    product.setTour(tour);
                    tour.setFreePalettes(free);
                }
            }
        }
        if(add==(-1)){
            if(product.getTour()!=null){
                if(product.getTour().getId()==tourId){
                    product.setTour(null);
                    tour.setFreePalettes(tour.getFreePalettes()+(product.getProduct().getPalettes()*Integer.parseInt(product.getAmount())));
                }
            }
        }
        this.productOrderRepository.save(product);
        this.tourRepository.save(tour);
        model.addAttribute("product", product);
        model.addAttribute("tour", tour);

        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();

        ArrayList<ProductOrder> products = new ArrayList<>();
        for(ProductOrder productOrder : productOrders){
            if(productOrder.getAccOrRej().equalsIgnoreCase("akzeptiert")) {}
            else {
                if(productOrder.getTour() == null){
                    products.add(productOrder);
                }
            }
        }
        model.addAttribute("products", products);

        ArrayList<ProductOrder> tourProducts = new ArrayList<>();
        for (ProductOrder productOrder : productOrders){
            if(productOrder.getTour() == tour){
                tourProducts.add(productOrder);
            }
        }
        model.addAttribute("tourProducts", tourProducts);
        return "newTourProductOrders";
    }

    @RequestMapping(value="/deleteTourCheck/{tourId}")
    public String checkDeleteTour(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = this.tourRepository.findOne(tourId);
        Iterable<ProductOrder> allProductOrders = productOrderRepository.findAll();
        ArrayList<ProductOrder> products = new ArrayList<>();
        for(ProductOrder product : allProductOrders){
            if(product.getTour()==tour) {
                products.add(product);
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("tour", tour);
        return "deleteTourCheck";
    }

    @RequestMapping(value="/deleteTour/{tourId}")
    public String deleteTour(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = this.tourRepository.findOne(tourId);
        Iterable<ProductOrder> allProductOrders = productOrderRepository.findAll();
        ArrayList<ProductOrder> products = new ArrayList<>();
        for(ProductOrder product : allProductOrders){
            if(product.getTour()==tour){
                products.add(product);
                product.setTour(null);
                this.productOrderRepository.save(product);
            }
        }
        Trailer trailer = tour.getTrailer();
        trailer.setFree(trailer.getFree()+1);
        this.trailerRepository.save(trailer);
        Vehicle vehicle = tour.getVehicle();
        vehicle.setFree(vehicle.getFree()+1);
        this.vehicleRepository.save(vehicle);
        
        this.tourRepository.delete(tour);
        model.addAttribute("products", products);
        model.addAttribute("tour", tour);
        return "deleteTour";
    }

}