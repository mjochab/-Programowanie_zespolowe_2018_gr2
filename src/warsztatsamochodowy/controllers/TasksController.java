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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Pracownik;
import warsztatsamochodowy.database.entity.Repair;
import warsztatsamochodowy.database.entity.Samochod;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class TasksController implements Initializable {

    @FXML
    private TableView<Repair> tabelaFix;
    @FXML
    private TableColumn<Repair, String> colWorker;
    @FXML
    private TableColumn<?, ?> colClient;
    @FXML
    private TableColumn<?, ?> colCar;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableColumn<?, ?> colAbout;
    @FXML
    private Button back;
    @FXML
    private Button addTask;
    @FXML
    private Button editTask;
    @FXML
    private Button deleteTask;

    public static Long Repair_ID;

    public Long getRepair_ID() {
        return Repair_ID;
    }

    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabelaFix.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colPrice.setCellValueFactory(new PropertyValueFactory("koszt"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colAbout.setCellValueFactory(new PropertyValueFactory("opis"));
        colClient.setCellValueFactory(new PropertyValueFactory("klient"));
        //tabelaFix.setItems(FXCollections.observableArrayList(fix));
        wczytajBaze();
    }

    private void dodajDoTabeli(Long ID, String koszt, String status, String opis, Klient klient) {

        Repair repair = new Repair(ID, koszt, status, opis, klient);
        tabelaFix.getItems().add(repair);

    }

    private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT n.napraw_id, n.koszt, n.status, n.opis, "
                    + "k.KlientId, k.Imie, k.Nazwisko "
                    + "FROM naprawa as n "
                    + "INNER JOIN klient k ON k.KlientId = n.id_klienta;");

            while (rs.next()) {
                String ID = rs.getString("napraw_id");
                String koszt = rs.getString("koszt");
                String status = rs.getString("status");
                String opis = rs.getString("opis");

                dodajDoTabeli(Long.parseLong(ID), koszt, status, opis,
                        new Klient(rs.getLong("KlientId"), rs.getString("Imie"), rs.getString("Nazwisko")));
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
    private void back(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage settings = (Stage) back.getScene().getWindow();
        settings.close();
    }

    @FXML
    private void addTask(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/SearchClient.fxml", "Warsztat samochodowy - Wybierz klienta");
        Stage this_scene = (Stage) addTask.getScene().getWindow();
        this_scene.close();
    }


    @FXML
    private void deleteTask(ActionEvent event) {
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = sesja.createStatement();
            }

            ObservableList<Repair> naprawaZaznaczona;
            ObservableList<Repair> doUsuniecia = FXCollections.observableArrayList();
            naprawaZaznaczona = tabelaFix.getSelectionModel().getSelectedItems();
            for (Repair c : naprawaZaznaczona) {

                Long id = c.getID();

                int wynik = stmt.executeUpdate("DELETE FROM naprawa WHERE napraw_id = " + id + ";");
                if (wynik == 1) {

                    doUsuniecia.add(c);
                }

            }

            usunzTabeli(doUsuniecia);
            tabelaFix.getSelectionModel().clearSelection();

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

    private void usunzTabeli(ObservableList<Repair> zaznaczoneNaprawy) {

        ObservableList<Repair> wszystkieNaprawy = tabelaFix.getItems();
        wszystkieNaprawy.removeAll(zaznaczoneNaprawy);
        // wszystkieCzesci.removeAll(czescZaznaczona);

    }
    public static String Imie, Nazwisko, Naprawa_ID, Data_zakonczenia, Koszt, Status, Opis, Edit;
    @FXML
    public void editTask(ActionEvent event){
        if (tabelaFix.getSelectionModel().getSelectedItems().size() == 1) {

            try {
                Repair c = tabelaFix.getSelectionModel().getSelectedItem();
                if (sesja == null || sesja.isClosed()) {
                    sesja = PolaczenieDB.connectDatabase();
                }
                stmt = sesja.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM klient INNER JOIN naprawa ON naprawa.id_klienta = klient.KlientId WHERE naprawa.napraw_id = " + c.getID());

                while (rs.next()) {
                    Naprawa_ID = rs.getString("napraw_id");
                    Imie = rs.getString("Imie");
                    Nazwisko = rs.getString("Nazwisko");
                    Data_zakonczenia = rs.getString("data_zakonczenia");
                    Koszt = rs.getString("koszt");
                    Status = rs.getString("status");
                    Opis = rs.getString("opis");
                }
                helper.sceneSwitcher("/warsztatsamochodowy/views/TaskDetail.fxml", "Warsztat samochodowy - Szczegoly zmowienia");
                Stage this_scene = (Stage) tabelaFix.getScene().getWindow();
                this_scene.close();

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

        } else {
            helper.error("Wybierz 1 zlecenie");
        }
    }

    public String getRepairID() {
        return Naprawa_ID;
    }

    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public String getData_zakonczenia() {
        return Data_zakonczenia;
    }

    public String getKoszt() {
        return Koszt;
    }

    public String getStatus() {
        return Status;
    }

    public String getOpis() {
        return Opis;
    }

}
