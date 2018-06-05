/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Samochod;

/**
 * FXML Controller class
 *
 * @author Piotr Świder
 */


public class AddClientController implements Initializable {

    @FXML
    private TextField tfImie;
    @FXML
    private TextField tfNazwisko;
    @FXML
    private TextField tfNrTel;
    @FXML
    private TextField tfMiejscowosc;
    @FXML
    private TextField tfAdres;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfProducent;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfTyp;
    @FXML
    private Button b_zapisz;
    @FXML
    private Button b_powrot;

    

    private Helper helper = new Helper();
    
    private Klient klientToEdit;
    /**
    /**
     * Initializes the controller class.
     * 
     * Okno dodawania nowych klientów do bazy danych
     */
    /**
     * Metoda inicjalizująca dodawnie noych klientów do bazy danych
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    /**
     * Metoda zapisująca wprowadzone dane o nowym kliencie do bazy danych
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onSave(ActionEvent event) throws IOException {
        String imie = tfImie.getText();
        String nazwisko = tfNazwisko.getText();
        String nrTel = tfNrTel.getText();
        String miejscowosc = tfMiejscowosc.getText();
        String adres = tfAdres.getText();
        String email = tfEmail.getText();
        String vin = tfVin.getText();
        String producent = tfProducent.getText();
        String model = tfModel.getText();
        String typ = tfTyp.getText();
        
        Samochod samochod = null;
          if(StringUtils.isNotBlank(vin) && StringUtils.isNotBlank(producent) 
                && StringUtils.isNotBlank(model) &&  StringUtils.isNotBlank(typ)) {
            samochod = new Samochod(klientToEdit != null ? klientToEdit.getSamochod().getId() : null, vin, producent, model, typ);
        } 
        
        if(StringUtils.isBlank(imie)) {
            helper.error("Imie jest wymagane!");
        }
        
        if(StringUtils.isBlank(nazwisko)) {
            helper.error("Nazwisko jest wymagane!");
        }
        
        if(StringUtils.isBlank(nrTel)) {
            helper.error("nr telefonu jest wymagany!");
        }
        
        if(samochod == null) {
            helper.error("Dane samochodu są wymagane!");
        }
        
        if(StringUtils.isNotBlank(imie) && StringUtils.isNotBlank(nazwisko) 
                && StringUtils.isNotBlank(nrTel) && samochod != null) {
            Klient k = new Klient(imie, nazwisko, nrTel, miejscowosc, adres, email, samochod);
            if(klientToEdit != null) {
                k.setId(klientToEdit.getId());
            }
            DBHelper.getInstance().addOrUpdateKlient(k);
            DBHelper.getInstance().addOrUpdateNaprawa(k);
        } else {
            return;
        }
        powrotDoKlientow(event);
    }
    /**
     * Obsługa przycisku powracania do modułu Klienci 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void powrotDoKlientow(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Clients.fxml", "Warsztat samochodowy - Klienci");
        Stage addNewClient = (Stage) b_powrot.getScene().getWindow();
        addNewClient.close();
    }

    public void setKlientToEdit(Klient klientToEdit) {
        this.klientToEdit = klientToEdit;
        if(this.klientToEdit != null) {
           tfImie.setText(this.klientToEdit.getImie());
            tfNazwisko.setText(this.klientToEdit.getNazwisko());
            tfNrTel.setText(this.klientToEdit.getNrTel());
            tfMiejscowosc.setText(this.klientToEdit.getMiejscowosc());
            tfAdres.setText(this.klientToEdit.getAdres());
            tfEmail.setText(this.klientToEdit.getEmail());
            tfVin.setText(this.klientToEdit.getSamochod().getVin());
            tfProducent.setText(this.klientToEdit.getSamochod().getProducent());
            tfModel.setText(this.klientToEdit.getSamochod().getModel());
            tfTyp.setText(this.klientToEdit.getSamochod().getTyp());
       }
    }
}
