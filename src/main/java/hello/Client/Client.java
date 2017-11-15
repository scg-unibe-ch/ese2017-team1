package hello.Client;

/**
 * Created by angelakeller on 16.10.17.
 * This class should provide the clientForm.html
 */

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    public String name;
    private String phone;
    private String street;
    private String city;
    private String email;
    private String land;


    public Long getId() {return id;}

    public void setId (Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPhone() { return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getLand() {return land;}

    public void setLand(String land) { this.land = land;}

}
