package hello.Services;

import hello.Repositories.VehicleRepository;
import hello.Trucks.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("vehicleService")
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public Iterable<Vehicle> listAllVehicles(){ return this.vehicleRepository.findAll(); }

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

    public Vehicle findVehicle(Long vehId) { return vehicleRepository.findOne(vehId); }

    public void save(Vehicle vehicle) { vehicleRepository.save(vehicle); }
}