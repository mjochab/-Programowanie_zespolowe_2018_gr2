/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

/**
 *
 * @author lukasz
 */
public class Repair {
    private int FixId;
    private int PracownikId;
    private int SamochodId;
    private String Status;
    private float Koszt;
    private String carName;
    private String carProducer;
    public Repair(int FixId, int PracownikId, int SamochodId, String Status, float Koszt) {
        this.FixId = FixId;
        this.PracownikId = PracownikId;
        this.SamochodId = SamochodId;
        this.Status = Status;
        this.Koszt = Koszt;
    }

    public Repair(int FixId, String carName, String carProducer) {
        this.FixId = FixId;
        this.carName = carName;
        this.carProducer = carProducer;
    }   

   
    public int getFixId() {
        return FixId;
    }

    public void setFixId(int FixId) {
        this.FixId = FixId;
    }

  

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public float getKoszt() {
        return Koszt;
    }

    public void setKoszt(float Koszt) {
        this.Koszt = Koszt;
    }

    public int getPracownikId() {
        return PracownikId;
    }

    public void setPracownikId(int PracownikId) {
        this.PracownikId = PracownikId;
    }

    public int getSamochodId() {
        return SamochodId;
    }

    public void setSamochodId(int SamochodId) {
        this.SamochodId = SamochodId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarProducer() {
        return carProducer;
    }

    public void setCarProducer(String carProducer) {
        this.carProducer = carProducer;
    }
   
}
