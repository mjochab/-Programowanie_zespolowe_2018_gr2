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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import warsztatsamochodowy.Helper;
import warsztatsamochodowy.database.entity.Repair;
import warsztatsamochodowy.database.entity.RepairWorker;
import warsztatsamochodowy.database.entity.Klient;
import warsztatsamochodowy.database.entity.Pracownik;
import warsztatsamochodowy.database.entity.Samochod;
import warsztatsamochodowy.database.entity.Task;
import warsztatsamochodowy.viewmodels.RaportsVM;
import warsztatsamochodowy.viewmodels.RepairWorkerVM;

/**
 *
 * @author Piotr Świder
 */
public class DBHelper {

    private static DatabaseConnection dbConnection;

    private static Connection connection;

    private static DBHelper instance;

    private static Helper helper;

    public DBHelper() {
        helper = new Helper();
        dbConnection = new DatabaseConnection();
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    private static void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dbConnection.connectDatabase();
        }
    }

    public static void closeHelper() {
        try {
            if (instance != null && !connection.isClosed()) {
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
            s.execute("SELECT k.*, s.SamochodId as ID_SAM, s.Producent, s.Model, s.Typ, s.VIN FROM klient k INNER JOIN "
                    + "samochod s ON k.KlientId = s.Klient ORDER BY k.KlientId ASC");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                klienci.add(new Klient(rs.getLong("KlientId"), rs.getString("Imie"), rs.getString("Nazwisko"),
                        rs.getString("Telefon"), rs.getString("Miejscowosc"), rs.getString("Adres"), rs.getString("Email"),
                        new Samochod(rs.getLong("ID_SAM"), rs.getString("VIN"), rs.getString("Producent"), rs.getString("Model"), rs.getString("Typ"))));

            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return klienci;
    }
    public List<RaportsVM> getAllClientsForPDF(){
        List<RaportsVM> clients = new ArrayList<>();
        String query = "SELECT n.napraw_id, k.Imie, k.Nazwisko, n.opis, n.koszt FROM naprawa n inner join klient k on n.id_klienta = k.KlientId";
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute(query);
          
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                clients.add(new RaportsVM(
                        rs.getInt("napraw_id"),
                        rs.getString("Imie"),
                        rs.getString("Nazwisko"),
                        rs.getString("opis"),
                        rs.getLong("koszt")));

            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return clients;
    }
    public List<Repair> getAllTasks() {
        List<Repair> fix = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT n.napraw_id, n.koszt, n.status, n.opis, p.PracownikId, p.Imie, p.Nazwisko, \n" +
"                    k.KlientId, k.Imie, k.Nazwisko, \n" +
"                    s.SamochodId, s.producent, s.model\n" +
"                   FROM naprawa as n \n" +
"                    inner join naprawa_pracownika np on n.napraw_id = np.id_naprawy \n" +
"                    INNER JOIN pracownik p ON p.PracownikId = np.id_pracownika \n" +
"                    INNER JOIN klient k ON k.KlientId = n.id_klienta \n" +
"                    INNER JOIN samochod s ON s.Klient = k.KlientId");
          
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                fix.add(new Repair(rs.getLong("napraw_id"), rs.getString("koszt"), rs.getString("status"), rs.getString("opis"), 
                        new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko")),
                        new Klient(rs.getLong("KlientId"), rs.getString("Imie"), rs.getString("Nazwisko")),
                        new Samochod(rs.getLong("SamochodId"), rs.getString("Producent"), rs.getString("Model"))));

            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return fix;
    }
   

    public List<RepairWorkerVM> getAllWorkersAssignedToRepairs() {
        List<RepairWorkerVM> repairWorker = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("select p.PracownikId, s.SamochodId,n.napraw_id, p.Imie, p.Nazwisko, s.Producent, s.Model, n.status from naprawa_pracownika np "
                    + "inner join naprawa n on np.id_naprawy = n.napraw_id\n"
                    + "inner join pracownik p on np.id_pracownika = p.PracownikId\n"
                    + "inner join klient k on n.id_klienta = k.KlientId\n"
                    + "inner join samochod s on k.KlientId = s.Klient");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                repairWorker.add(new RepairWorkerVM(
                        rs.getInt("napraw_id"),
                        rs.getInt("SamochodId"),
                        rs.getInt("PracownikId"),
                                                    
                        rs.getString("Imie"),
                        rs.getString("Nazwisko"),
                        rs.getString("Model"),
                        rs.getString("Producent"),
                        rs.getString("Status")));
            }
            rs.close();
            s.close();

        } catch (SQLException ex) {
            helper.error(ex.getMessage());
        }
        return repairWorker;
    }

    public List<Samochod> getCars() {
        List<Samochod> cars = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT s.*, k.KlientId as ID_KLI, k.Imie, k.Nazwisko FROM samochod s "
                    + "INNER JOIN klient k ON k.KlientId = s.Klient ORDER BY s.SamochodId ASC");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                cars.add(new Samochod(rs.getLong("SamochodId"), rs.getString("VIN"), rs.getString("Producent"),
                        rs.getString("Model"), rs.getString("Typ"),
                        new Klient(rs.getLong("ID_KLI"), rs.getString("Imie"), rs.getString("Nazwisko"))));

            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return cars;
    }

    public List<Pracownik> getAllWorkers() {
        List<Pracownik> workers = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT * from pracownik");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                workers.add(new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko"), rs.getString("Status")));
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        if (workers == null || workers.isEmpty()) {
            helper.error("Lista pracownikow jest pusta, dodaj nowych pracownikow");
            return null;
        }
        for (Pracownik w : workers) {
            System.out.println(w.getImie());
        }
        return workers;
    }

    public Klient getKlientById(Long id) {
        Klient klient = null;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT k.*, s.SamochodId as ID_SAM, s.Producent, s.Model, s.Typ, s.VIN FROM klient k LEFT JOIN "
                    + "samochod s ON k.KlientId = s.Klient WHERE k.KlientId = ? ORDER BY k.KlientId ASC");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                klient = new Klient(rs.getLong("KlientId"), rs.getString("Imie"), rs.getString("Nazwisko"),
                        rs.getString("Telefon"), rs.getString("Miejscowosc"), rs.getString("Adres"), rs.getString("Email"),
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
                    "SELECT s.*, k.KlientId as ID_KLI, k.Imie, k.Nazwisko FROM samochod s "
                    + "JOIN klient k ON k.KlientId = s.Klient WHERE s.SamochodId = ? ORDER BY s.SamochodId ASC");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = new Samochod(rs.getLong("SamochodId"), rs.getString("VIN"), rs.getString("Producent"),
                        rs.getString("Model"), rs.getString("Typ"),
                        new Klient(rs.getLong("ID_KLI"), rs.getString("Imie"), rs.getString("Nazwisko")));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return car;
    }

    public void addOrUpdateKlient(Klient klient) {
        try {
            checkConnection();
            String query = "INSERT INTO klient(Imie, Nazwisko, Telefon, Miejscowosc, Adres, Email) VALUES(?, ?, ?, ?, ?, ?)";
            if(klient.getId() != null) {
                query = "UPDATE klient SET Imie=?, Nazwisko=?, Telefon=?, Miejscowosc=?, Adres=?, Email=? WHERE ID=?";
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, klient.getImie());
            ps.setString(2, klient.getNazwisko());
            ps.setString(3, klient.getNrTel());
            ps.setString(4, klient.getMiejscowosc());
            ps.setString(5, klient.getAdres());
            ps.setString(6, klient.getEmail());
            if (klient.getId() != null) {
                ps.setLong(7, klient.getId());
                ps.executeUpdate();
            } else {
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    klient.setId(rs.getLong(1));
                }
                rs.close();
            }
            ps.close();
            klient.getSamochod().setKlient(klient);
            addOrUpdateCar(klient.getSamochod());
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }

    }

    public void addOrUpdateNaprawa(Klient client) {
        try {
            Date now = new Date();
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO naprawa(Klient, Koszt, Samochod, Data_rozpoczecia,Data_zakonczenia, Opis,Status) VALUES(?, ?, ?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            System.out.println(client.getId());
            ps.setLong(1, client.getId());
            ps.setFloat(2, 0);
            ps.setLong(3, client.getSamochod().getId());
            ps.setTimestamp(4, new Timestamp(now.getTime()));
            ps.setTimestamp(5, new Timestamp(now.getTime()));
            ps.setString(6, "");
            ps.setString(7, "");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            helper.error(ex.getMessage());
        }
    }

    public void addWorkerToFix(long fixId, int workerId) {
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO naprawa_pracownika(Naprawa,Pracownik) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, fixId);
            ps.setInt(2, workerId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
    }

    public void addOrUpdateCar(Samochod car) {
        try {
            checkConnection();
            String query = "INSERT INTO samochod(VIN, Producent, Model, Typ, Klient) VALUES(?, ?, ?, ?, ?)";
            if(car.getId() != null) {
                query = "UPDATE samochod SET VIN=?, Producent=?, Model=?, Typ=? WHERE ID=?";
            }
            PreparedStatement ps = connection.prepareStatement(
            query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getVin());
            ps.setString(2, car.getProducent());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getTyp());
            if(car.getId() != null) {
                ps.setLong(5, car.getId());
                ps.executeUpdate();
            } else {
                ps.setLong(5, car.getKlient().getId());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    car.setId(rs.getLong(1));
                }
                rs.close();
            }
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }

    }
    
    public boolean checkKlientAndCarHaveDependency(Klient k) {
        boolean result = false;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT 1 from naprawa where Klient=? or Samochod=?");
            ps.setLong(1, k.getId());
            ps.setLong(2, k.getSamochod().getId());
            ResultSet rs = ps.executeQuery();
            result = rs.next();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteKlient(Long id) {
        boolean result = false;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement("delete from klient where ID=?");
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
            PreparedStatement ps = connection.prepareStatement("delete from Samochod s where s.SamochodId = ?");
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
