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
 * Klasa kontrolera FXML do zmiany ustawień zalogowanego użytkownika.
 *
 * @author lukasz, sebastian
 */
import java.sql.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserSettingsController implements Initializable {

    @FXML
    private Button powrot;

    @FXML
    private TextField sb_imie;

    @FXML
    private TextField sb_nazwisko;

    @FXML
    private TextField sb_miejscowosc;

    @FXML
    private TextField sb_adres;

    @FXML
    private TextField sb_login;

    @FXML
    private PasswordField sb_haslo;

    @FXML
    private PasswordField sb_nowe_haslo;

    @FXML
    private TextField sb_telefon;

    @FXML
    private TextField sb_email;

    DatabaseConnection PolaczenieDB = new DatabaseConnection();

    LoginController login = new LoginController();
    String username = login.getLogin();
    String aktualne_haslo = "";

    Connection sesja = PolaczenieDB.connectDatabase();

    private Helper helper = new Helper();

    /**
     * Podczas inicjalizacji kontrolera z bazy danych pobierane są dane
     * zalogowanego użytkownika i wyświetlane w polach tekstowych
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Statement stmt = null;

        try {

            stmt = sesja.createStatement();

            //dodawanie tabeli, jeżeli nie istnieje
            String query = "CREATE TABLE IF NOT EXISTS `pracownik` (\n"
                    + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Login` varchar(100) NOT NULL,\n"
                    + "  `Haslo` varchar(100) NOT NULL,\n"
                    + "  `Imie` varchar(100) NOT NULL,\n"
                    + "  `Nazwisko` varchar(100) NOT NULL,\n"
                    + "  `Miejscowosc` varchar(100) NOT NULL,\n"
                    + "  `Adres` varchar(100) NOT NULL,\n"
                    + "  `Telefon` varchar(100) NOT NULL,\n"
                    + "  `Email` varchar(100) NOT NULL,\n"
                    + "  `Specjalizacja` varchar(100) NOT NULL,\n"
                    + "  `Wynagrodzenie` varchar(100) ,\n"
                    + "  `Status` varchar(100) ,\n"
                    + "  PRIMARY KEY (`ID`),\n"
                    + "  UNIQUE KEY `ID_UNIQUE` (`ID`)\n"
                    + ");";

            int wynik = stmt.executeUpdate(query);

            //dodawanie tabeli, jeżeli nie istnieje
            
            
            //dodawanie rekordów do tabeli Pracownik
            
            query = "INSERT INTO `pracownik` (`ID`, `Login`, `Haslo`, `Imie`, `Nazwisko`, `Miejscowosc`, `Adres`, `Telefon`, `Email`, `Specjalizacja`, `Wynagrodzenie`, `Status`) "
                    + "VALUES (1,'Janusz','123456','Janusz','Nosacz','Rzeszów','ul. Podwiłocze 1','123456789','janusz@gmail.com','Kierownik','5000','Zatrudniony'),"
                    + "(2,'Grażyna','brajanek2010','Grazyna','Nosacz','Rzeszów','ul. Podwisłocze 1','987456321','grazyna@gmail.com','Recepcja','2000','Zatrudnony'),"
                    + "(3,'Heniek','kochamgrazynke','Henryk','Kowalski','Kraków','ul. Partyzantów 4','111222333','heniek@gmail.com','Mechanik','3000','Zatrudnoiny'),"
                    + "(4,'Tadeusz','qwerty','Tadeusz','Nowak','Mielec','ul. Grunwaldzka 10','741852963','tadek@gmail.com','Administrator','4000','Zatrudniony');";
            
                     wynik = stmt.executeUpdate(query);

            //dodawanie rekordów do tabeli Pracownik
            
                    } catch (Exception e) {
            // helper.error(e.getMessage());
        }
        
        try {
            
                       if(stmt == null) stmt = sesja.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from pracownik where Login = '" + username + "';");
            while (rs.next()) {

                sb_imie.setText(rs.getString("Imie"));
                sb_nazwisko.setText(rs.getString("Nazwisko"));
                sb_miejscowosc.setText(rs.getString("Miejscowosc"));
                sb_adres.setText(rs.getString("Adres"));
                sb_telefon.setText(rs.getString("Telefon"));
                sb_email.setText(rs.getString("Email"));
                sb_login.setText(rs.getString("Login"));
                aktualne_haslo = rs.getString("Haslo");

            }
        } catch (Exception e) {
            helper.error(e.getMessage());
        }
    }

    @FXML
    /**
     * Metoda pobiera wartości z pól tekstowych i aktualizuje dane użytkownika w
     * bazie danych. Zmiana hasła wymaga wpisania aktualnego hasła, w przypadku
     * braku zgodności haseł wyświetlany jest błąd i dane nie są aktualizowane.
     */
    private void zapiszZmiany(ActionEvent event) {

        try {
            int poprawnosc = 1;
            Statement stmt = sesja.createStatement();
            String new_miejscowosc = sb_miejscowosc.getText();
            String new_adres = sb_adres.getText();
            String new_telefon = sb_telefon.getText();
            String new_email = sb_email.getText();
            String old_password = sb_haslo.getText();
            String new_password = sb_nowe_haslo.getText();

            if (new_password.length() > 0) {

                if (old_password.equals(aktualne_haslo)) {

                } else {
                    poprawnosc = 0;
                    helper.error("Hasła nie zgadzają się");

                }

            } else {
                new_password = aktualne_haslo;
            }

            if (poprawnosc == 1) {
                aktualne_haslo = new_password;
                int wynik = stmt.executeUpdate("update pracownik set Miejscowosc = '" + new_miejscowosc
                        + "', Adres = '" + new_adres
                        + "', Telefon = '" + new_telefon
                        + "', Email = '" + new_email
                        + "', Haslo = '" + new_password
                        + "' where Login = '" + username + "';");
                if (wynik == 1) {

                    helper.message("Ustawienia zostały zapisane");
                }

            }
        } catch (Exception e) {
            helper.error(e.getMessage());
        }

    }

    @FXML
    private void powrtoDoMenu(ActionEvent event) throws IOException {
        try {
            sesja.close();
        } catch (Exception e) {
            helper.error(e.getMessage());
        }
        helper.powrotDoMenu();
        Stage settings = (Stage) powrot.getScene().getWindow();
        settings.close();
    }

}
