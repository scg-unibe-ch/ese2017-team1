package hello.Services;

import hello.Repositories.VehicleRepository;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service responsible for handling requests concerning vehicles
 */
@Service("vehicleService")
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    /**
     * @return Iterable of all Vehicles in vehicleRepository
     */
    public Iterable<Vehicle> listAllVehicles(){ return this.vehicleRepository.findAll(); }

    /**
     * returns an ArrayList of Vehicles that are still available
     */
    public ArrayList<Vehicle> listVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Checks if a vehicle is still available, if yes it's listed in vehicles
        for (Vehicle vehicle : listAllVehicles()) {
            if (vehicle.getFree() > 0) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    /**
     * returns an ArrayList of Vehicles that are already used (all Vehicles of that type are assigned to a tour)
     */
    public ArrayList<Vehicle> listUsedVehicles() {
        ArrayList<Vehicle> usedVehicles = new ArrayList<>();

        // Checks if a vehicle is still available, if not it's listed in usedVehicles
        for (Vehicle vehicle : listAllVehicles()) {
            if (vehicle.getFree() <= 0) {
                usedVehicles.add(vehicle);
            }
        }
        return usedVehicles;
    }

    /**
     * returns vehicle with Id vehId
     */
    public Vehicle findVehicle(Long vehId) { return vehicleRepository.findOne(vehId); }

    /**
     * saves vehicle in vehicleRepository
     */
    public void save(Vehicle vehicle) { vehicleRepository.save(vehicle); }
}
