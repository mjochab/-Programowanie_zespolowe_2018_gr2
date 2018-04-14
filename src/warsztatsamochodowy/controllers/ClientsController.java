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
import warsztatsamochodowy.database.HibernateHelper;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Samochod;

/**
 * FXML Controller class
 *
 * @author Piotr Świder
 */
public class ClientsController implements Initializable {

    @FXML
    private Button powrot;
    @FXML
    private Button dodajKlienta;
    
    @FXML
    private TableView<Klient> tabelaKlienci;
    @FXML
    private TableColumn<Klient, String> colImie;
    @FXML
    private TableColumn<Klient, String> colNazwisko;
    @FXML
    private TableColumn<Klient, String> colNrTel;
    @FXML
    private TableColumn<Klient, String> colSamochod;
    
    private Helper helper = new Helper();
    
    private List<Klient> klienci;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //TODO usunąć po dodaniu samochodów
        HibernateHelper hibernateHelper = HibernateHelper.getInstance();
        if(hibernateHelper.getCars().isEmpty()) {
            hibernateHelper.addOrUpdateCar(new Samochod("Audi", "A4", "RZ123456"));
            hibernateHelper.addOrUpdateCar(new Samochod("Daewoo", "Tico", "RLA321654"));
        }
        klienci = HibernateHelper.getInstance().getKlienci();
        colImie.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colSamochod.setCellValueFactory(new PropertyValueFactory("samochod"));
        tabelaKlienci.setItems(FXCollections.observableArrayList(klienci));
    }    

    @FXML
    private void dodajKlienta(ActionEvent event) throws IOException {
         helper.sceneSwitcher("/warsztatsamochodowy/views/AddClient.fxml", "Warsztat samochodowy - Dodaj Klienta");
         Stage clients = (Stage) powrot.getScene().getWindow();
         clients.close();
    }

    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage clients = (Stage) powrot.getScene().getWindow();
        clients.close();
    }
    
}
