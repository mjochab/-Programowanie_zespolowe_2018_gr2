/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Artur
 */

public class NewWorkersController implements Initializable {

    @FXML
    private Button b_zatwierdz;
    @FXML
    private Button b_powrot;
    @FXML
    private TextField tfImie;
    @FXML
    private TextField tfNazwisko;
    @FXML
    private TextField tfMiejscowosc;
    @FXML
    private TextField tfAdres;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfHaslo;
    @FXML
    private TextField tfTelefon;
    @FXML
    private TextField tfEmial;

     private Helper helper = new Helper();
    /**
     * Initializes the controller class.
     */
    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja = PolaczenieDB.connectDatabase();
    @FXML
    private ComboBox<?> comboSpecjalizacja;
    @FXML
    private RadioButton RadioMezczyzna;
    @FXML
    private RadioButton RadioKobieta;
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    
    /**
     * Tworzneie metody dodawania pracownika do Bazy
     * @param event 
     */
    @FXML
    private void ZatwierdzZm(ActionEvent event) {
        
        try {
             Statement stmt = sesja.createStatement();
             String new_imie = tfImie.getText();
             String new_nazwisko = tfNazwisko.getText();
             String new_adres = tfAdres.getText();
             String new_miejscowosc = tfMiejscowosc.getText();
             String new_email = tfEmial.getText();
             String new_telefon = tfTelefon.getText();
             String specjalizacja = comboSpecjalizacja.getSelectionModel().getSelectedItem().toString();
             String new_login = tfLogin.getText();
             String new_haslo = tfHaslo.getText();
             
             
             
             
       /* int rs = stmt.executeUpdate("INSERT INTO Pracownik(imie_p,nazwisko_p,specjalizacja,login,haslo) Values "
                + "('" + textfield8.getText() + "','" + textfield9.getText() + "','" + "10 000$" + "','" + textfield10.getText() + "','" + 13 + "','" + 212 +"','" + 105 +  "')");

        */
    } 
         catch (SQLException e) {
        System.err.println("ERROR"+e);

    }
        
        
    }
    /**
     * Powrot do menu
     * @param event
     * @throws IOException 
     */

    @FXML
    private void PowrotTab(ActionEvent event) throws IOException {
        
           helper.sceneSwitcher("/warsztatsamochodowy/views/Workers.fxml", "Warsztat samochodowy - Pracownicy");
            Stage mainmenu_scene = (Stage) b_powrot.getScene().getWindow();
            mainmenu_scene.close();
        
    }
    
}
