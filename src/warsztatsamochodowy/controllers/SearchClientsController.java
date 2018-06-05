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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;
import warsztatsamochodowy.database.entity.Klient;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class SearchClientsController implements Initializable {

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

    @FXML
    private TextField field_imie;
    @FXML
    private TextField field_nazwisko;
    @FXML
    private TextField field_tel;
    @FXML
    private TextField field_city;
    @FXML
    private TextField field_adres;
    @FXML
    private TextField field_email;
    @FXML
    private Button button_search;
    @FXML
    private Button button_close;

    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
    public static String Nazwa, ID, Cena, Producent, Ilosc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabelaKlienci.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colImie.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colMiejscowosc.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        colAdres.setCellValueFactory(new PropertyValueFactory("adres"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        //tabelaKlienci.setItems(FXCollections.observableArrayList(klienci));
        wczytajBaze();
    }

    private void dodajDoTabeli(Long id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String email) {

        Klient klient = new Klient(id, imie, nazwisko, nrTel, miejscowosc, adres, email);
        tabelaKlienci.getItems().add(klient);

    }

    private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM klient;");

            while (rs.next()) {
                String id = rs.getString("KlientId");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String nrTel = rs.getString("Telefon");
                String miejscowosc = rs.getString("Miejscowosc");
                String adres = rs.getString("Adres");
                String email = rs.getString("Email");
                dodajDoTabeli(Long.parseLong(id), imie, nazwisko, nrTel, miejscowosc, adres, email);
            }

        } catch (Exception e) {
            helper.error(e.getMessage());
        } finally {

            if (sesja != null) {
                try {
                    sesja.close();
                } catch (Exception e) {
                }
            }
        }

    }
    
    public void goBack(ActionEvent event) throws IOException{
        helper.sceneSwitcher("/warsztatsamochodowy/views/AddRepair.fxml", "Warsztat samochodowy - Dodaj naprawe");
        Stage this_scene = (Stage) button_close.getScene().getWindow();
        this_scene.close();
    }

}
