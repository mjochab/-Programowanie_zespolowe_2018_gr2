package warsztatsamochodowy.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.controllers.AddRepairController;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.PracownikRepair;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class AddWorkersToRepairController implements Initializable {

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
    private TextField field_spec;
    @FXML
    private Button button_search;
    @FXML
    private TableView<PracownikRepair> tabelaPracownicy;
    @FXML
    private TableColumn<?, ?> colImie;
    @FXML
    private TableColumn<?, ?> colNazwisko;
    @FXML
    private TableColumn<?, ?> colNrTel;
    @FXML
    private TableColumn<?, ?> colMiejscowosc;
    @FXML
    private TableColumn<?, ?> colAdres;
    @FXML
    private TableColumn<?, ?> colSpecjalizacja;
    @FXML
    private Button button_wybierz;
    @FXML
    private TableView<PracownikRepair> tabelaPracownicyDodani;
    @FXML
    private TableColumn<?, ?> colImie1;
    @FXML
    private TableColumn<?, ?> colNazwisko1;
    @FXML
    private TableColumn<?, ?> colNrTel1;
    @FXML
    private TableColumn<?, ?> colMiejscowosc1;
    @FXML
    private TableColumn<?, ?> colAdres1;
    @FXML
    private TableColumn<?, ?> colSpecjalizacja1;
    @FXML
    private Button button_dalej;

    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;

    AddRepairController addrepaircontroller = new AddRepairController();
    int last_id = addrepaircontroller.getLastID();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabelaPracownicy.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colImie.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colMiejscowosc.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        colAdres.setCellValueFactory(new PropertyValueFactory("adres"));
        colSpecjalizacja.setCellValueFactory(new PropertyValueFactory("specjalizacja"));
        colImie1.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko1.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel1.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colMiejscowosc1.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        colAdres1.setCellValueFactory(new PropertyValueFactory("adres"));
        colSpecjalizacja1.setCellValueFactory(new PropertyValueFactory("specjalizacja"));
        //tabelaKlienci.setItems(FXCollections.observableArrayList(klienci));
        wczytajBaze();
    }

    private void dodajDoTabeli(int id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String specjalizacja) {

        PracownikRepair pracownik = new PracownikRepair(id, imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
        tabelaPracownicy.getItems().add(pracownik);

    }

    private void dodajDoTabeliDodanych(int id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String specjalizacja) {

        PracownikRepair pracownik = new PracownikRepair(id, imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
        tabelaPracownicyDodani.getItems().add(pracownik);

    }

    private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownik;");

            while (rs.next()) {
                String id = rs.getString("PracownikId");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String nrTel = rs.getString("Telefon");
                String miejscowosc = rs.getString("Miejscowosc");
                String adres = rs.getString("Adres");
                String specjalizacja = rs.getString("Specjalizacja");
                dodajDoTabeli(Integer.parseInt(id), imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
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

            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownik as p "
                    + "INNER JOIN naprawa_pracownika np ON p.PracownikId = np.id_pracownika WHERE np.id_naprawy = " + last_id);

            while (rs.next()) {
                String id = rs.getString("PracownikId");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String nrTel = rs.getString("Telefon");
                String miejscowosc = rs.getString("Miejscowosc");
                String adres = rs.getString("Adres");
                String specjalizacja = rs.getString("Specjalizacja");
                dodajDoTabeliDodanych(Integer.parseInt(id), imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
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
    private void searchWorker(ActionEvent event) {
        tabelaPracownicy.getItems().clear();

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            String query = "SELECT * FROM pracownik WHERE ";
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
            if (field_spec.getText().isEmpty() == false) {
                query = query + "specjalizacja = '" + field_spec.getText().toString() + "' AND ";
            }

            query = query + " PracownikId IS NOT NULL";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("PracownikId");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String nrTel = rs.getString("Telefon");
                String miejscowosc = rs.getString("Miejscowosc");
                String adres = rs.getString("Adres");
                String specjalizacja = rs.getString("Specjalizacja");
                dodajDoTabeli(Integer.parseInt(id), imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
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
    private void selectWorker(ActionEvent event) {
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = sesja.createStatement();
            }

            ObservableList<PracownikRepair> pracownikZaznaczony;
            pracownikZaznaczony = tabelaPracownicy.getSelectionModel().getSelectedItems();
            for (PracownikRepair c : pracownikZaznaczony) {

                PreparedStatement ps = sesja.prepareStatement(
                        "INSERT INTO naprawa_pracownika(id_pracownika, id_naprawy) VALUES(?,?); ", Statement.RETURN_GENERATED_KEYS
                );
                int id = c.getId();
                ps.setInt(1, id);
                ps.setInt(2, last_id);

                ps.execute();
                ps.close();

                helper.message("Dodano pracownika");
                tabelaPracownicyDodani.getItems().clear();
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
    private void goToRepair(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Tasks.fxml", "Warsztat samochodowy - Zlecenia");
        Stage this_scene = (Stage) button_dalej.getScene().getWindow();
        this_scene.hide();
    }

}
