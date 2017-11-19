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


    @RequestMapping(value="/newTour")
    public String list(@ModelAttribute("driver") Driver driver, Model model){
        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        return "newTour";
    }


    @RequestMapping(value="/newTourTruck/{driverId}")
    public String tourDriver(@PathVariable("driverId") Long driverId, Model model) {
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
        model.addAttribute("vehicles", vehicles);
        Driver driver = this.driverRepository.findOne(driverId);
        model.addAttribute("driver", driver);
        Tour tour = new Tour();
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

    @RequestMapping(value="/newTourProductOrders/{tourId}/{trailerId}")
    public String tourTrailer(@PathVariable("tourId") Long tourId, @PathVariable("trailerId") Long trailerId, Model model) {
        Iterable<ProductOrder> productOrders = this.productOrderRepository.findAll();
        model.addAttribute("productOrders", productOrders);
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
        return "newTourProductOrders";
    }

}