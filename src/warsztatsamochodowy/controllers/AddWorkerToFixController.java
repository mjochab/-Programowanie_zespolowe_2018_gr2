/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.database.entity.Repair;
import warsztatsamochodowy.database.entity.Pracownik;
import warsztatsamochodowy.database.entity.Samochod;
import warsztatsamochodowy.viewmodels.RepairWorkerVM;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class AddWorkerToFixController implements Initializable {
    private DBHelper dbhelper = new DBHelper();
    private Helper helper = new Helper();
    private  List<Pracownik> dbWorkers = new ArrayList<>();
    private  List<Repair> dbFix = new ArrayList<>();
    private  List<RepairWorkerVM> repairWorkerVM = new ArrayList<>();
    @FXML
    private ComboBox<Pracownik> workersCB = new ComboBox<>();
    @FXML
    private ComboBox<Repair> fixesCB  = new ComboBox<>();
    @FXML
    private Button addWorkerToFix;
    @FXML
    private Button back;
    private List<Samochod> dbCars;
    @FXML
    private TableView<RepairWorkerVM> workerFixTable;
    @FXML
    private TableColumn<Integer, Integer> workerIdTable;
    @FXML
    private TableColumn<String, String> workerNameTable;
    @FXML
    private TableColumn<String, String> workerLastNameTable;
    @FXML
    private TableColumn<Integer, Integer> carIdTable;
    @FXML
    private TableColumn<String, String> carProducerTable;
    @FXML
    private TableColumn<String, String> carMakeTable;
    /**
     * Initializes the controller class.
     * Dodaje listy napraw oraz liste pracownikow do comboboxow
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     this.getTableData();
     this.setComboBoxes();
     
    }    

    @FXML
    private void addWorkerToFix(ActionEvent event) {
        int fixId = fixesCB.getSelectionModel().getSelectedItem().getFixId();
        int workerId = workersCB.getSelectionModel().getSelectedItem().getID();
        if(fixId < 1 && workerId < 1){
            helper.error("Nalezy wybrac pracownika oraz naprawe");
            return;
        }
        dbhelper.addWorkerToFix(fixId, workerId);
        helper.message("Dodano pracownika do naprawy");
        this.getTableData();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/MainMenu.fxml", "Warsztat samochodowy - Menu");
        Stage this_scene = (Stage) back.getScene().getWindow();
        this_scene.close();

    }
    //implement converter for displaying data
    private  StringConverter<Pracownik> converterWorker = new StringConverter<Pracownik>() {
            @Override
            public String toString(Pracownik object) {
                return object.getID() + " " +object.getImie() +object.getNazwisko() ;
            }

            @Override
            public Pracownik fromString(String string) {
                return null;
            }
        };
    //seed table with data from database
    private void getTableData(){
      repairWorkerVM = dbhelper.getAllWorkersAssignedToRepairs();
     
      workerFixTable.setItems(FXCollections.observableArrayList(repairWorkerVM));
     
      workerIdTable.setCellValueFactory(new PropertyValueFactory("WorkerId"));
      workerNameTable.setCellValueFactory(new PropertyValueFactory("Imie"));
      workerLastNameTable.setCellValueFactory(new PropertyValueFactory("Nazwisko"));
      carIdTable.setCellValueFactory(new PropertyValueFactory("CarId"));
      carProducerTable.setCellValueFactory(new PropertyValueFactory("Producent"));
      carMakeTable.setCellValueFactory(new PropertyValueFactory("Model"));
     
    }
    private void setComboBoxes(){
     dbFix = dbhelper.getAllTasks();
     dbWorkers = dbhelper.getAllWorkers();
     dbCars = dbhelper.getCars();
     
     ObservableList<Pracownik> dbPracownikList = FXCollections.observableArrayList(dbWorkers);
     ObservableList<Repair> dbFixList = FXCollections.observableArrayList(dbFix);
     
     ObservableList<Repair> filteredFixList = FXCollections.observableArrayList();
     
     if(dbCars == null || dbCars.isEmpty()){
         helper.message("Nie znaleziono aut.");
         return;
     }
     // set list of workers to combobox
     workersCB.setItems(dbPracownikList);
  
     //set converter
     workersCB.setConverter(converterWorker);
     
     //get carId and create new list
     dbFixList.forEach((f) -> {
            Samochod samochod = dbCars.stream()
                    .filter(w -> w.getId() == f.getSamochodId()).findFirst().get();
             if(samochod == null){
                 helper.message("Nie znaleziono auta");
                 return;
                 }
          filteredFixList.add(new Repair(f.getFixId(), samochod.getProducent(), samochod.getModel()));
           
     });
     //set combobox for fixes
     fixesCB.setItems(filteredFixList);
     fixesCB.setConverter(converterFix);
     
    }
    //implement converter for displaying data
    private  StringConverter<Repair> converterFix = new StringConverter<Repair>() {
            @Override
            public String toString(Repair object) {
                return object.getFixId() + " " +object.getCarName()+object.getCarProducer();
            }

            @Override
            public Repair fromString(String string) {
                return null;
            }
        };
}
