package vn.edu.fpt.fts.model;
// Generated Feb 4, 2015 3:30:12 PM by Hibernate Tools 3.6.0


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * DealStatus generated by hbm2java
 */
public class DealStatus  implements java.io.Serializable {


     private int dealStatusId;
     private Serializable dealStatusName;
     private Set<Deal> deals = new HashSet<Deal>(0);

    public DealStatus() {
    }

	
    public DealStatus(int dealStatusId, Serializable dealStatusName) {
        this.dealStatusId = dealStatusId;
        this.dealStatusName = dealStatusName;
    }
    public DealStatus(int dealStatusId, Serializable dealStatusName, Set<Deal> deals) {
       this.dealStatusId = dealStatusId;
       this.dealStatusName = dealStatusName;
       this.deals = deals;
    }
   
    public int getDealStatusId() {
        return this.dealStatusId;
    }
    
    public void setDealStatusId(int dealStatusId) {
        this.dealStatusId = dealStatusId;
    }
    public Serializable getDealStatusName() {
        return this.dealStatusName;
    }
    
    public void setDealStatusName(Serializable dealStatusName) {
        this.dealStatusName = dealStatusName;
    }
    public Set<Deal> getDeals() {
        return this.deals;
    }
    
    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }




}


