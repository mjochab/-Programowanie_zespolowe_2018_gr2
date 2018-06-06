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
public class RaportsVM {
    private int RepairId;
    private String clientName;
    private String lastName;
    
    private String desc;
    private long cost;

    public RaportsVM(int RepairId,String clientName, String lastName, String desc, long cost) {
        this.RepairId = RepairId;
        this.clientName = clientName;
        this.lastName = lastName;
        this.desc = desc;
        this.cost = cost;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getRepairId() {
        return RepairId;
    }

    public void setRepairId(int RepairId) {
        this.RepairId = RepairId;
    }
    
    
}
