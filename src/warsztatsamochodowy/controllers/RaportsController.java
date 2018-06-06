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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pdfcreator.ConvertToPdf;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DBHelper;
import warsztatsamochodowy.viewmodels.RaportsVM;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class RaportsController implements Initializable {

    @FXML
    private Button allUsersRaport;
    @FXML
    private Button back;
    @FXML
    private Button createReceipt;
    @FXML
    private TableView<RaportsVM> receiptTable;
    @FXML
    private TableColumn<String, String> colName;
    @FXML
    private TableColumn<String, String> colLastName;
    @FXML
    private TableColumn<String, String> colDesc;
    @FXML
    private TableColumn<Long, Long> colCost;

    private  List<RaportsVM> raportsVM = new ArrayList<>();
    private DBHelper dbhelper = new DBHelper();
    private Helper helper = new Helper();
    private ConvertToPdf convert = new ConvertToPdf();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTableData();
    }    
    private void getTableData(){
        raportsVM = dbhelper.getAllClientsForPDF();
        receiptTable.setItems(FXCollections.observableArrayList(raportsVM));
        
        colName.setCellValueFactory(new PropertyValueFactory("clientName"));
        colLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        colDesc.setCellValueFactory(new PropertyValueFactory("desc"));
        colCost.setCellValueFactory(new PropertyValueFactory("cost"));
    }

    @FXML
    private void allUsersRaport(ActionEvent event) {
       convert.printAllUsers();
       helper.message("Stworzono raport wszystkich uzytkownikow");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/MainMenu.fxml", "Warsztat samochodowy - Menu");
        Stage this_scene = (Stage) back.getScene().getWindow();
        this_scene.close();
    }

    @FXML
    private void createReceipt(ActionEvent event) {
        
        
         RaportsVM repair = receiptTable.getSelectionModel().getSelectedItem();
          System.out.println(repair);
        if(repair == null){
            helper.error("Prosze wybrac klienta z tabeli dla ktorego ma byc wygenerowany rachunek");
            return;
        }
       
        convert.printReceipt(repair.getRepairId());
        helper.message("Stworzono rachunek");
    }
    
    
}
