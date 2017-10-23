package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class was created to save a job. At the moment we are not using it.
 * Instead we are using the ProductOrder classes and the FormController for it.
 *
 *
 * Job object which contains the required information concerning a job:
 * Id of the product, client (name), product description, address of the client, email of the client (not mandatory).
 *
 * We are using Thymeleaf, which parses the form.html template
 * and evaluates the various template expressions to render the form.
 */

/*@Entity // This tells Hibernate to make a table out of this class
public class Job {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String product;
    private int amount;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct(){
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }

    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}*/