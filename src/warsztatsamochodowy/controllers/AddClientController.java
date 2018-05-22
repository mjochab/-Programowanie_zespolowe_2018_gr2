/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.lang3.StringUtils;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.HibernateHelper;
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
    private ComboBox<Samochod> cbxSamochod;
    @FXML
    private Button b_zapisz;
    @FXML
    private Button b_powrot;

    

    private Helper helper = new Helper();
    /**
    /**
     * Initializes the controller class.
     */
    /**
     * Metoda inicjalizująca dodawnie noych klientów do bazy danych
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxSamochod.setConverter(new StringConverter<Samochod>() {
            @Override
            public String toString(Samochod object) {
                return object.toString();
            }

            @Override
            public Samochod fromString(String string) {
                return null;
            }
        });
        List<Samochod> samochody = HibernateHelper.getInstance().getCars();
        cbxSamochod.setItems(FXCollections.observableArrayList(samochody));
        HibernateHelper.getInstance().getCars();
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
        Samochod samochod = cbxSamochod.getSelectionModel().getSelectedItem();
        
        if(StringUtils.isNotBlank(imie) && StringUtils.isNotBlank(nazwisko) 
                && StringUtils.isNotBlank(nrTel) && samochod != null) {
            Klient k = new Klient(imie, nazwisko, nrTel, samochod);
            HibernateHelper.getInstance().addOrUpdateKlient(k);
        } else {
            return;
        }
        powrotDoKlientow(event);
    }
    
    /**
     * Metoda obslugujaca przycisk powrotu do modulu Klienci z modulu dodawania klientow
     */
    
    @FXML
    private void powrotDoKlientow(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Clients.fxml", "Warsztat samochodowy - Klienci");
        Stage addNewClient = (Stage) b_powrot.getScene().getWindow();
        addNewClient.close();
    }
}
