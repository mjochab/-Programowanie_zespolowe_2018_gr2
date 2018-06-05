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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.entity.Klient;

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
    private Button edytujKlienta;
    @FXML
    private Button usunKlienta;
    
    @FXML
    private TableView<Klient> tabelaKlienci;
    @FXML
    private TableColumn<Klient, String> colImie;
    @FXML
    private TableColumn<Klient, String> colNazwisko;
    @FXML
    private TableColumn<Klient, String> colNrTel;
    @FXML
    private TableColumn<Klient, String> colMiejscowosc;
    @FXML
    private TableColumn<Klient, String> colAdres;
    @FXML
    private TableColumn<Klient, String> colEmail;
    @FXML
    private TableColumn<Klient, String> colSamochod;
    
    private Helper helper = new Helper();
    
    private List<Klient> klienci;
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
        klienci = DBHelper.getInstance().getKlienci();
        colImie.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colMiejscowosc.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        colAdres.setCellValueFactory(new PropertyValueFactory("adres"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        //colSamochod.setCellValueFactory(new PropertyValueFactory("samochod"));
        tabelaKlienci.setItems(FXCollections.observableArrayList(klienci));
        tabelaKlienci.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
    /**
     * Obsługa przycisku otwierającego okno dodawania nowych klientów
     * 
     * @param event
     * @throws IOException 
     */

    
    @FXML
    private void dodajKlienta(ActionEvent event) throws IOException {
         helper.sceneSwitcher("/warsztatsamochodowy/views/AddClient.fxml", "Warsztat samochodowy - Dodaj Klienta");
         Stage clients = (Stage) powrot.getScene().getWindow();
         clients.close();
    }
    
    @FXML
    private void usunKlienta(ActionEvent event) throws IOException {
        if(tabelaKlienci.getSelectionModel().getSelectedItem() != null) {
            if(DBHelper.getInstance().checkKlientAndCarHaveDependency(tabelaKlienci.getSelectionModel().getSelectedItem())) {
                helper.message("Nie można usunąc klienta - posiada zależności w naprawach");
            } else {
                DBHelper.getInstance().deleteCar(tabelaKlienci.getSelectionModel().getSelectedItem().getSamochod().getId());
                DBHelper.getInstance().deleteKlient(tabelaKlienci.getSelectionModel().getSelectedItem().getId());
                tabelaKlienci.getSelectionModel().clearSelection();
                klienci = DBHelper.getInstance().getKlienci();
                tabelaKlienci.setItems(FXCollections.observableArrayList(klienci));
            }
        } else {
             helper.message("Zaznacz Klienta!");
        }
    }

    @FXML
    private void edytujKlienta(ActionEvent event) throws IOException {
        if (tabelaKlienci.getSelectionModel().getSelectedItem() != null) {
            openEditScene(tabelaKlienci.getSelectionModel().getSelectedItem());
            Stage clients = (Stage) powrot.getScene().getWindow();
            clients.close();
            tabelaKlienci.getSelectionModel().clearSelection();
        } else {
            helper.message("Zaznacz Klienta!");
        }
    }
    /**
     * Medota obslugujaca przycisk powrotu do panelu glownego programu
     * @param event
     * @throws IOException 
     */

    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage clients = (Stage) powrot.getScene().getWindow();
        clients.close();
    }
    
    private void openEditScene(Klient klient) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/warsztatsamochodowy/views/AddClient.fxml"));
        Stage settings_scene = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        AddClientController editController = (AddClientController) fxmlLoader.getController();
        editController.setKlientToEdit(klient);
        settings_scene.setScene(new Scene(root));
        settings_scene.setTitle("Warsztat samochodowy - Edytuj Klienta");
        settings_scene.show();
    }
    
}
