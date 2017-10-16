package hello;

/**
 * Created by angelakeller on 16.10.17.
 * This class provides the product.html and saves the order details
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String product;
    private int amount;

    public int getId() {return id;}
    public String getProduct() { return product;}
    public int getAmount() {return amount;}

    public void setId() {this.id = id;}
    public void setProduct() {this.product = product;}
    public void setAmount() {this.amount = amount;}

}
