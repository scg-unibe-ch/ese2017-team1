package hello.Controllers;

import hello.Trucks.Vehicle;
import hello.Repositories.VehicleRepository;
import hello.Users.Driver.Driver;
import hello.Repositories.DriverRepository;
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


    @RequestMapping(value="/newTour")
    public String list(@ModelAttribute("driver") Driver driver, Model model){
        Iterable<Driver> drivers = this.driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        return "newTour";
    }


    @RequestMapping(value="/newTourTruck/{driverId}")
    public String assignedJob(@PathVariable("driverId") Long driverId, Model model) {
        Driver driver = this.driverRepository.findOne(driverId);
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
        model.addAttribute("driver", driver);
        return "newTourTruck";
    }

    @RequestMapping(value="/newTourTruck/{driverId}/{vehId}")
    public String assignedJob(@PathVariable("driverId") Long driverId, @PathVariable("vehId") Long vehId, Model model) {
        Driver driver = this.driverRepository.findOne(driverId);
        Vehicle vehicle = this.vehicleRepository.findOne(vehId);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("driver", driver);
        return "newTourTruck";
    }

}