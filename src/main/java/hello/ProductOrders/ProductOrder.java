package hello.ProductOrders;

/**
 * Created by angelakeller on 16.10.17.
 * This class provides the product.html and saves the order details
*/
import hello.Client.Client;

import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
public class ProductOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String product;
    private String amount;
    @ManyToOne
    private Client client;


    public Integer getId() {return id;}
    public String getProduct() { return product;}
    public String getAmount() {return amount;}

    public void setId(Integer id) {this.id = id;}
    public void setProduct(String product) {this.product = product;}
    public void setAmount(String amount) {this.amount = amount;}

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
