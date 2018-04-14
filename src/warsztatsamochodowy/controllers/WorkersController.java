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

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class WorkersController implements Initializable {

    @FXML
    private Button powrot;
    @FXML
    private Button dodajPracownika;
    
    private Helper helper = new Helper();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dodajPracownika(ActionEvent event) {
    }

    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage workers = (Stage) powrot.getScene().getWindow();
        workers.close();
    }
    
}