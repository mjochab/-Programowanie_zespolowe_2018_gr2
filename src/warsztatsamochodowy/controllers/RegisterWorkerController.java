/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class RegisterWorkerController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button registerWorker;

    
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
     
    Connection sesja;
    private Helper helper = new Helper();
    Statement stmt;
    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField specialization;
    @FXML
    private TextField specialization1;
    @FXML
    private Label status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void registerWorker(ActionEvent event) {
        String UserLogin = login.getText();
        String UserPassword = password.getText();
        String UserName = name.getText();
        String userLastName = this.lastName.getText();
        String userEmail = this.email.getText();
        String userAddress = this.address.getText();
        String userCity = this.city.getText();
        String userSpecialization = this.specialization.getText();
        String userPhone = this.phone.getText();
        String userStatus = this.status.getText();
        try{
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            Statement stmt = sesja.createStatement();
            
            int wynik = stmt.executeUpdate("INSERT INTO pracownik (Login,Haslo,Imie,Nazwisko,Email,Adres,Miejscowosc,Specjalizacja,Telefon,Status) VALUES ('"
                    + UserLogin
                    + "', '" + UserPassword
                    + "', '" + UserName
                    + "', '" + userLastName
                    + "', '" + userEmail
                    + "', '" + userAddress
                    + "', '" + userCity
                    + "', '" + userSpecialization
                    + "', '" + userPhone
                    + "', '" + userStatus
                    + "');");
            if (wynik == 1) {
                 login.clear();
                 password.clear();
            }
                helper.message("Pracownik został dodany");
        } catch(Exception e){
             helper.error(e.getMessage());
        }finally {

            if (sesja != null) {
                try {
                    sesja.close();
                    this.powrot();
                } catch (Exception e) {
                }
            }
    }
    }
    private void powrot() throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Login.fxml", "Warsztat samochodowy - Logowanie");
        Stage this_scene = (Stage) registerWorker.getScene().getWindow();
        this_scene.close();
    }
}
