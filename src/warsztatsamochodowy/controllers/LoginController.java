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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * Klasa kontrolera FXML do obsługi okna logowania.
 *
 */
public class LoginController implements Initializable {

    public static String Stanowisko;
    public static String Username;
    public static String ID;

    public boolean junit = false;
    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;

    @FXML
    private TextField username;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;
    @FXML
    private Button registerButton;

    @FXML

    /**
     * Kliknięcie przycisku logowania przesyła wpisany login i hasło do funkcji
     * sprawdzającej poprawność danych.
     */
    private void buttonLogowanie_click(ActionEvent event) throws IOException {
        sprawdzLogowanie(username.getText(), password.getText());
    }

    @FXML
    private void password_onKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sprawdzLogowanie(username.getText(), password.getText());
        }
    }

    /**
     * Funkcja zamyka bieżące okno i loguje użytkownika do aplikacji, pokazując
     * menu główne.
     */
    public void zalogujUzytkownika() throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/MainMenu.fxml", "Warsztat samochodowy - Menu główne");
        Stage login_scene = (Stage) login.getScene().getWindow();
        login_scene.close();
    }

    /**
     * Zwraca nazwe stanowiska logowanego użytkownika
     *
     * @return Stanowisko stanowisko użytkownika
     */
    public String getStanowisko() {
        return Stanowisko;
    }

    /**
     * Zwraca login logowanego użytkownika
     *
     * @return Username login użytkowwnika
     */
    public String getLogin() {
        return Username;
    }
    
    public String getID() {
        return ID;
    }

    /**
     * Funkcja sprawdza poprawność danych wprowadzonych przez użytkownika i
     * wywołuje funkcję pokazującą menu główne lub wyświetla komunikat o
     * błędzie.
     *
     * @param username login użytkownika
     * @param password hasło użytkownika
     */
    protected boolean sprawdzLogowanie(String username, String password) throws IOException {

        boolean dziala = false;
        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("select PracownikId ,Login, Haslo, Specjalizacja, Status from pracownik where Login = '" + username
                    + "' AND Haslo = '" + password + "';");
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String status = rs.getString("Status");
                    if (status.equals("Zwolniony")) {
                        helper.error("Pracownik został zwolniony!");
                        break;
                    }
                    Stanowisko = rs.getString("Specjalizacja");
                    Username = username;
                    ID = rs.getString("PracownikId");
                    break;
                }
            } else {
                dziala = true;
                 if(junit == false) helper.error("Podano błędny login lub hasło!");
            }

        } catch (Exception e) {
            helper.error(e.getMessage());
        } finally {

            if (sesja != null) {
                try {
                    sesja.close();
                } catch (Exception e) {
                } finally {
                    if (Username != null) {
                      if(junit == false) zalogujUzytkownika();
                     dziala = true;
                        
                    }
                }
            }
        }
return dziala;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Username = null;
        Stanowisko = null;
    }

    @FXML
    private void registerWorker(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/RegisterWorker.fxml", "Warsztat samochodowy - Rejestracja pracownika");
        Stage login_scene = (Stage) login.getScene().getWindow();
        login_scene.close();
    }

}
