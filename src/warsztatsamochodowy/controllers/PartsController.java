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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class PartsController implements Initializable {

    @FXML
    private TextField Tf_nazwa;
    @FXML
    private TextField Tf_producent;
    @FXML
    private TextField Tf_cena;
    @FXML
    private Button b_zatwierdz;
    @FXML
    private Button b_powrot;

    

    private Helper helper = new Helper();
    /**
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void powrtoDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage settings = (Stage) b_powrot.getScene().getWindow();
        settings.close();
    }
    
}
