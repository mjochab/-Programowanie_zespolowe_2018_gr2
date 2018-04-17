/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author lukasz, sebastian
 */
import java.sql.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserSettingsController implements Initializable {

    @FXML
    private Button powrot;

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
    private PasswordField sb_haslo;

    @FXML
    private PasswordField sb_nowe_haslo;

    @FXML
    private TextField sb_telefon;

    @FXML
    private TextField sb_email;

    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja = PolaczenieDB.connectDatabase();

    @FXML
    private Helper helper = new Helper();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Statement stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("select * from pracownik where login = '" + username + "';");
            while (rs.next()) {

                sb_imie.setText(rs.getString(2));
                sb_nazwisko.setText(rs.getString(3));
                sb_miejscowosc.setText(rs.getString(7));
                sb_adres.setText(rs.getString(8));
                sb_telefon.setText(rs.getString(9));
                sb_email.setText(rs.getString(10));
                sb_login.setText(rs.getString(5));
                aktualne_haslo = rs.getString(6);

            }

        } catch (Exception e) {
            helper.error(e.getMessage());
        }
    }

    @FXML
    private void zapiszZmiany(ActionEvent event) {

        try {
            int poprawnosc = 1;
            Statement stmt = sesja.createStatement();
            String new_miejscowosc = sb_miejscowosc.getText();
            String new_adres = sb_adres.getText();
            String new_telefon = sb_telefon.getText();
            String new_email = sb_email.getText();
            String old_password = sb_haslo.getText();
            String new_password = sb_nowe_haslo.getText();

            if (new_password.length() > 0) {

                if (old_password.equals(aktualne_haslo)) {

                } else {
                    poprawnosc = 0;
                    helper.error("Hasła nie zgadzają się");

                }

            } else {
                new_password = aktualne_haslo;
            }

            if (poprawnosc == 1) {
                aktualne_haslo = new_password;
                int wynik = stmt.executeUpdate("update pracownik set miejscowosc = '" + new_miejscowosc
                        + "', adres = '" + new_adres
                        + "', telefon = '" + new_telefon
                        + "', email = '" + new_email
                        + "', haslo = '" + new_password
                        + "' where login = '" + username + "';");
                if (wynik == 1) {

                    helper.message("Ustawienia zostały zapisane");
                }

            }

        } catch (Exception e) {
            helper.error(e.getMessage());
        }

    }

    @FXML
    private void powrtoDoMenu(ActionEvent event) throws IOException {

        try {
            sesja.close();
        } catch (SQLException ex) {

        }

        helper.powrotDoMenu();
        Stage settings = (Stage) powrot.getScene().getWindow();
        settings.close();
    }

}
