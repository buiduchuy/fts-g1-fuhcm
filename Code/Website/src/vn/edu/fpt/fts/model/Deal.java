package vn.edu.fpt.fts.model;
// Generated Feb 4, 2015 3:30:12 PM by Hibernate Tools 3.6.0


import java.io.Serializable;

/**
 * Deal generated by hbm2java
 */
public class Deal  implements java.io.Serializable {


     private int dealId;
     private Goods goods;
     private DealStatus dealStatus;
     private Route route;
     private Order order;
     private double price;
     private Serializable notes;
     private Serializable createTime;
     private int sender;
     private Integer ownerId;
     private boolean isActive;

    public Deal() {
    }

	
    public Deal(int dealId, DealStatus dealStatus, double price, Serializable notes, Serializable createTime, int sender, boolean isActive) {
        this.dealId = dealId;
        this.dealStatus = dealStatus;
        this.price = price;
        this.notes = notes;
        this.createTime = createTime;
        this.sender = sender;
        this.isActive = isActive;
    }
    public Deal(int dealId, Goods goods, DealStatus dealStatus, Route route, Order order, double price, Serializable notes, Serializable createTime, int sender, Integer ownerId, boolean isActive) {
       this.dealId = dealId;
       this.goods = goods;
       this.dealStatus = dealStatus;
       this.route = route;
       this.order = order;
       this.price = price;
       this.notes = notes;
       this.createTime = createTime;
       this.sender = sender;
       this.ownerId = ownerId;
       this.isActive = isActive;
    }
   
    public int getDealId() {
        return this.dealId;
    }
    
    public void setDealId(int dealId) {
        this.dealId = dealId;
    }
    public Goods getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    public DealStatus getDealStatus() {
        return this.dealStatus;
    }
    
    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }
    public Route getRoute() {
        return this.route;
    }
    
    public void setRoute(Route route) {
        this.route = route;
    }
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public Serializable getNotes() {
        return this.notes;
    }
    
    public void setNotes(Serializable notes) {
        this.notes = notes;
    }
    public Serializable getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Serializable createTime) {
        this.createTime = createTime;
    }
    public int getSender() {
        return this.sender;
    }
    
    public void setSender(int sender) {
        this.sender = sender;
    }
    public Integer getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }




}


