package hello.ProductOrders;

/**
 * Created by angelakeller on 16.10.17.
 * This class provides the product.html and saves the order details
*/
import hello.Client.Client;
import hello.Users.Driver.Driver;
import hello.Product.Product;

import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
public class ProductOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private String amount;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Driver driver;
    private String accOrRej;

    public String getAccOrRej() {
        return accOrRej;
    }

    public void setAccOrRej(String accOrRej) {
        this.accOrRej = accOrRej;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getAmount() {return amount;}

    public void setAmount(String amount) {this.amount = amount;}

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
