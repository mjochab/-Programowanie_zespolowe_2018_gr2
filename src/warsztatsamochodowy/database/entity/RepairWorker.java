/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

/**
 *
 * @author lukasz
 */
public class RepairWorker {
    private int FixWorkerId;
    private int fixId;
    private int pracownikId;

    public RepairWorker(int FixWorkerId, int fixId, int pracownikId) {
        this.FixWorkerId = FixWorkerId;
        this.fixId = fixId;
        this.pracownikId = pracownikId;
    }
    
    

    public int getFixWorkerId() {
        return FixWorkerId;
    }

    public void setFixWorkerId(int FixWorkerId) {
        this.FixWorkerId = FixWorkerId;
    }

    public int getFixId() {
        return fixId;
    }

    public void setFixId(int fixId) {
        this.fixId = fixId;
    }

    public int getPracownikId() {
        return pracownikId;
    }

    public void setPracownikId(int pracownikId) {
        this.pracownikId = pracownikId;
    }
    
    
}
