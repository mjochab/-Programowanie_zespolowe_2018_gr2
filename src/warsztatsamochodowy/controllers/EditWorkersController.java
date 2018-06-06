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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class EditWorkersController implements Initializable {

    @FXML
    private TextField sb_imie;
    @FXML
    private TextField sb_nazwisko;
    @FXML
    private TextField sb_miejscowosc;
    @FXML
    private TextField sb_adres;
    @FXML
    private TextField sb_login;
    @FXML
    private TextField sb_telefon;
    @FXML
    private TextField sb_email;
    @FXML
    private PasswordField sb_haslo;
    @FXML
    private PasswordField sb_nowe_haslo;
    @FXML
    private Button b_zapisz;
    @FXML
    private Button b_powrot;

    /**
     * Initializes the controller class.
     */
    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();

    String aktualne_haslo = "";

    Connection sesja;

    private Helper helper = new Helper();
    Statement stmt;
    @FXML
    private ComboBox<String> cb_specjalizacja;
    @FXML
    private ComboBox<String> cb_status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cb_specjalizacja.getItems().addAll(
                "Diagnosta",
                "Mechanik",
                "Pomocnik",
                "Recepcjonista"
        );
          cb_status.getItems().addAll(
               "Zatrudniony",
                "Urlop",
                "Zwolniony"
        );
        try {
            sesja = PolaczenieDB.connectDatabase();
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("select * from pracownik where PracownikId = " + WorkersController.idPracownika);
            while (rs.next()) {

                sb_imie.setText(rs.getString("Imie"));
                sb_nazwisko.setText(rs.getString("Nazwisko"));
                sb_miejscowosc.setText(rs.getString("Miejscowosc"));
                sb_adres.setText(rs.getString("Adres"));
                sb_telefon.setText(rs.getString("Telefon"));
                sb_email.setText(rs.getString("Email"));
                
                sb_login.setText(rs.getString("Login"));
                aktualne_haslo = rs.getString("Haslo");
                break;
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
    private void Zapisz(ActionEvent event) {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            int poprawnosc = 1;
            Statement stmt = sesja.createStatement();

            String new_miejscowosc = sb_miejscowosc.getText();
            String new_imie = sb_imie.getText();
            String new_nazwisko = sb_nazwisko.getText();
            String new_adres = sb_adres.getText();
            String new_telefon = sb_telefon.getText();
            String new_email = sb_email.getText();
            String old_password = sb_haslo.getText();
            String new_password = sb_nowe_haslo.getText();
            String new_login = sb_login.getText();
            String new_specjalizacja = cb_specjalizacja.getSelectionModel().getSelectedItem().toString();
            String new_status = cb_status.getSelectionModel().getSelectedItem().toString();
            if (poprawnosc == 1 && old_password.equals(new_password)) {
                aktualne_haslo = new_password;
                int wynik = stmt.executeUpdate("update pracownik set Miejscowosc = '" + new_miejscowosc
                        + "', Imie='" + new_imie
                        + "', Nazwisko='" + new_nazwisko
                        + "', Adres = '" + new_adres
                        + "', Telefon = '" + new_telefon
                        + "', Email = '" + new_email
                        + "', Haslo = '" + new_password
                        + "', Login = '" + new_login
                        +"', Specjalizacja = '" + new_specjalizacja
                        +"', Status = '" + new_status
                        + "' where PracownikId = '" + WorkersController.idPracownika+"';");

                if (wynik == 1) {

                    helper.message("Ustawienia zostaly zapisane");
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
    private void Powrot(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Workers.fxml", "Warsztat samochodowy - Części");
        Stage this_scene = (Stage) b_powrot.getScene().getWindow();
        this_scene.close();
    }

}
