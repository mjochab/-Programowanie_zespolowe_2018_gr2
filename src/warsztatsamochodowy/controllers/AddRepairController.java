/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class AddRepairController implements Initializable {

    @FXML
    private TextField field_koszt;
    @FXML
    private DatePicker datazakonczenia;
    @FXML
    private ComboBox<String> combo_status;
    @FXML
    private TextField field_opis;
    @FXML
    private Button wybierz_klienta;
    @FXML
    private Button wybierz_samochod;
    @FXML
    private TableView<?> tab_czesci;
    @FXML
    private TableColumn<?, ?> kol_Nazwa;
    @FXML
    private TableColumn<?, ?> kol_Producent;
    @FXML
    private TableColumn<?, ?> kol_Ilosc;
    @FXML
    private TableColumn<?, ?> kol_Cena;
    @FXML
    private Button dodaj_czesc;
    @FXML
    private Button button_add;
    @FXML
    private Button button_back;

    @FXML
    private Label label_client;
    @FXML
    private TextField field_client;

    public void setFieldClientName(String name) {
        field_client.setText(name);
    }

    public void setName(String name) {
        label_client.setText(name);
    }

    private Helper helper = new Helper();

    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;

    //LoginController login = new LoginController();
    //String id_pracownika = login.getID();

    public static int Last_ID;

    public int getLastID() {
        return Last_ID;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> status = FXCollections.observableArrayList(
                "Przyjete",
                "W trakcie",
                "Zakonczone"
        );
        combo_status.setItems(status);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Tasks.fxml", "Warsztat samochodowy - Zlecenia");
        Stage this_scene = (Stage) button_back.getScene().getWindow();
        this_scene.close();
    }

    @FXML
    public void addTask(ActionEvent event) throws IOException {
        SearchClientsController klient = new SearchClientsController();
        String id_wybranegoklienta = klient.getClientID();

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            PreparedStatement ps = sesja.prepareStatement(
                    "INSERT INTO naprawa(data_rozpoczecia, data_zakonczenia, koszt, status, opis, id_klienta) VALUES(?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS
            );
            Date now = new Date();
            ps.setTimestamp(1, new Timestamp(now.getTime()));
            ps.setString(2, datazakonczenia.getValue().toString());

            ps.setInt(3, Integer.parseInt(field_koszt.getText()));
            ps.setString(4, combo_status.getSelectionModel().getSelectedItem());
            ps.setString(5, field_opis.getText());

            ps.setString(6, id_wybranegoklienta);
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Last_ID = rs.getInt(1);
            }
            
            ps.close();
            helper.message("Dodano naprawe");

//            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
//            while (rs.next()) {
//                Last_ID = rs.getString("LAST_INSERT_ID()");
//            }
            helper.sceneSwitcher("/warsztatsamochodowy/views/AddPartsToRepair.fxml", "Warsztat samochodowy - Dodaj czesci");
            Stage this_scene = (Stage) button_add.getScene().getWindow();
            this_scene.hide();


        } catch (Exception e) {
            helper.error("Blednie wypelnione pola");
        } finally {

            if (sesja != null) {
                try {
                    sesja.close();
                } catch (Exception e) {
                }
            }
        }


    }

    @FXML
    public void searchClient(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/SearchClient.fxml", "Warsztat samochodowy - Wyszukaj klienta");
        Stage this_scene = (Stage) button_back.getScene().getWindow();
        this_scene.hide();
    }

}
