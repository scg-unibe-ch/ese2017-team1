package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Job object which contains the required information concerning a job:
 * Id of the product, client (name), product description, address of the client, email of the client (not mandatory).
 *
 * We are using Thymeleaf, which parses the form.html template
 * and evaluates the various template expressions to render the form.
 */

@Entity // This tells Hibernate to make a table out of this class
public class Job {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String client;
    private String product;
    private String address;
    private String email;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }
    public void setClient(String name) {
        this.client = client;
    }

    public String getProduct(){
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}