/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.entity.Fix;
import warsztatsamochodowy.database.entity.Pracownik;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class AddWorkerToFixController implements Initializable {
    private DBHelper dbhelper = new DBHelper();
    @FXML
    private ComboBox<String> workersList = new ComboBox<>();;
    @FXML
    private ComboBox<Fix> fixesList;
    @FXML
    private Button addWorkerToFix;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     List<Pracownik> dbWorkers = new ArrayList<>();
     dbWorkers = dbhelper.getAllWorkers();
     ObservableList<Pracownik> listPracownik = FXCollections.observableArrayList(dbWorkers);
     for(Pracownik p : listPracownik){
         workersList.getItems().add(p.getImie());
     }
     
    }    

    @FXML
    private void addWorkerToFix(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
