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
public class ProductOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String product;
    private Integer amount;

    public Integer getId() {return id;}
    public String getProduct() { return product;}
    public Integer getAmount() {return amount;}

    public void setId(Integer id) {this.id = id;}
    public void setProduct(String product) {this.product = product;}
    public void setAmount(Integer amount) {this.amount = amount;}

}
