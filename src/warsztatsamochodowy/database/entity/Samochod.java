package warsztatsamochodowy.database.entity;

import java.io.Serializable;
 
/**
 *
 * @author Piotr Świder
 */
public class Samochod implements Serializable {
    
    private Long id;
    
    private Klient klient;
    
    private String vin;
 
    private String producent;
   
    private String model;
   
    private String typ;
 
    public Samochod() {
    }

    public Samochod(Long id, String vin, String producent, String model, String typ, Klient klient) {
        this.id = id;
        this.klient = klient;
        this.vin = vin;
        this.producent = producent;
        this.model = model;
        this.typ = typ;
    }

    public Samochod(Long id, String vin, String producent, String model, String typ) {
        this.id = id;
        this.vin = vin;
        this.producent = producent;
        this.model = model;
        this.typ = typ;
    }
    
    public Samochod(Long id, String producent, String model) {
        this.id = id;
        this.producent = producent;
        this.model = model;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

   

    @Override
    public String toString() {
        if(typ==null && vin==null){
            return producent + " " + model;
        }else return producent + " " + model + " " + typ + " " + vin;
    }
    


   

}