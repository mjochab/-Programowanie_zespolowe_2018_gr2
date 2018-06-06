/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.viewmodels;

/**
 *
 * @author lukasz
 */
public class RepairWorkerVM {
    private int FixId;
    private int CarId;
    private int WorkerId;
    private String Imie;
    private String Nazwisko;
    private String Model;
    private String Producent;
    private String Status;
    
    public RepairWorkerVM(int CarId, int WorkerId, int FixId, String Imie, String Nazwisko, String Model, String Producent) {
        this.CarId = CarId;
        this.WorkerId = WorkerId;
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.Model = Model;
        this.Producent = Producent;
        this.FixId = FixId;
    }

    public RepairWorkerVM(int FixId, int CarId, int WorkerId, String Imie, String Nazwisko, String Model, String Producent, String Status) {
        this.FixId = FixId;
        this.CarId = CarId;
        this.WorkerId = WorkerId;
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.Model = Model;
        this.Producent = Producent;
        this.Status = Status;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int CarId) {
        this.CarId = CarId;
    }

    public int getWorkerId() {
        return WorkerId;
    }

    public void setWorkerId(int WorkerId) {
        this.WorkerId = WorkerId;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Marka) {
        this.Model = Marka;
    }

    public String getProducent() {
        return Producent;
    }

    public void setProducent(String Producent) {
        this.Producent = Producent;
    }

    public int getFixId() {
        return FixId;
    }

    public void setFixId(int FixId) {
        this.FixId = FixId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
