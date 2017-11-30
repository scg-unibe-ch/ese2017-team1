package hello.Services;

import hello.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.Users.Driver.Driver;

@Service("driverService")
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Iterable<Driver> listDrivers() {
        Iterable<Driver> drivers = this.driverRepository.findAll();
        return drivers;
    }

    public Driver findDriver(Long driverId) { return this.driverRepository.findOne(driverId); }
}
