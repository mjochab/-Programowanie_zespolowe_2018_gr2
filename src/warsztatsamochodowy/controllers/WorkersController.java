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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Potwierdzenie;
import warsztatsamochodowy.database.entity.Pracownik;

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
    private TableColumn<Pracownik, String> StatusColumn;
    @FXML
    private TableColumn<Pracownik, String> ImieColumn;
    @FXML
    private TableColumn<Pracownik, String> NazwiskoColumn;
    @FXML
    private TableColumn<Pracownik, String> TelColumn;
    @FXML
    private TableColumn<Pracownik, String> SpecjalizacjaColumn;

    private ObservableList<Pracownik> data;
    /**
     * Initializes the controller class.
     */

    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    Connection sesja = PolaczenieDB.connectDatabase();
    @FXML
    private TableView<Pracownik> tablepracownik;
    @FXML
    private Button usunPracownika;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
ladujTabelePracownick();
    

    }
    public void ladujTabelePracownick(){
          data = FXCollections.observableArrayList();
        Statement stmt = null;

        try {

            stmt = sesja.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pracownik;");
            while (rs.next()) {
                data.add(new Pracownik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));

            }

            ImieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
            NazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
            TelColumn.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
            SpecjalizacjaColumn.setCellValueFactory(new PropertyValueFactory<>("Specjalizacja"));
            StatusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));

            tablepracownik.setItems(null);
            tablepracownik.setItems(data);

        } catch (Exception e) {

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

    @FXML
    private void usunPracownika(ActionEvent event) {

        int id_placowki = tablepracownik.getSelectionModel().getSelectedItem().getID();

        System.out.println(id_placowki);
        Statement stmt = null;
          boolean flaga=Potwierdzenie.display("Usuń", "Czy napewno chcesz usunać?");
          if(flaga== true){
        try {
            stmt = sesja.createStatement();

         int rs = stmt.executeUpdate("delete from Pracownik where ID = " + tablepracownik.getSelectionModel().getSelectedItem().getID());
       
         helper.message("Pracownik zostal usuniety");
              ladujTabelePracownick();
              
              
        } catch (SQLException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
          
    }

}
