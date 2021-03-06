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
import warsztatsamochodowy.database.DatabaseConnection;

/**
 * Klasa kontrolera FXML do dodawanie nowych czesci.
 *
 */
import java.sql.*;
import javafx.scene.control.TextField;

public class AddPartController implements Initializable {

    @FXML
    private Button zatwierdz;

    @FXML
    private Button powrot;

    @FXML
    private TextField sb_nazwa;

    @FXML
    private TextField sb_producent;

    @FXML
    private TextField sb_ilosc;

    @FXML
    private TextField sb_cena;

   
boolean junit = false;
    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja;

    private Helper helper = new Helper();
    Statement stmt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    
     @FXML
    void dodajCzesc_button() {

                        String nazwa = sb_nazwa.getText();
        String producent = sb_producent.getText();
        String ilosc = sb_ilosc.getText();
        String cena = sb_cena.getText();
        dodajCzesc(nazwa,producent,ilosc,cena);

    }
    
   protected boolean dodajCzesc(String nazwa, String producent, String ilosc, String cena) {
        
boolean dziala = false;

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            Statement stmt = sesja.createStatement();

            int wynik = stmt.executeUpdate("INSERT INTO czesc (Nazwa,Producent,Ilosc,Cena) VALUES ('"
                    + nazwa
                    + "', '" + producent
                    + "', '" + ilosc
                    + "', '" + cena
                    + "');");

            if (wynik == 1) {
            sb_nazwa.clear();
                dziala = true;
                if(junit == false) {
                    sb_producent.clear();
            sb_ilosc.clear();
            sb_cena.clear();
                helper.message("Część została dodana");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            helper.error(e.getMessage());
        } finally {

            if (sesja != null) {
                try {
                    sesja.close();
                } catch (Exception e) {
                }
            }

        }
        return dziala;
    }
    

    @FXML
    private void powrot(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Parts.fxml", "Warsztat samochodowy - Części");
        Stage this_scene = (Stage) zatwierdz.getScene().getWindow();
        this_scene.close();
    }

}
