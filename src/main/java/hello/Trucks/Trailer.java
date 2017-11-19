package hello.Trucks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by angelakeller on 10.11.17.
 */

@Entity
public class Trailer{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Integer amount;
    private String model;
    private Integer palettesAmount;
    private Integer freight;
    private Integer free;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPalettesAmount() {
        return palettesAmount;
    }

    public void setPalettesAmount(Integer palettesAmount) {
        this.palettesAmount = palettesAmount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }
}
