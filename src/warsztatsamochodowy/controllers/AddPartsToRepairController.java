/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import warsztatsamochodowy.Helper;
import static warsztatsamochodowy.controllers.SearchClientsController.ID;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;
import warsztatsamochodowy.database.entity.Klient;

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
    private TableView<Czesc> tab_czesciDodane;

    @FXML
    private TableColumn<?, ?> kol_Nazwa1;

    @FXML
    private TableColumn<?, ?> kol_Producent1;

    @FXML
    private TableColumn<?, ?> kol_Ilosc1;

    @FXML
    private TableColumn<?, ?> kol_Cena1;

    @FXML
    private TextField field_nazwa;

    @FXML
    private TextField field_producent;

    @FXML
    private TextField field_cena;

    private Helper helper = new Helper();

    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
    public static String Nazwa, ID, Cena, Producent, Ilosc;

    AddRepairController addrepaircontroller = new AddRepairController();
    //int last_id = Integer.parseInt(addrepaircontroller.getLastID());
    int last_id = addrepaircontroller.getLastID();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab_czesci.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        kol_Nazwa.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        kol_Producent.setCellValueFactory(new PropertyValueFactory<>("Producent"));
        kol_Ilosc.setCellValueFactory(new PropertyValueFactory<>("Ilosc"));
        kol_Cena.setCellValueFactory(new PropertyValueFactory<>("Cena"));
        kol_Nazwa1.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        kol_Producent1.setCellValueFactory(new PropertyValueFactory<>("Producent"));
        kol_Ilosc1.setCellValueFactory(new PropertyValueFactory<>("Ilosc"));
        kol_Cena1.setCellValueFactory(new PropertyValueFactory<>("Cena"));
        wczytajBaze();
    }

    @FXML
    private void dodajCzesc(ActionEvent event) {
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = sesja.createStatement();
            }

            ObservableList<Czesc> czescZaznaczona;
            czescZaznaczona = tab_czesci.getSelectionModel().getSelectedItems();
            for (Czesc c : czescZaznaczona) {

                PreparedStatement ps = sesja.prepareStatement(
                        "INSERT INTO naprawa_czesci(id_czesci, id_naprawy) VALUES(?,?); ", Statement.RETURN_GENERATED_KEYS
                );
                String id = c.getID();
                ps.setInt(1, Integer.parseInt(id));
                ps.setInt(2, last_id);

                ps.execute();
                ps.close();
                
                helper.message("Dodano czesc");
                tab_czesciDodane.getItems().clear();
                wczytajBazeDodanych();

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
    private void szukajCzesci(ActionEvent event) {
        tab_czesci.getItems().clear();

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            String query = "SELECT * FROM Czesc WHERE ";
            if (field_nazwa.getText().isEmpty() == false) {
                query = query + "nazwa = '" + field_nazwa.getText() + "' AND ";
            }
            if (field_producent.getText().isEmpty() == false) {
                query = query + "producent = '" + field_producent.getText() + "' AND ";
            }
            if (field_cena.getText().isEmpty() == false) {
                query = query + "cena = '" + field_cena.getText() + "' AND ";
            }

            query = query + " CzescId IS NOT NULL";
            ResultSet rs = stmt.executeQuery(query);
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

    private void dodajDoTabeli(String id, String nazwa, String producent, String ilosc, String cena) {

        Czesc czesc = new Czesc(id, nazwa, producent, ilosc, cena);
        tab_czesci.getItems().add(czesc);

    }
    private void dodajDoTabeliDodanych(String id, String nazwa, String producent, String ilosc, String cena) {

        Czesc czesc = new Czesc(id, nazwa, producent, ilosc, cena);
        tab_czesciDodane.getItems().add(czesc);

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
    
    private void wczytajBazeDodanych() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM czesc as c INNER JOIN naprawa_czesci nc ON c.CzescID = nc.id_czesci WHERE nc.id_naprawy = "+last_id);

            while (rs.next()) {
                String id = rs.getString("CzescId");
                String nazwa = rs.getString("Nazwa");
                String producent = rs.getString("Producent");
                String ilosc = rs.getString("Ilosc");
                String cena = rs.getString("Cena");
                dodajDoTabeliDodanych(id, nazwa, producent, ilosc, cena);
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
