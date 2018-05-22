/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class PartsController implements Initializable {

    @FXML
    private Button b_dodaj;

    @FXML
    private Button b_edytuj;

    @FXML
    private Button b_usun;

    @FXML
    private Button b_powrot;

    @FXML
    private TableView<Czesc> tab_czesci;

    @FXML
    private TableColumn<Czesc, String> kol_Nazwa;

    @FXML
    private TableColumn<Czesc, String> kol_Producent;

    @FXML
    private TableColumn<Czesc, String> kol_Ilosc;

    @FXML
    private TableColumn<Czesc, String> kol_Cena;

    private Helper helper = new Helper();
  /**
     * Polaczenie z baza
     * Tworzenie zmienncyh 
     */
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
    public static String Nazwa, ID, Cena, Producent, Ilosc;

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    private TableColumn<Config, String> columNazwa;
    private TableColumn<Config, String> columnProducent;
    private TableColumn<Config, String> columnCena;

   private ObservableList<Config> data;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Statement stmt = null;
                data = FXCollections.observableArrayList();
       try{
           stmt = sesja.createStatement();
           String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) "
                    + "VALUES (1,'Filtr powietrza','Bosh','70');";

             ResultSet rs = stmt.executeQuery("select nazwa_cz,producent_cz,cena_cz from czesci;");
             
             while (rs.next()) {
                
                columNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_cz"));
                columnProducent.setCellValueFactory(new PropertyValueFactory<>("producent_cz"));
                columnCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
            }
             // tab_czesci.setItems();
            //    tab_czesci.setItems();
           
       }
       
       
       catch (Exception e) {
            helper.error(e.getMessage());
        }
    }
    private void dodajDoTabeli(String id, String nazwa, String producent, String ilosc, String cena) {

        Czesc czesc = new Czesc(id, nazwa, producent, ilosc, cena);
        tab_czesci.getItems().add(czesc);

    };

   private void usunzTabeli(ObservableList<Czesc> zaznaczoneCzesci) {

        ObservableList<Czesc> wszystkieCzesci = tab_czesci.getItems();
        wszystkieCzesci.removeAll(zaznaczoneCzesci);
        // wszystkieCzesci.removeAll(czescZaznaczona);

    }

   private void wczytajBaze() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Czesc;");

            while (rs.next()) {
                String id = rs.getString("ID");
                String nazwa = rs.getString("Nazwa");
                String producent = rs.getString("Producent");
                String ilosc = rs.getString("Ilosc");
                String cena = rs.getString("Cena");
                dodajDoTabeli(id, nazwa, producent, ilosc, cena);
            }

        } catch (Exception e) {
            helper.error(e.getMessage());
        } finally {

            if (sesja != null) {
                try {
                    sesja.close();
                } catch (Exception e) {
                }
            }
        }

    }

 
    
    
    /**
     * Tworzenie metody przycisku powrot
     * @param event
     * @throws IOException 
     */
    @FXML
    private void powrotDoMenu(ActionEvent event) throws IOException {
        helper.powrotDoMenu();
        Stage settings = (Stage) b_powrot.getScene().getWindow();
        settings.close();
    }
    /**
     * Tworzenie metody dodawnaia do przycisku AddCzesci
     * @param event
     * @throws SQLException 
     */

    private void AddCzesci(ActionEvent event) throws SQLException {
           Statement stmt =null;
           
        try{
            stmt = sesja.createStatement();
        
            String new_nazwa = Tf_nazwa.getText();
            String new_producent= Tf_producent.getText();
            int new_cena = Integer.parseInt(Tf_cena.getText());
          String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) VALUES (NULL,'"+new_nazwa+"','"+new_producent+"','"+new_cena+"')";
            
            //String query = "INSERT INTO `czesci` (`id_czesci`, `nazwa_cz`, `producent_cz`, `cena_cz`) VALUES ('1',"+new_nazwa+"','"+new_producent+"','"+new_cena+"')";
            int wynik = stmt.executeUpdate(query);
            helper.message("poszlo"+new_nazwa);
        } catch (Exception e) {
            // helper.error(e.getMessage());
        }
                
            
    }

    @FXML
    private void dodajCzesc(ActionEvent event) throws IOException {

        helper.sceneSwitcher("/warsztatsamochodowy/views/AddPart.fxml", "Warsztat samochodowy - Nowa część");
        Stage this_scene = (Stage) tab_czesci.getScene().getWindow();
        this_scene.close();

    }

    @FXML
    private void usunCzesc(ActionEvent event) {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = sesja.createStatement();
            }
            ObservableList<Czesc> czescZaznaczona;
            ObservableList<Czesc> doUsuniecia = FXCollections.observableArrayList();
            czescZaznaczona = tab_czesci.getSelectionModel().getSelectedItems();
            for (Czesc c : czescZaznaczona) {

                String id = c.getID();

                int wynik = stmt.executeUpdate("DELETE FROM Czesc WHERE ID = " + id + ";");
                if (wynik == 1) {

                    doUsuniecia.add(c);
                }

            }

            usunzTabeli(doUsuniecia);
            tab_czesci.getSelectionModel().clearSelection();

        } catch (Exception e) {
            helper.error(e.getMessage());
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
    private void edytujCzesc(ActionEvent event) throws IOException {

        if (tab_czesci.getSelectionModel().getSelectedItems().size() == 1) {

            Czesc c = tab_czesci.getSelectionModel().getSelectedItem();

            ID = c.getID();
            Nazwa = c.getNazwa();
            Producent = c.getProducent();
            Ilosc = c.getIlosc();
            Cena = c.getCena();

            helper.sceneSwitcher("/warsztatsamochodowy/views/EditPart.fxml", "Warsztat samochodowy - Edycja części");
            Stage this_scene = (Stage) tab_czesci.getScene().getWindow();
            this_scene.close();

        } else {
            helper.error("Wybierz 1 część");
        }

    }

}
