package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;

/**
 * Klasa kontrolera FXML do obsługi okna logowania.
 *
 */
public class LoginController implements Initializable {

    public static String Stanowisko;
    public static String Username;

    private Helper helper = new Helper();
    @FXML
    private TextField username;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

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
     * Wyświetlenie komunikatu o błędzie
     *
     * @param message treść komunikatu
     */
    private void error(String message) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Funkcja zamyka bieżące okno i loguje użytkownika do aplikacji, pokazując
     * menu główne.
     *
     * @param username login użytkownika
     * @param stanowisko stanowisko użytkownika
     */
    public void zalogujUzytkownika(String username, String stanowisko) throws IOException {
        Stanowisko = stanowisko;
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

    /**
     * Funkcja sprawdza poprawność danych wprowadzonych przez użytkownika i
     * wywołuje funkcję pokazującą menu główne lub wyświetla komunikat o
     * błędzie.
     *
     * @param username login użytkownika
     * @param password hasło użytkownika
     */
    private void sprawdzLogowanie(String username, String password) throws IOException {

        if (konta.containsKey(username)) {

            if (konta.get(username)[0].equals(password)) {
                zalogujUzytkownika(konta.get(username)[0], konta.get(username)[1]);
            } else {
                error("Wprowadzono złe hasło!");
            }
        } else {
            error("Użytkownik nie istnieje!");
        }

    }

    HashMap<String, String[]> konta = new HashMap<String, String[]>();

    /**
     * Funkcja inicjalizująca kontroler. zapisuje przykładowe dane kont
     * użytkowników.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        konta.put("Janusz", new String[]{"123456", "Kierownik"});
        konta.put("Grażyna", new String[]{"brajanek2010", "Recepcjonistka"});
        konta.put("Heniek", new String[]{"kochamgrazynke", "Mechanik"});
        konta.put("Tadeusz", new String[]{"qwerty", "Administrator"});
    }

}
