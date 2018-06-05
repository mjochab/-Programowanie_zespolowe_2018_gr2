/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

import java.io.Serializable;

/**
 *
 * @author lukasz
 */
public class Repair implements Serializable {

    private Long ID;
    private Pracownik pracownik;
    private Klient klient;
    private Samochod samochod;
    private String koszt;
    private String status;
    private String opis;

    public Repair(Long ID, String koszt, String status, String opis) {
        this.ID = ID;
        this.koszt = koszt;
        this.status = status;
        this.opis = opis;
    }

    public Repair(Long ID, String koszt, String status, String opis, Pracownik pracownik, Klient klient, Samochod samochod) {
        this.ID = ID;
        this.klient = klient;
        this.samochod = samochod;
        this.pracownik = pracownik;
        this.koszt = koszt;
        this.status = status;
        this.opis = opis;
    }

    public Repair(Long ID, String koszt, String status, String opis, Pracownik pracownik, Klient klient) {
        this.ID = ID;
        this.klient = klient;
        this.pracownik = pracownik;
        this.koszt = koszt;
        this.status = status;
        this.opis = opis;
    }

    public Repair(Long ID, String koszt, String status, String opis, Pracownik pracownik) {
        this.ID = ID;
        this.pracownik = pracownik;
        this.koszt = koszt;
        this.status = status;
        this.opis = opis;
    }

    public Long getID() {
        return ID;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public Klient getKlient() {
        return klient;
    }

    public Samochod getSamochod() {
        return samochod;
    }

    public String getKoszt() {
        return koszt;
    }

    public String getStatus() {
        return status;
    }

    public String getOpis() {
        return opis;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    
    
}
