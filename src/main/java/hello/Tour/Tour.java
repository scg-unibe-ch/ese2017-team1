package hello.Tour;

import hello.Trucks.Trailer;
import hello.Trucks.Vehicle;
import hello.Users.Driver.Driver;

import javax.persistence.*;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Trailer trailer;
    private Integer freePalettes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        trailer.setFree(trailer.getFree()+1);
        freePalettes += trailer.getPalettesAmount();
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        vehicle.setFree(vehicle.getFree()+1);
        freePalettes += vehicle.getPalettesAmount();
    }

    public Integer getFreePalettes() {
        return freePalettes;
    }

    public void setFreePalettes(Integer freePalettes) {
        this.freePalettes = freePalettes;
    }
}
