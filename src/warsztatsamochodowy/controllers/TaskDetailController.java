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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import warsztatsamochodowy.Helper;
import static warsztatsamochodowy.controllers.TasksController.Data_zakonczenia;
import static warsztatsamochodowy.controllers.TasksController.Imie;
import static warsztatsamochodowy.controllers.TasksController.Koszt;
import static warsztatsamochodowy.controllers.TasksController.Naprawa_ID;
import static warsztatsamochodowy.controllers.TasksController.Nazwisko;
import static warsztatsamochodowy.controllers.TasksController.Opis;
import static warsztatsamochodowy.controllers.TasksController.Status;
import warsztatsamochodowy.database.DatabaseConnection;
import warsztatsamochodowy.database.entity.Czesc;
import warsztatsamochodowy.database.entity.PracownikRepair;
import warsztatsamochodowy.database.entity.Repair;

/**
 * FXML Controller class
 *
 * @author Bartek
 */
public class TaskDetailController implements Initializable {

    @FXML
    private TextField field_koszt;
    @FXML
    private DatePicker datazakonczenia;
    @FXML
    private ComboBox<String> combo_status;
    @FXML
    private TextField field_opis;
    @FXML
    private Button button_zmienKlienta;
    @FXML
    private TableView<Czesc> tab_czesci;
    @FXML
    private TableColumn<?, ?> kol_Nazwa;
    @FXML
    private TableColumn<?, ?> kol_Producent;
    @FXML
    private TableColumn<?, ?> kol_Ilosc;
    @FXML
    private TableColumn<?, ?> kol_Cena;
    @FXML
    private TableView<PracownikRepair> tabelaPracownicy;
    @FXML
    private TableColumn<?, ?> colImie;
    @FXML
    private TableColumn<?, ?> colNazwisko;
    @FXML
    private TableColumn<?, ?> colNrTel;
    @FXML
    private TableColumn<?, ?> colMiejscowosc;
    @FXML
    private TableColumn<?, ?> colAdres;
    @FXML
    private TableColumn<?, ?> colSpecjalizacja;
    @FXML
    private Button button_zmienCzesci;
    @FXML
    private Button button_zmienPracownika;
    @FXML
    private Button button_powrot;
    @FXML
    private Label label_klient;

    private Helper helper = new Helper();
    DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;

    public static boolean edit;
    @FXML
    private Button button_detalEdit;

    public boolean getEdit() {
        return edit;
    }

    public void setEdit(boolean stan) {
        edit = stan;
    }

    TasksController task = new TasksController();
    @FXML
    private Button button_wczytaj;

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> status = FXCollections.observableArrayList(
                "Przyjete",
                "W trakcie",
                "Zakonczone"
        );
        combo_status.setItems(status);

        label_klient.setText(task.getImie() + " " + task.getNazwisko());
        datazakonczenia.setValue(LOCAL_DATE("" + task.getData_zakonczenia() + ""));
        combo_status.setValue(task.getStatus());
        field_koszt.setText(task.getKoszt());
        field_opis.setText(task.getOpis());

        tab_czesci.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        kol_Nazwa.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        kol_Producent.setCellValueFactory(new PropertyValueFactory<>("Producent"));
        kol_Ilosc.setCellValueFactory(new PropertyValueFactory<>("Ilosc"));
        kol_Cena.setCellValueFactory(new PropertyValueFactory<>("Cena"));

