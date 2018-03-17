package warsztatsamochodowy.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
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

    @FXML
    public void logout(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/warsztatsamochodowy/views/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage login_scene = new Stage();
            login_scene.setScene(new Scene(root1));
            login_scene.setTitle("Warsztat samochodowy - Logowanie");
            login_scene.show();
            Stage mainmenu_scene = (Stage) logout.getScene().getWindow();
            mainmenu_scene.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String stanowisko = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
