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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import static warsztatsamochodowy.controllers.LoginController.ID;
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
    private Label test;
    
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
    private Button button_close;
    
    public static String ID;
    @FXML
    private Button button_wybierz;
    
    public String getID() {
        return ID;
    }
    
    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
    //public static String Nazwa, ID, Cena, Producent, Ilosc;

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
    
    @FXML
    public void selectClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/warsztatsamochodowy/views/AddRepair.fxml"));
        Parent root = loader.load();
        AddRepairController klient = (AddRepairController)loader.getController();
        
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = sesja.createStatement();
            }
            
            ObservableList<Klient> klientZaznaczony;
            klientZaznaczony = tabelaKlienci.getSelectionModel().getSelectedItems();
            for (Klient c : klientZaznaczony) {
                
                ID = c.getId().toString();
                
                ResultSet rs = stmt.executeQuery("Select Imie, Nazwisko FROM Klient WHERE KlientId = " + ID + ";");
                while (rs.next()) {
                    klient.setName(rs.getString("Imie")+" "+rs.getString("Nazwisko"));
                    button_wybierz.getScene().setRoot(root);
                }
                
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
    
    @FXML
    public void searchClient(ActionEvent event) throws IOException {
        tabelaKlienci.getItems().clear();
        
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            String query = "SELECT * FROM klient WHERE ";
            if (field_imie.getText().isEmpty() == false) {
                query = query + "imie = '" + field_imie.getText().toString() + "' AND ";
            }
            if (field_nazwisko.getText().isEmpty() == false) {
                query = query + "nazwisko = '" + field_nazwisko.getText().toString() + "' AND ";
            }
            if (field_tel.getText().isEmpty() == false) {
                query = query + "telefon = '" + field_tel.getText().toString() + "' AND ";
            }
            if (field_city.getText().isEmpty() == false) {
                query = query + "Miejscowosc = '" + field_city.getText().toString() + "' AND ";
            }
            if (field_adres.getText().isEmpty() == false) {
                query = query + "Adres = '" + field_adres.getText().toString() + "' AND ";
            }
            if (field_email.getText().isEmpty() == false) {
                query = query + "email = '" + field_email.getText().toString() + "' AND ";
            }
            
            query = query + " KlientId IS NOT NULL";
            ResultSet rs = stmt.executeQuery(query);
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
    
    public void goBack(ActionEvent event) throws IOException {
        Stage this_scene = (Stage) button_close.getScene().getWindow();
        this_scene.close();
    }
    
}
