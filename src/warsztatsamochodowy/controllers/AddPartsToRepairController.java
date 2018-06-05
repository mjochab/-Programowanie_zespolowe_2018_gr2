/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class AddPartsToRepairController implements Initializable {

    @FXML
    private Button b_dodaj;
    @FXML
    private TableView<Czesc> tab_czesci;

    @FXML
    private TableColumn<Czesc, String> kol_Nazwa;

    @FXML
    private TableColumn<Czesc, String> kol_Producent;

    @FXML
    private TableColumn<Czesc, String> kol_Ilosc;

    @FXML
    private TableColumn<Czesc, String> kol_Cena;
    @FXML
    private TableView<?> tab_czesci1;
    @FXML
    private TableColumn<?, ?> kol_Nazwa1;
    @FXML
    private TableColumn<?, ?> kol_Producent1;
    @FXML
    private TableColumn<?, ?> kol_Ilosc1;
    @FXML
    private TableColumn<?, ?> kol_Cena1;

    private Helper helper = new Helper();

    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
    public static String Nazwa, ID, Cena, Producent, Ilosc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab_czesci.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        kol_Nazwa.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        kol_Producent.setCellValueFactory(new PropertyValueFactory<>("Producent"));
        kol_Ilosc.setCellValueFactory(new PropertyValueFactory<>("Ilosc"));
        kol_Cena.setCellValueFactory(new PropertyValueFactory<>("Cena"));
        wczytajBaze();
    }    

    @FXML
    private void dodajCzesc(ActionEvent event) {
    }
    
        private void dodajDoTabeli(String id, String nazwa, String producent, String ilosc, String cena) {

        Czesc czesc = new Czesc(id, nazwa, producent, ilosc, cena);
        tab_czesci.getItems().add(czesc);

    }

    private void usunzTabeli(ObservableList<Czesc> zaznaczoneCzesci) {

        ObservableList<Czesc> wszystkieCzesci = tab_czesci.getItems();
        wszystkieCzesci.removeAll(zaznaczoneCzesci);
        // wszystkieCzesci.removeAll(czescZaznaczona);

    }

    private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Czesc;");

            while (rs.next()) {
                String id = rs.getString("CzescId");
                String nazwa = rs.getString("Nazwa");
                String producent = rs.getString("Producent");
                String ilosc = rs.getString("Ilosc");
                String cena = rs.getString("Cena");
                dodajDoTabeli(id, nazwa, producent, ilosc, cena);
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
    
}
