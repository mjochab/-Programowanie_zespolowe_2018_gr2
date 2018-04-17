package warsztatsamochodowy.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bartek
 */
public class DatabaseDelete {

    private static DatabaseConnection PolaczenieDB = new DatabaseConnection();
    private static Connection sesja;

    public static void main(String[] args) {
        try {
            PolaczenieDB.connectDatabase();
            try {
                Statement stmt = sesja.createStatement();
                String query = "DELETE FROM klient,samochod";
                int deletedRows = stmt.executeUpdate(query);
                if (deletedRows > 0) {
                    System.out.println("Wszystkie rekordy z bazy usunieto pomyslnie...");
                } else {
                    System.out.println("Tabele juz sa puste.");
                }

            } catch (SQLException s) {
                System.out.println("Blad podczas usuwania. ");
                s.printStackTrace();
            }
            // close Connection
            sesja.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
