package warsztatsamochodowy.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 *
 * @author Piotr Świder
 */
@Entity
@Table(name="SAMOCHODY")
public class Samochod implements Serializable {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
 
    @Column(name="MARKA", length = 255)
    private String marka;
   
    @Column(name="MODEL", length = 255)
    private String model;
   
    @Column(name="NUMER_REJESTRACYJNY", length = 15)
    private String numerRejestracyjny;
 
    public Samochod() {
    }

    public Samochod(Long id) {
        this.id = id;
    }

    public Samochod(String marka, String model, String numerRejestracyjny) {
        this.marka = marka;
        this.model = model;
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    @Override
    public String toString() {
        return marka + " " + model + " " + numerRejestracyjny;
    }

   

}