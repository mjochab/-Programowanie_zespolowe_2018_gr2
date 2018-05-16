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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class PartsController implements Initializable {

    @FXML
    private TextField Tf_nazwa;
    @FXML
    private TextField Tf_producent;
    @FXML
    private TextField Tf_cena;
    @FXML
    private Button b_zatwierdz;
    @FXML
    private Button b_powrot;

    

    private Helper helper = new Helper();
    @FXML
    private TableView<Config> tab_czesci;
    /**
    /**
     * Polaczenie z baza
     * Tworzenie zmienncyh 
     */
    
     DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja = PolaczenieDB.connectDatabase();
    @FXML
    private TableColumn<Config, String> columNazwa;
    @FXML
    private TableColumn<Config, String> columnProducent;
    @FXML
    private TableColumn<Config, String> columnCena;

   private ObservableList<Config> data;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Statement stmt = null;
                data = FXCollections.observableArrayList();
       try{
           stmt = sesja.createStatement();
           String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) "
                    + "VALUES (1,'Filtr powietrza','Bosh','70');";
       
      
             ResultSet rs = stmt.executeQuery("select nazwa_cz,producent_cz,cena_cz from czesci;");
            
             
             
             
             while (rs.next()) {
                
                columNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_cz"));
                columnProducent.setCellValueFactory(new PropertyValueFactory<>("producent_cz"));
                columnCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
            }
             // tab_czesci.setItems();
            //    tab_czesci.setItems();
           
       }
       
       
       catch (Exception e) {
            helper.error(e.getMessage());
        }
    
 
    
    }
    /**
     * Tworzenie metody przycisku powrot
     * @param event
     * @throws IOException 
     */
    @FXML
    private void powrtoDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage settings = (Stage) b_powrot.getScene().getWindow();
        settings.close();
    }
    /**
     * Tworzenie metody dodawnaia do przycisku AddCzesci
     * @param event
     * @throws SQLException 
     */

    @FXML
    private void AddCzesci(ActionEvent event) throws SQLException {
           Statement stmt =null;
           
        try{
            stmt = sesja.createStatement();
        
            String new_nazwa = Tf_nazwa.getText();
            String new_producent= Tf_producent.getText();
            int new_cena = Integer.parseInt(Tf_cena.getText());
          String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) VALUES (NULL,'"+new_nazwa+"','"+new_producent+"','"+new_cena+"')";
            
            //String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) VALUES ('1',"+new_nazwa+"','"+new_producent+"','"+new_cena+"')";
            int wynik = stmt.executeUpdate(query);
            helper.message("poszlo"+new_nazwa);
        } catch (Exception e) {
            // helper.error(e.getMessage());
        }
                
            
    }
    
}
