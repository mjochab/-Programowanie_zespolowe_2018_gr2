package warsztatsamochodowy.database;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sc00rpY
 *
 * 
 * Test połączenia z bazą danych
 * 
*/


public class DatabaseConnectionTest {

    public DatabaseConnectionTest() {
    }

    @Test
    public void testConnectDatabase() {
        String nazwa_testu = "Test połączenia z bazą";
        System.out.println(nazwa_testu);
        DatabaseConnection instance = new DatabaseConnection();
        Connection conn = instance.connectDatabase();
        assertNotNull(conn);
        System.out.println(nazwa_testu + " przeszedł pomyślnie");

    }

}
