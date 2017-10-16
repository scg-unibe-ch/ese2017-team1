package hello;

/**
 * Created by angelakeller on 16.10.17.
 * This class should provide the clientForm.html
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String name;
    private String company;
    private String street;
    private String city;
    private String email;

    public String getName() {return name;}
    public String getCompany() { return company;}
    public String getStreet() {return street;}
    public String getCity() {return city;}
    public String getEmail() {return email;}

    public void setName() {this.name = name;}
    public void setCompany() {this.company = company;}
    public void setStreet() {this.street = street;}
    public void setCity() {this.city = city;}
    public void setEmail() {this.email = email;}
}
