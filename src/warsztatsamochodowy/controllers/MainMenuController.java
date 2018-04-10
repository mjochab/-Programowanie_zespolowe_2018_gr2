package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;

/**
 * Klasa kontrolera FXML do obsługi menu głównego.
 *
 */
public class MainMenuController implements Initializable {

    @FXML
    private Pane logout;

    @FXML
    private Pane settings;

    @FXML
    private Pane clients;

    @FXML
    private Pane visits;

    @FXML
    private Pane team;

    @FXML
    private Pane parts;

    @FXML
    private Pane tasks;

    @FXML
    private Pane orders;

    //zmienne globalne z loginem i stanowiskiem użytkownika
    LoginController login = new LoginController();
    String stanowisko = login.getStanowisko();
    String username = login.getLogin();
    
    private final Helper helper = new Helper();
    //ustawienie przycisków w menu jako odblokowane
    boolean lock_tasks, lock_orders, lock_parts, lock_team, lock_clients, lock_visits, lock_settings, lock_logout = false;

    /**
     * Funkcja blokuje przyciski menu dla nieuprawnionych użytkowników i
     * zapisuje jego login oraz stanowisko do zmiennej globalnej.
     *
     * @param username login użytkownika
     * @param stanowisko stanowisko użytkownika
     */
    public void przygotujMenu() {
        stanowisko = this.stanowisko;
        switch (stanowisko) {
            case "Kierownik":
                lock_tasks = true;
                lock_orders = true;
                lock_parts = true;
                lock_clients = true;
                lock_visits = true;
                tasks.setOpacity(0.45);
                orders.setOpacity(0.45);
                parts.setOpacity(0.45);
                clients.setOpacity(0.45);
                visits.setOpacity(0.45);
                break;
            case "Recepcjonistka":
                lock_team = true;
                lock_parts = true;
                lock_orders = true;
                team.setOpacity(0.45);
                orders.setOpacity(0.45);
                parts.setOpacity(0.45);
                break;
            case "Mechanik":
                lock_team = true;
                lock_clients = true;
                lock_visits = true;
                team.setOpacity(0.45);
                clients.setOpacity(0.45);
                visits.setOpacity(0.45);
        }
    }

    /**
     * Funkcja zamyka menu główne i pokazuje ekran logowania.
     *
     */
    @FXML
    public void logout(MouseEvent event) throws IOException {
        if (lock_logout == false) {
            helper.sceneSwitcher("/warsztatsamochodowy/views/Login.fxml", "Warsztat samochodowy - Logowanie");

            Stage mainMenu = (Stage) logout.getScene().getWindow();
            mainMenu.close();
        }

    }

    @FXML
    void clients(MouseEvent event) {
        if (lock_clients == false) {

        }

    }

    @FXML
    void orders(MouseEvent event) {
        if (lock_orders == false) {

        }
    }

    @FXML
    void parts(MouseEvent event) throws IOException {
        if (lock_parts == false) {

              helper.sceneSwitcher("/warsztatsamochodowy/views/Parts.fxml", "Warsztat samochodowy - Części");


                                     
                    Stage mainmenu_scene = (Stage) logout.getScene().getWindow();
                    mainmenu_scene.close();    

        }
    }

    @FXML
    void settings(MouseEvent event) throws IOException {
        if (lock_settings == false) {
            helper.sceneSwitcher("/warsztatsamochodowy/views/UserSettings.fxml", "Warsztat samochodowy - Ustawienia konta");

            Stage mainmenu_scene = (Stage) logout.getScene().getWindow();
            mainmenu_scene.close();
        }
    }

    @FXML
    void tasks(MouseEvent event) {
        if (lock_tasks == false) {

        }
    }

    @FXML
    void team(MouseEvent event) throws IOException {
        if (lock_team == false) {
            helper.sceneSwitcher("/warsztatsamochodowy/views/Workers.fxml", "Warsztat samochodowy - Pracownicy");
            Stage mainmenu_scene = (Stage) team.getScene().getWindow();
            mainmenu_scene.close();
        }
    }

    @FXML
    void visits(MouseEvent event) {
        if (lock_visits == false) {

        }
    }

    /**
     * Funkcja inicjalizująca kontroler.
     *
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        przygotujMenu();
    }

}
