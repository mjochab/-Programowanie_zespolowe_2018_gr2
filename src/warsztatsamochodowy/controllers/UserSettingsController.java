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
 * Klasa kontrolera FXML do zmiany ustawieĹ zalogowanego uĹźytkownika.
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

    Connection sesja;
    boolean junit = false;

    private Helper helper = new Helper();
    Statement stmt;

    /**
     * Podczas inicjalizacji kontrolera z bazy danych pobierane sÄ dane
     * zalogowanego uĹźytkownika i wyĹwietlane w polach tekstowych
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pobierzDane(username);
    }

    boolean pobierzDane(String username) {
        boolean dziala = false;
        try {
            sesja = PolaczenieDB.connectDatabase();
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("select * from pracownik where Login = '" + username + "';");
            if (rs.isBeforeFirst()) {

                while (rs.next()) {

           
                    if(junit == false) {
                        sb_imie.setText(rs.getString("Imie"));
                        sb_nazwisko.setText(rs.getString("Nazwisko"));
                        sb_miejscowosc.setText(rs.getString("Miejscowosc"));
                        sb_adres.setText(rs.getString("Adres"));
                        sb_telefon.setText(rs.getString("Telefon"));
                        sb_email.setText(rs.getString("Email"));
                        sb_login.setText(rs.getString("Login"));
                        aktualne_haslo = rs.getString("Haslo");
                    }
                                        dziala = true;
                    break;
                }
            } else {
                dziala = true;
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
        return dziala;
    }

    @FXML
    /**
     * Metoda pobiera wartoĹci z pĂłl tekstowych i aktualizuje dane
     * uĹźytkownika w bazie danych. Zmiana hasĹa wymaga wpisania aktualnego
     * hasĹa, w przypadku braku zgodnoĹci haseĹ wyĹwietlany jest bĹÄd i
     * dane nie sÄ aktualizowane.
     */
    private void zapiszZmiany(ActionEvent event) {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
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
                    helper.error("HasĹa nie zgadzajÄ siÄ");

                }

            } else {
                new_password = aktualne_haslo;
            }

            if (poprawnosc == 1) {
                aktualne_haslo = new_password;
                int wynik = stmt.executeUpdate("update pracownik set Miejscowosc = '" + new_miejscowosc
                        + "', Adres = '" + new_adres
                        + "', Telefon = '" + new_telefon
                        + "', Email = '" + new_email
                        + "', Haslo = '" + new_password
                        + "' where Login = '" + username + "';");
                if (wynik == 1) {

                    helper.message("Ustawienia zostaĹy zapisane");
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
    private void powrtoDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage settings = (Stage) powrot.getScene().getWindow();
        settings.close();
    }

}
