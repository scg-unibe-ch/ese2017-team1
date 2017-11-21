package hello.Controllers;

import hello.ProductOrders.ProductOrder;
import hello.Repositories.*;
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


@Controller
public class TourController extends WebMvcConfigurerAdapter {


    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TrailerRepository trailerRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;


    @RequestMapping(value="/showTours")
    public String listTours(@ModelAttribute("tour") Tour tour, Model model){
        Iterable<Tour> allTours = this.tourRepository.findAll();
        ArrayList<Tour> tours = new ArrayList<>();
        ArrayList<Tour> incompTours = new ArrayList<>();
        for (Tour tour1 : allTours){
            if(tour1.getDriver() != null && tour1.getVehicle()!= null && tour1.getTrailer()!= null){
                tours.add(tour1);
            }
            else{
                incompTours.add(tour1);
            }
        }
        model.addAttribute("tours", tours);
        model.addAttribute("incompTours", incompTours);
        return "showTours";
    }

    @RequestMapping(value="/newTourCreate")
    public String newTour(@ModelAttribute("driver") Driver driver, Model model){
        Tour tour = new Tour();
        this.tourRepository.save(tour);
        model.addAttribute("tour", tour);
        return "newTourCreate";
    }


    @RequestMapping(value="/newTour/{tourId}")
    public String Driver(@PathVariable("tourId") Long tourId, @ModelAttribute("driver") Driver driver, Model model){
        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        Tour tour = this.tourRepository.findOne(tourId);
        model.addAttribute("tour", tour);
        return "newTour";
    }

    @RequestMapping(value="/newTourTruck/{tourId}/{driverId}")
    public String tourDriver(@PathVariable("tourId") Long tourId, @PathVariable("driverId") Long driverId, Model model) {
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        Driver driver = this.driverRepository.findOne(driverId);
        model.addAttribute("driver", driver);
        Tour tour = this.tourRepository.findOne(tourId);
        tour.setDriver(driver);
        this.tourRepository.save(tour);
        model.addAttribute("tour", tour);
        return "newTourTruck";
    }

    @RequestMapping(value="/newTourTrailer/{tourId}/{vehId}")
    public String tourTruck(@PathVariable("tourId") Long tourId, @PathVariable("vehId") Long vehId, Model model) {
        Iterable<Trailer> trailers = this.trailerRepository.findAll();
        model.addAttribute("trailers", trailers);
        Tour tour = this.tourRepository.findOne(tourId);
        Vehicle vehicle = this.vehicleRepository.findOne(vehId);
        vehicle.setFree(vehicle.getFree() - 1);
        tour.setVehicle(vehicle);
        this.tourRepository.save(tour);
        this.vehicleRepository.save(vehicle);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("tour", tour);
        return "newTourTrailer";
    }

    @RequestMapping(value="/newTourProductOrders/{tourId}")
    public String tourProducts(@PathVariable("tourId") Long tourId, Model model) {
        Tour tour = this.tourRepository.findOne(tourId);
        model.addAttribute("tour",tour);
        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();

        ArrayList<ProductOrder> products = new ArrayList<>();
        for(ProductOrder productOrder : productOrders){
            if(productOrder.getTour() == null){
                products.add(productOrder);
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
            if(productOrder.getTour() == null){
                products.add(productOrder);
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
            if(productOrder.getTour() == null){
                products.add(productOrder);
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

}