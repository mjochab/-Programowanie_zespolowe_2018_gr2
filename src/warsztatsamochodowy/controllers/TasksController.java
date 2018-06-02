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
import javafx.scene.control.TableColumn;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Pracownik;
import warsztatsamochodowy.database.entity.Samochod;
import warsztatsamochodowy.database.entity.Task;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class TasksController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button addTask;
    @FXML
    private Button editTask;
    @FXML
    private Button deleteTask;
    @FXML
    private TableColumn<Pracownik, String> colWorker;
    @FXML
    private TableColumn<Klient, String> colClient;
    @FXML
    private TableColumn<Samochod, String> colCar;
    @FXML
    private TableColumn<Task, String> colStatus;
    @FXML
    private TableColumn<Task, Float> colPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void addTask(ActionEvent event) {
    }

    @FXML
    private void editTask(ActionEvent event) {
    }

    @FXML
    private void deleteTask(ActionEvent event) {
    }
    
}
