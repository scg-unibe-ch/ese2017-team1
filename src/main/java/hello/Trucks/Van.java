package hello.Trucks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by angelakeller on 10.11.17.
 */
@Entity
public class Van {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String model;
    private Integer palettesAmount;
    private Integer freight;

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
}
