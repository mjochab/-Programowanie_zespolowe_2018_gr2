
package warsztatsamochodowy.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 *
 * @author Sc00rpY
 *
 *
 * Test dodawania czesci do magazynu
 *
 */

public class AddPartControllerTest {
    
    public AddPartControllerTest() {
    }

    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        
                           DatabaseConnection PolaczenieDB = new DatabaseConnection();
    Connection sesja;
    Statement stmt;
              sesja = PolaczenieDB.connectDatabase();
            stmt = sesja.createStatement();
stmt.executeUpdate("DELETE FROM Czesc WHERE Nazwa = 'junit';");
    }



    @Test
    public void testDodajCzesc() {
        String nazwa_testu = "Test dodawania czesci do magazynu";
        AddPartController instance = new AddPartController();
        instance.junit = true;
        boolean wynik = instance.dodajCzesc("junit","junit","1","1");
        assertTrue(wynik);
        System.out.println(nazwa_testu + " przebiegl pomyślnie");
    }
    
}
