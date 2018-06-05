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
    private TableColumn<?, ?> colWorker;
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
        colWorker.setCellValueFactory(new PropertyValueFactory("pracownik"));
        colClient.setCellValueFactory(new PropertyValueFactory("klient"));
        //tabelaFix.setItems(FXCollections.observableArrayList(fix));
        wczytajBaze();
    }

    private void dodajDoTabeli(Long ID, String koszt, String status, String opis, Pracownik pracownik, Klient klient) {

        Repair repair = new Repair(ID, koszt, status, opis, pracownik, klient);
        tabelaFix.getItems().add(repair);

    }

    private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT n.napraw_id, n.koszt, n.status, n.opis, p.PracownikId, p.Imie, p.Nazwisko, " 
                    + "k.KlientId, k.Imie, k.Nazwisko " 
                    + "FROM naprawa as n " 
                    + "INNER JOIN pracownik p ON p.PracownikId = n.id_pracownika " 
                    + "INNER JOIN klient k ON k.KlientId = n.id_klienta;");

            while (rs.next()) {
                String ID = rs.getString("napraw_id");
                String koszt = rs.getString("koszt");
                String status = rs.getString("status");
                String opis = rs.getString("opis");
                //new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko"));
//                new Klient(rs.getLong("KlientId"), rs.getString("Imie"), rs.getString("Nazwisko"));
//                new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko"));
                dodajDoTabeli(Long.parseLong(ID), koszt, status, opis, 
                        new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko")),
                        new Klient(rs.getLong("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko")));
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
        helper.sceneSwitcher("/warsztatsamochodowy/views/AddRepair.fxml", "Warsztat samochodowy - Dodaj naprawe");
        Stage this_scene = (Stage) addTask.getScene().getWindow();
        this_scene.close();
    }

    @FXML
    private void editTask(ActionEvent event) throws IOException {
//                    helper.sceneSwitcher("/warsztatsamochodowy/views/AddPartsToRepair.fxml", "Warsztat samochodowy - Dodaj czesci");
//            Stage this_scene = (Stage) editTask.getScene().getWindow();
//            this_scene.hide();
    }

    @FXML
    private void deleteTask(ActionEvent event) {
    }

}
