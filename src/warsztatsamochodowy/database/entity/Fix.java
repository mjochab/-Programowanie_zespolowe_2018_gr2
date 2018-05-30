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
public class Fix {
    private int FixId;
    private Pracownik Pracownik;
    private Samochod Samochod;
    private String Status;
    private float Koszt;
    
    
    public Fix(int FixId, Pracownik Pracownik, Samochod Samochod, String Status, float Koszt) {
        this.FixId = FixId;
        this.Pracownik = Pracownik;
        this.Samochod = Samochod;
        this.Status = Status;
        this.Koszt = Koszt;
    }
   
    public int getFixId() {
        return FixId;
    }

    public void setFixId(int FixId) {
        this.FixId = FixId;
    }

    public Pracownik getPracownik() {
        return Pracownik;
    }

    public void setPracownik(Pracownik Pracownik) {
        this.Pracownik = Pracownik;
    }

    public Samochod getSamochod() {
        return Samochod;
    }

    public void setSamochod(Samochod Samochod) {
        this.Samochod = Samochod;
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
   
}
