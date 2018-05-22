package warsztatsamochodowy.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Bartek
 * 
 * Klasa do ladowania danych, przykladowe dane:
 *  CustName OrderNo PhoneNo
 
    XYZ      230     123456789
    ABC      450     879641238
 * Kolumny oddzielone tabulatorem
 * Wiersze oddzielone enterem
 * 
 */
public class DatabaseLoad {

    private static DatabaseConnection PolaczenieDB = new DatabaseConnection();
    private static Connection sesja;

    public static void main(String[] args) {

        PolaczenieDB.connectDatabase();
        try {
            Statement stmt = sesja.createStatement();
            String query
                    = "load data infile 'c:/temp/klient.txt' \n"
                    + "   replace \n"
                    + "   into table klient \n"
                    + "   columns terminated by '\\t' \n"
                    + "   ignore 1 lines";
            stmt.execute(query);
            String query2
                    = "load data infile 'c:/temp/samochod.txt' \n"
                    + "   replace \n"
                    + "   into table samochod \n"
                    + "   columns terminated by '\\t' \n"
                    + "   ignore 1 lines";
            stmt.execute(query2);
            sesja.close();
        } catch (SQLException s) {
            System.out.println("Blad podczas dodawania. ");
            s.printStackTrace();
        }
    }
}
