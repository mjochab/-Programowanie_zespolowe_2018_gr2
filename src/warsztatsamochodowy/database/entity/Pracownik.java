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

    private final IntegerProperty ID;
    private final StringProperty Login;
    private final StringProperty Haslo;
    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty Miejscowosc;
    private final StringProperty Adres;
    private final StringProperty Telefon;
    private final StringProperty Email;
    private final StringProperty Specjalizacja;
    private final StringProperty Status;
    private final IntegerProperty Wynagrodzenie;

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
        return Email;
    }
          public IntegerProperty WynagrodzenieProperty() {
        return Wynagrodzenie;
    }
    
    
}
