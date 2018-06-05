package warsztatsamochodowy.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.DatabaseConnection;

/**
 *
 * @author Sc00rpY
 *
 *
 * Test usuwania czesci z magazynu
 *
 */
public class PartsControllerTest {

    public PartsControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {

        DatabaseConnection PolaczenieDB = new DatabaseConnection();
        Connection sesja;
        sesja = PolaczenieDB.connectDatabase();
        Statement stmt = sesja.createStatement();

        int wynik = stmt.executeUpdate("INSERT INTO czesc (CzescID,Nazwa,Producent,Ilosc,Cena) VALUES ('"
                + 999999
                + "', '" + "junit"
                + "', '" + "junit"
                + "', '" + 1
                + "', '" + 1
                + "');");
    }

    @Test
    public void testUsunCzesci() {
        String nazwa_testu = "Test usuwania czesci z magazynu";
        PartsController instance = new PartsController();
        // instance.junit = true;
        boolean wynik = instance.usunCzesci("999999");
        assertTrue(wynik);
        System.out.println(nazwa_testu + " przebiegl pomyślnie");

    }

}
