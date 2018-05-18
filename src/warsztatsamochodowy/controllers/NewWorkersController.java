/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class NewWorkersController implements Initializable {

    @FXML
    private Button b_zatwierdz;
    @FXML
    private Button b_powrot;
    @FXML
    private TextField tfImie;
    @FXML
    private TextField tfNazwisko;
    @FXML
    private TextField tfMiejscowosc;
    @FXML
    private TextField tfAdres;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfHaslo;
    @FXML
    private TextField tfTelefon;
    @FXML
    private TextField tfEmial;

    private Helper helper = new Helper();

    @FXML
    private ComboBox<String> cbSpecjalizacja;
    @FXML
    private ComboBox<String> cbStatus;

    /**
     * Initializes the controller class.
     */

    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    Connection sesja = PolaczenieDB.connectDatabase();
    @FXML
    private TextField tfWynagordzenie;

    public void initialize(URL url, ResourceBundle rb) {
        cbStatus.getItems().addAll(
                "Zatrudniony",
                "Urlop",
                "Zwolniony"
        );
        cbSpecjalizacja.getItems().addAll(
                "Diagnosta",
                "Mechanik",
                "Pomocnik",
                "Recepcjonista"
        );
    }

    /**
     * Tworzenie metody zatwierdzenia metody tworzenia do bazy
     *
     * @param event
     */
    @FXML
    private void ZatwierdzZm(ActionEvent event) {

        Statement stmt = null;

        try {

            stmt = sesja.createStatement();
            String wynagrodzenie = tfWynagordzenie.getText();
            int wyplata = Integer.parseInt(wynagrodzenie);

            String query = "INSERT INTO pracownik (ID, Login, Haslo, Imie, Nazwisko, Miejscowosc, Adres, Telefon, Email, Specjalizacja, Status,Wynagrodzenie) "
                    + "Values(NULL,'" + tfLogin.getText()
                    + "','" + tfHaslo.getText() + "','" + tfImie.getText()
                    + "','" + tfNazwisko.getText()
                    + "','" + tfMiejscowosc.getText()
                    + "','" + tfAdres.getText() + "','"
                    + tfTelefon.getText() + "','"
                    + tfEmial.getText() + "','"
                    + cbSpecjalizacja.getSelectionModel().getSelectedItem().toString()
                    + "','" + cbStatus.getSelectionModel().getSelectedItem().toString()
                    + "','" + wyplata
                    + "');";

            int wynik = stmt.executeUpdate(query);
            helper.message("Ustawienia zostały zapisane");
            tfLogin.clear();
            tfWynagordzenie.clear();
        } catch (Exception e) {
            helper.message("Sprawdź dane w formularzu");
        }

    }

    @FXML
    private void PowrotTab(ActionEvent event) throws IOException {

        try {

            sesja.close();
        } catch (Exception e) {
            helper.error(e.getMessage());
        }
        helper.powrotDoMenu();
        Stage settings = (Stage) b_powrot.getScene().getWindow();
        settings.close();

    }

}
