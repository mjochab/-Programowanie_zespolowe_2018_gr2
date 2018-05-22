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
        
        try{
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            Statement stmt = sesja.createStatement();
            
            int wynik = stmt.executeUpdate("INSERT INTO pracownik (Login,Haslo) VALUES ('"
                    + UserLogin
                    + "', '" + UserPassword
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
                } catch (Exception e) {
                }
            }
    }
    }
   @FXML
    private void powrot(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Login.fxml", "Warsztat samochodowy - Logowanie");
        Stage this_scene = (Stage) registerWorker.getScene().getWindow();
        this_scene.close();
    }
}
