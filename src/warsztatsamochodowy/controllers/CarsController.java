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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.entity.Samochod;

/**
 * FXML Controller class
 *
 * @author Piotr Świder
 */
public class CarsController implements Initializable {

    @FXML
    private Button powrot;
    
    @FXML
    private TableView<Samochod> tabelaCars;
    @FXML
    private TableColumn<Samochod, String> colVin;
    @FXML
    private TableColumn<Samochod, String> colProducent;
    @FXML
    private TableColumn<Samochod, String> colModel;
    @FXML
    private TableColumn<Samochod, String> colTyp;
    @FXML
    private TableColumn<Samochod, String> colKlient;
    
    private Helper helper = new Helper();
    
    private List<Samochod> cars;
    /**
     * Initializes the controller class.
     */
    /*
    Moduł Klienci
    */
    @Override
    /**
     * Modul Klienci
     */
    public void initialize(URL url, ResourceBundle rb) {
        cars = DBHelper.getInstance().getCars();
        colVin.setCellValueFactory(new PropertyValueFactory("vin"));
        colProducent.setCellValueFactory(new PropertyValueFactory("producent"));
        colModel.setCellValueFactory(new PropertyValueFactory("model"));
        colTyp.setCellValueFactory(new PropertyValueFactory("typ"));
        colKlient.setCellValueFactory(new PropertyValueFactory("klient"));
        tabelaCars.setItems(FXCollections.observableArrayList(cars));
    }    
    /**
     * Obsługa przycisku otwierającego okno dodawania nowych klientów
     * 
     * @param event
     * @throws IOException 
     */

    
    /**
     * Medota obslugujaca przycisk powrotu do panelu glownego programu
     * @param event
     * @throws IOException 
     */

    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage cars = (Stage) powrot.getScene().getWindow();
        cars.close();
    }
    
}
