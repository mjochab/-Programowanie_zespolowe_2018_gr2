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
 *
 */
import java.sql.*;
import javafx.scene.control.TextField;

public class EditPartController implements Initializable {

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

    PartsController parts = new PartsController();
    String id = parts.getID();
    String nazwa = parts.getNazwa();
    String producent = parts.getProducent();
    String cena = parts.getCena();
    String ilosc = parts.getIlosc();

    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja;

    private Helper helper = new Helper();
    Statement stmt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sb_nazwa.setText(nazwa);
        sb_producent.setText(producent);
        sb_ilosc.setText(ilosc);
        sb_cena.setText(cena);

    }

    @FXML
    void edytujCzesc(ActionEvent event) {

        String nazwa = sb_nazwa.getText();
        String producent = sb_producent.getText();
        String ilosc = sb_ilosc.getText();
        String cena = sb_cena.getText();
        String id = this.id;

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();
            Statement stmt = sesja.createStatement();

            int wynik = stmt.executeUpdate("UPDATE czesc set Nazwa = '" + nazwa
                    + "', Producent = '" + producent
                    + "', Ilosc = '" + ilosc
                    + "', Cena = '" + cena
                    + "' WHERE ID = '" + id + "';");
            if (wynik == 1) {
                helper.message("Część została zmieniona");
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

    @FXML
    private void powrot(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Parts.fxml", "Warsztat samochodowy - Części");
        Stage this_scene = (Stage) zatwierdz.getScene().getWindow();
        this_scene.close();
    }

}
