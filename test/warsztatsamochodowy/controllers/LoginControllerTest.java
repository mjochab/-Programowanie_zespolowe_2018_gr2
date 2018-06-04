package warsztatsamochodowy.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 *
 * @author Sc00rpY
 *
 *
 * Test logowania użytkownika do aplikacji
 *
 */
public class LoginControllerTest {

    static String login = "";
    static String haslo = "";

    public LoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {

        DatabaseConnection PolaczenieDB = new DatabaseConnection();
        Connection sesja = PolaczenieDB.connectDatabase();

        Statement stmt = sesja.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT Login, Haslo from pracownik WHERE Status = 'Zatrudniony' LIMIT 1");
        while (rs.next()) {

            login = rs.getString("Login");
            haslo = rs.getString("Haslo");
            break;
        }

    }

    @Test
    public void testsprawdzLogowaniea() throws Exception {
        String nazwa_testu = "Test logowania użytkownika do aplikacji";
        LoginController instance = new LoginController();
        instance.junit = true;
        boolean wynik = instance.sprawdzLogowanie(login, haslo);
        assertTrue(wynik);
        System.out.println(nazwa_testu + " przeszedł pomyślnie");
    }

}
