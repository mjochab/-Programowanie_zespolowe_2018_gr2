package warsztatsamochodowy.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
/**
 *
 * @author Piotr Świder
 */
/**
 * Klasa przechowujaca dane o klientach
 * 
 */
@Entity
@Table(name="KLIENCI")
public class Klient implements Serializable {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
 
    @Column(name="IMIE", length = 255)
    private String imie;
   
    @Column(name="NAZWISKO", length = 255)
    private String nazwisko;
  
    @Column(name="NR_TEL", length = 20)
    private String nrTel;
    
    @ManyToOne
    @JoinColumn(name="SAMOCHOD_ID")
    private Samochod samochod;
 
    public Klient() {
    }

    public Klient(Long id) {
        this.id = id;
    }
    /**
     * Modul Klienci
     * 
     * @param imie
     * @param nazwisko
     * @param nrTel
     * @param samochod 
     */
    public Klient(String imie, String nazwisko, String nrTel, Samochod samochod) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrTel = nrTel;
        this.samochod = samochod;
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
}