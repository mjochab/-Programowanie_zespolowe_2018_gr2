/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * Klasa kontrolera FXML Pracownicy
 *
 * @author lukasz
 */
public class WorkersController implements Initializable {

    @FXML
    private Button powrot;
    @FXML
    private Button dodajPracownika;
    
    private Helper helper = new Helper();
    @FXML
    private TableColumn<?, ?> StatusColumn;
    @FXML
    private TableColumn<?, ?> ImieColumn;
    @FXML
    private TableColumn<?, ?> NazwiskoColumn;
    @FXML
    private TableColumn<?, ?> TelColumn;
    @FXML
    private TableColumn<?, ?> SpecjalizacjaColumn;
    /**
     * Initializes the controller class.
     */
    
       DatabaseConnection PolaczenieDB = new DatabaseConnection();



    Connection sesja = PolaczenieDB.connectDatabase();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        Statement stmt = null;

        try {

            stmt = sesja.createStatement();
               ResultSet rs = stmt.executeQuery("select * from pracownik;");
            while (rs.next()) {

                ImieColumn.setText(rs.getString("Imie"));
                NazwiskoColumn.setText(rs.getString("Nazwisko"));
                TelColumn.setText(rs.getString("Telefon"));
                SpecjalizacjaColumn.setText(rs.getString("Specjalizacja"));
                StatusColumn.setText(rs.getString("Status"));
                
                
                
            }
            
            
            
            
        
        }catch(Exception e){
            
        }
        
    }    

    @FXML
    private void dodajPracownika(ActionEvent event) throws IOException {

   
        
            helper.sceneSwitcher("/warsztatsamochodowy/views/NewWorkers.fxml", "Warsztat samochodowy - Pracownicy");
            Stage mainmenu_scene = (Stage) dodajPracownika.getScene().getWindow();
            mainmenu_scene.close();
        
    }
        
   
    

    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage workers = (Stage) powrot.getScene().getWindow();
        workers.close();
    }
    
}
