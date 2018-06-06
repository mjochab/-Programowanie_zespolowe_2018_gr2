/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Artur
 */
public class Pracownik {

    private IntegerProperty ID;
    private StringProperty Login;
    private StringProperty Haslo;
    private StringProperty Imie;
    private StringProperty Nazwisko;
    private StringProperty Miejscowosc;
    private StringProperty Adres;
    private StringProperty Telefon;
    private StringProperty Email;
    private StringProperty Specjalizacja;
    private StringProperty Status;
    private IntegerProperty Wynagrodzenie;

    public Pracownik(Integer ID, String Login, String Haslo, String Imie, String Nazwisko, String Miejscowosc, String Adres, String Telefon, String Email, String Specjalizacja, String Status, Integer Wynagrodzenie) {
        this.ID = new SimpleIntegerProperty(ID);
        this.Login = new SimpleStringProperty(Login);
        this.Haslo = new SimpleStringProperty(Haslo);
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Miejscowosc = new SimpleStringProperty(Miejscowosc);
        this.Adres = new SimpleStringProperty(Adres);
        this.Telefon = new SimpleStringProperty(Telefon);
        this.Email = new SimpleStringProperty(Email);
        this.Specjalizacja = new SimpleStringProperty(Specjalizacja);
        this.Status = new SimpleStringProperty(Status);
        this.Wynagrodzenie = new SimpleIntegerProperty(Wynagrodzenie);

    }

    public Pracownik(Integer ID, String Imie, String Nazwisko, String Status) {
        this.ID = new SimpleIntegerProperty(ID);
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Status = new SimpleStringProperty(Status);
    }

    public Pracownik(int ID, String Imie, String Nazwisko) {
        this.ID = new SimpleIntegerProperty(ID);
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
    }

    public Integer getID() {
        return ID.get();
    }

    public String getLogin() {
        return Login.get();
    }

    public String getHaslo() {
        return Haslo.get();
    }

    public String getImie() {
        return Imie.get();
    }
    
    public void setImie(String Imie) {
        ImieProperty().set(Imie);
    }

    public String getNazwisko() {
        return Nazwisko.get();
    }
    
    public void setNazwisko(String Nazwisko) {
        NazwiskoProperty().set(Nazwisko);
    }

    public String getMiejscowosc() {
        return Miejscowosc.get();
    }

    public String getAdres() {
        return Adres.get();
    }

    public String getTelefon() {
        return Telefon.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public String getSpecjalizacja() {
        return Specjalizacja.get();
    }

    public String getStatus() {
        return Status.get();
    }

    public Integer getWynagrodzenie() {
        return Wynagrodzenie.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public StringProperty LoginProperty() {
        return Login;
    }

    public StringProperty HasloProperty() {
        return Haslo;
    }

    public StringProperty ImieProperty() {
        return Imie;
    }

    public StringProperty NazwiskoProperty() {
        return Nazwisko;
    }

    public StringProperty MiejscowoscProperty() {
        return Miejscowosc;
    }

    public StringProperty AdresProperty() {
        return Adres;
    }

    public StringProperty TelefonProperty() {
        return Telefon;
    }

    public StringProperty EmailProperty() {
        return Email;
    }

    public StringProperty SpecjalizacjaProperty() {
        return Specjalizacja;
    }

    public StringProperty StatusProperty() {
        return Status;
    }

    public IntegerProperty WynagrodzenieProperty() {
        return Wynagrodzenie;
    }

    @Override
    public String toString() {
        return Imie + " " + Nazwisko;
    }

}
