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
public class Task {
    private int TaskId;
    
    private Klient klient;
    
    private Pracownik pracownik;
    
    private Samochod samochod;
    
    private float price;
    
    private String status;

    public Task(int TaskId, Klient klient, Pracownik pracownik, Samochod samochod, float price, String status) {
        this.TaskId = TaskId;
        this.klient = klient;
        this.pracownik = pracownik;
        this.samochod = samochod;
        this.price = price;
        this.status = status;
    }

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int TaskId) {
        this.TaskId = TaskId;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
}
