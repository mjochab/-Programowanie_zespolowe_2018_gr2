
package warsztatsamochodowy.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static warsztatsamochodowy.controllers.LoginControllerTest.login;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 *
 * @author Sc00rpY
 *
 *
 * Test pobierania ustawien uzytkownika
 *
 */
public class UserSettingsControllerTest {
    
    String username = "";
    
    public UserSettingsControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        
                DatabaseConnection PolaczenieDB = new DatabaseConnection();
        Connection sesja = PolaczenieDB.connectDatabase();

        Statement stmt = sesja.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT Login from pracownik LIMIT 1");
        while (rs.next()) {

            login = rs.getString("Login");
            break;
        }
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testPobierzDane() {
        String nazwa_testu = "Test pobierania ustawien uzytkownika";
        UserSettingsController instance = new UserSettingsController();
        instance.junit = true;
        boolean wynik = instance.pobierzDane(login);
        assertTrue(wynik);
        System.out.println(nazwa_testu + " przebiegl pomyślnie");
    }
    
}
