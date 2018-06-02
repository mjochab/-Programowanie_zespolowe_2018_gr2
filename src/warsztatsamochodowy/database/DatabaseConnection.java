package warsztatsamochodowy.database;
import java.sql.*;
import warsztatsamochodowy.Helper;

/**
 * 
 * 
 * 
 * @author Tomek Galka
 */

public class DatabaseConnection {

          Connection con;
    private Helper helper = new Helper();
    
   
    public Connection connectDatabase(){
        /**
         *  Proba polaczenia z lokalna baza danych
         * 
         */
         
                try{
              
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztat?useSSL=false","root","");
                    return con;
                
/*
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("select * from pracownik");
                    while(rs.next())
                        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+ "  " + rs.getString(3)+ "  " +rs.getString(4)+"   "+ rs.getString(5)+"  "+rs.getString(6));
                    con.close();

*/
    /**
     * Wyrzuca wyjatek w razie blednego polaczenia z baza danych
     * 
     * 
     */
                }catch(Exception e){ 
                       helper.error(e.getMessage());
                return null;
                }
                  
            }



}
