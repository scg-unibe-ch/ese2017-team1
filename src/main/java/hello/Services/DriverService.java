package hello.Services;

import hello.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.Users.Driver.Driver;

/**
 * Service responsible for handling requests concerning drivers
 */
@Service("driverService")
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    /**
     * @return Iterable of all Drivers in driverRepository
     */
    public Iterable<Driver> listDrivers() { return this.driverRepository.findAll(); }

    /**
     * returns driver with Id driverId
     */
    public Driver findDriver(Long driverId) { return this.driverRepository.findOne(driverId); }

    /**
     * saves driver in driverRepository
     */
    public void save(Driver driver) { driverRepository.save(driver); }
}
