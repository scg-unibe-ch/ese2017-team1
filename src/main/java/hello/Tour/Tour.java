package hello.Tour;

import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import hello.Users.Driver.Driver;

import javax.persistence.*;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Trailer trailer;
    private Integer freePalettes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getFreePalettes() {
        return freePalettes;
    }

    public void setFreePalettes(Integer freePalettes) {
        this.freePalettes = freePalettes;
    }
}
