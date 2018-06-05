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

    public List<Repair> getAllTasks() {
        List<Repair> fix = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("SELECT n.napraw_id, n.koszt, n.status, n.opis, p.PracownikId, p.Imie, p.Nazwisko, "
                    + "k.KlientId, k.Imie, k.Nazwisko, "
                    + "s.SamochodId, s.producent, s.model"
                    + "FROM napraw2 as n "
                    + "INNER JOIN pracownik p ON p.PracownikId = n.id_pracownika "
                    + "INNER JOIN klient k ON k.KlientId = n.id_klienta "
                    + "INNER JOIN samochod s ON s.SamochodId = n.id_samochodu ");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                fix.add(new Repair(rs.getLong("naprawa_id"), rs.getString("koszt"), rs.getString("status"), rs.getString("opis"), 
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
    
//        public List<Repair> getAllTasks() {
//        List<Repair> fix = new ArrayList<>();
//        try {
//            checkConnection();
//            Statement s = connection.createStatement();
//            s.execute("SELECT n.*, p.PracownikId, p.Imie, p.Nazwisko "
//                    + "FROM napraw2 as n "
//                    + "INNER JOIN pracownik p ON p.PracownikId = n.id_pracownika ");
//            ResultSet rs = s.getResultSet();
//            while (rs.next()) {
//                fix.add(new Repair(rs.getLong("napraw_id"), rs.getString("Koszt"), rs.getString("Status"), rs.getString("Opis"), 
//                        new Pracownik(rs.getInt("PracownikId"), rs.getString("Imie"), rs.getString("Nazwisko"))));
//
//            }
//            rs.close();
//            s.close();
//        } catch (SQLException e) {
//            helper.error(e.getMessage());
//        }
//        return fix;
//    }

    public List<RepairWorkerVM> getAllWorkersAssignedToRepairs() {
        List<RepairWorkerVM> repairWorker = new ArrayList<>();
        try {
            checkConnection();
            Statement s = connection.createStatement();
            s.execute("select p.PracownikId, s.SamochodId,n.NaprawaId, p.Imie, p.Nazwisko, s.Producent, s.Model from naprawa_pracownik np\n" +
                    + "inner join naprawa n on np.Naprawa = n.NaprawaId\n"
                    + "inner join pracownik p on np.Pracownik = p.PracownikId\n"
                    + "inner join klient k on n.Klient = k.KlientId\n"
                    + "inner join samochod s on n.Samochod = s.SamochodId");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                repairWorker.add(new RepairWorkerVM(
                        rs.getInt("SamochodId"),
                                                    rs.getInt("PracownikId"),
                                                    rs.getInt("NaprawaId"),
                        rs.getString("Imie"),
                        rs.getString("Nazwisko"),
                        rs.getString("Model"),
                        rs.getString("Producent")));
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

    public void addWorkerToFix(int fixId, int workerId) {
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO naprawa_pracownik(Naprawa,Pracownik) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, fixId);
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
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO samochod(VIN, Producent, Model, Typ, Klient) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getVin());
            ps.setString(2, car.getProducent());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getTyp());
            ps.setLong(5, car.getKlient().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                car.setId(rs.getLong(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            helper.error(e.getMessage());
        }

    }

    public boolean deleteKlient(Long id) {
        boolean result = false;
        try {
            checkConnection();
            PreparedStatement ps = connection.prepareStatement("delete from Klient k where k.KlientId = ?");
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