        tabelaPracownicy.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colImie.setCellValueFactory(new PropertyValueFactory("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        colNrTel.setCellValueFactory(new PropertyValueFactory("nrTel"));
        colMiejscowosc.setCellValueFactory(new PropertyValueFactory("miejscowosc"));
        colAdres.setCellValueFactory(new PropertyValueFactory("adres"));
        colSpecjalizacja.setCellValueFactory(new PropertyValueFactory("specjalizacja"));
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        helper.sceneSwitcher("/warsztatsamochodowy/views/Tasks.fxml", "Warsztat samochodowy - Zlecenia");
        Stage this_scene = (Stage) button_powrot.getScene().getWindow();
        this_scene.close();
    }

    @FXML
    private void zmienKlienta(ActionEvent event) throws IOException {
        edit = true;
        helper.sceneSwitcher("/warsztatsamochodowy/views/SearchClient.fxml", "Warsztat samochodowy - Wybierz klienta");
        Stage this_scene = (Stage) button_zmienKlienta.getScene().getWindow();
        this_scene.close();
    }

    @FXML
    private void zmienCzesci(ActionEvent event) throws IOException {
        edit = true;
        helper.sceneSwitcher("/warsztatsamochodowy/views/AddPartsToRepair.fxml", "Warsztat samochodowy - Dodaj czesci");
        Stage this_scene = (Stage) button_zmienCzesci.getScene().getWindow();

    }

    @FXML
    private void zmienPracownika(ActionEvent event) throws IOException {
        edit = true;
        helper.sceneSwitcher("/warsztatsamochodowy/views/AddWorkersToRepair.fxml", "Warsztat samochodowy - Dodaj pracownikow");
        Stage this_scene = (Stage) button_zmienPracownika.getScene().getWindow();
    }

    @FXML
    private void wczytajTabele(ActionEvent event) {
        tab_czesci.getItems().clear();
        tabelaPracownicy.getItems().clear();
        wczytajCzesci();
        wczytajPracownikow();

        try {
            TasksController task = new TasksController();
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM klient INNER JOIN naprawa ON naprawa.id_klienta = klient.KlientId WHERE naprawa.napraw_id = " + task.getRepairID());

            while (rs.next()) {
                Naprawa_ID = rs.getString("napraw_id");
                Imie = rs.getString("Imie");
                Nazwisko = rs.getString("Nazwisko");
                Data_zakonczenia = rs.getString("data_zakonczenia");
                Koszt = rs.getString("koszt");
                Status = rs.getString("status");
                Opis = rs.getString("opis");
            }
            label_klient.setText(task.getImie() + " " + task.getNazwisko());

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

    private void dodajDoTabeliDodanych(String id, String nazwa, String producent, String ilosc, String cena) {

        Czesc czesc = new Czesc(id, nazwa, producent, ilosc, cena);
        tab_czesci.getItems().add(czesc);

    }

    private void wczytajCzesci() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT *,nc.Ilosc as IloscCz FROM czesc as c INNER JOIN naprawa_czesci nc ON c.CzescID = nc.id_czesci WHERE nc.id_naprawy = " + task.getRepairID());

            while (rs.next()) {
                String id = rs.getString("CzescId");
                String nazwa = rs.getString("Nazwa");
                String producent = rs.getString("Producent");
                String ilosc = rs.getString("IloscCz");
                String cena = rs.getString("Cena");
                dodajDoTabeliDodanych(id, nazwa, producent, ilosc, cena);
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

    private void dodajDoTabeli(int id, String imie, String nazwisko, String nrTel, String miejscowosc, String adres, String specjalizacja) {

        PracownikRepair pracownik = new PracownikRepair(id, imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
        tabelaPracownicy.getItems().add(pracownik);

    }

    private void wczytajPracownikow() {

        try {
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownik as p "
                    + "INNER JOIN naprawa_pracownika np ON p.PracownikId = np.id_pracownika WHERE np.id_naprawy = " + task.getRepairID());

            while (rs.next()) {
                String id = rs.getString("PracownikId");
                String imie = rs.getString("Imie");
                String nazwisko = rs.getString("Nazwisko");
                String nrTel = rs.getString("Telefon");
                String miejscowosc = rs.getString("Miejscowosc");
                String adres = rs.getString("Adres");
                String specjalizacja = rs.getString("Specjalizacja");
                dodajDoTabeli(Integer.parseInt(id), imie, nazwisko, nrTel, miejscowosc, adres, specjalizacja);
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
    public static String Imie, Nazwisko, Naprawa_ID, Data_zakonczenia, Koszt, Status, Opis, Edit;

    @FXML
    private void detalEdit(ActionEvent event) {
        edit = true;
        try {
            TasksController task = new TasksController();
            if (sesja == null || sesja.isClosed()) {
                sesja = PolaczenieDB.connectDatabase();
            }
            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM klient INNER JOIN naprawa ON naprawa.id_klienta = klient.KlientId WHERE naprawa.napraw_id = " + task.getRepairID());

            while (rs.next()) {
                Naprawa_ID = rs.getString("napraw_id");
                Imie = rs.getString("Imie");
                Nazwisko = rs.getString("Nazwisko");
                Data_zakonczenia = rs.getString("data_zakonczenia");
                Koszt = rs.getString("koszt");
                Status = rs.getString("status");
                Opis = rs.getString("opis");
            }
            helper.sceneSwitcher("/warsztatsamochodowy/views/AddRepair.fxml", "Warsztat samochodowy - Szczegoly zlecenia");
            Stage this_scene = (Stage) button_detalEdit.getScene().getWindow();

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

    public String getRepairID() {
        return Naprawa_ID;
    }

    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public String getData_zakonczenia() {
        return Data_zakonczenia;
    }

    public String getKoszt() {
        return Koszt;
    }

    public String getStatus() {
        return Status;
    }

    public String getOpis() {
        return Opis;
    }

}
