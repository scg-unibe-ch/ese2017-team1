package hello;

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
    private Integer id;
    private String name;
    private String company;
    private String street;
    private String city;
    private String email;





    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    private String land;

    public Integer getId() {return id;}
    public String getName() {return name;}
    public String getCompany() { return company;}
    public String getStreet() {return street;}
    public String getCity() {return city;}
    public String getEmail() {return email;}

    public void setId (Integer id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCompany(String company) {this.company = company;}
    public void setStreet(String street) {this.street = street;}
    public void setCity(String city) {this.city = city;}
    public void setEmail(String email) {this.email = email;}
}
