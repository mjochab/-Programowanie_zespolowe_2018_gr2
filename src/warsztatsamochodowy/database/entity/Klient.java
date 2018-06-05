package warsztatsamochodowy.database.entity;

import java.io.Serializable;
 
/**
 *
 * @author Piotr Świder
 */
/**
 * Klasa przechowujaca dane o klientach
 * 
 */
public class Klient implements Serializable {

    private Long id;

    private String imie;

    private String nazwisko;

    private String nrTel;
    
    private String miejscowosc;
    
    private String adres;

    private String email;
    
    private Samochod samochod;
 
    public Klient() {
    }

    public Klient(String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String email, Samochod samochod) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrTel = nrTel;
        this.miejscowosc = miejscowosc;
        this.adres = adres;
        this.email = email;
        this.samochod = samochod;
    }
    
    

    public Klient(Long id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String email, Samochod samochod) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrTel = nrTel;
        this.miejscowosc = miejscowosc;
        this.adres = adres;
        this.samochod = samochod;
        this.email = email;
    }
    
    public Klient(Long id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String email) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrTel = nrTel;
        this.miejscowosc = miejscowosc;
        this.adres = adres;
        this.email = email;
    }

    public Klient(Long id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }   

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
}