package hello.Trucks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by angelakeller on 10.11.17.
 */

@Entity
public class TractorMachine {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String model;
    private Integer amount;
    //private Trailer trailer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}