/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ZatwierdzZm(ActionEvent event) {
    }

    @FXML
    private void PowrotTab(ActionEvent event) {
    }
    
}