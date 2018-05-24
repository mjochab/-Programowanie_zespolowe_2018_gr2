/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Samochod;

/**
 *
 * @author Piotr Świder
 */
public class DBHelper {
    
    private static DatabaseConnection dbConnection;

    private static Connection connection;
    
    private static DBHelper instance;
    
    private static Helper helper;

    private DBHelper() {
        helper = new Helper();
        dbConnection = new DatabaseConnection();
    }
    
    public static DBHelper getInstance() {
        if(instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }
    
    private static void checkConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            connection = dbConnection.connectDatabase();
        }
    }
    
     public static void closeHelper(){
         try {
         if(instance != null && !connection.isClosed()) {
           connection.close();
         }
         } catch (SQLException e) {
             helper.error(e.getMessage());
         }
    }
     
    public List<Klient> getKlienci() {
        List<Klient> klienci = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT k.*, s.id as ID_SAM, s.Producent, s.Model, s.Typ, s.VIN FROM klient k LEFT JOIN "
                    + "samochod s ON k.ID = s.Klient ORDER BY k.ID ASC");
            ResultSet rs = s.getResultSet();
            while(rs.next()) {
                klienci.add(new Klient(rs.getLong("ID"), rs.getString("Imie"), rs.getString("Nazwisko"), 
                        rs.getString("Telefon"),  rs.getString("Miejscowosc"), rs.getString("Adres"), rs.getString("Email"), 
                        new Samochod(rs.getLong("ID_SAM"), rs.getString("VIN"), rs.getString("Producent"), rs.getString("Model"), rs.getString("Typ"))));
                
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return klienci;
    }

    public List<Samochod> getCars() {
        List<Samochod> cars = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT s.*, k.ID as ID_KLI, k.Imie, k.Nazwisko FROM samochod s "
                    + "JOIN klient k ON k.ID = s.Klient ORDER BY s.ID ASC");
            ResultSet rs = s.getResultSet();
            while(rs.next()) {
                cars.add(new Samochod(rs.getLong("ID"), rs.getString("VIN"), rs.getString("Producent"), 
                        rs.getString("Model"),  rs.getString("Typ"),  
                        new Klient(rs.getLong("ID_KLI"), rs.getString("Imie"), rs.getString("Nazwisko"))));
                
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        } 
        return cars;
    }
  
    public Klient getKlientById(Long id) {
        Klient klient = null;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
            "SELECT k.*, s.id as ID_SAM, s.Producent, s.Model, s.Typ, s.VIN FROM klient k LEFT JOIN "
                    + "samochod s ON k.ID = s.Klient WHERE k.ID = ? ORDER BY k.ID ASC");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                klient = new Klient(rs.getLong("ID"), rs.getString("Imie"), rs.getString("Nazwisko"), 
                        rs.getString("Telefon"),  rs.getString("Miejscowosc"), rs.getString("Adres"), rs.getString("Email"), 
                        new Samochod(rs.getLong("ID_SAM"), rs.getString("VIN"), rs.getString("Producent"), rs.getString("Model"), rs.getString("Typ")));
                
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return klient;
    }
    
    public Samochod getCarById(Long id) {
        Samochod car = null;
       try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
            "SELECT s.*, k.ID as ID_KLI, k.Imie, k.Nazwisko FROM samochod s "
                    + "JOIN klient k ON k.ID = s.Klient WHERE s.ID = ? ORDER BY s.ID ASC");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                car = new Samochod(rs.getLong("ID"), rs.getString("VIN"), rs.getString("Producent"), 
                        rs.getString("Model"),  rs.getString("Typ"),  
                        new Klient(rs.getLong("ID_KLI"), rs.getString("Imie"), rs.getString("Nazwisko")));
                
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        } 
        return car;
    }

    public Klient addOrUpdateKlient(Klient klient) {
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO klient(Imie, Nazwisko, Telefon, Miejscowosc, Adres, Email) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, klient.getImie());
            ps.setString(2, klient.getNazwisko());
            ps.setString(3, klient.getNrTel());
            ps.setString(4, klient.getMiejscowosc());
            ps.setString(5, klient.getAdres());
            ps.setString(6, klient.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                klient.setId(rs.getLong(1));
            }
            rs.close();
            ps.close();
            klient.getSamochod().setKlient(klient);
            addOrUpdateCar(klient.getSamochod());
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return klient;
    }

    public Samochod addOrUpdateCar(Samochod car) {
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO samochod(VIN, Producent, Model, Typ, Klient) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getVin());
            ps.setString(2, car.getProducent());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getTyp());
            ps.setLong(5, car.getKlient().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                car.setId(rs.getLong(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        } 
        return car;
    }

    public boolean deleteKlient(Long id) {
        boolean result = false;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement("delete from Klient k where k.ID = ?");
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            if (res == 1) {
                result = true;
            }
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteCar(Long id) {
        boolean result = false;
        try {
           checkConnection();
            PreparedStatement ps = connection.prepareStatement("delete from Samochod s where s.id = ?");
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            if (res == 1) {
                result = true;
            }
           ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return result;
    }
}
