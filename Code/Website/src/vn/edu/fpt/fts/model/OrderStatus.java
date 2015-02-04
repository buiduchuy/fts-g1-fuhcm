package vn.edu.fpt.fts.model;
// Generated Feb 4, 2015 3:30:12 PM by Hibernate Tools 3.6.0


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderStatus generated by hbm2java
 */
public class OrderStatus  implements java.io.Serializable {


     private int orderStatusId;
     private Serializable orderStatusName;
     private Set<Order> orders = new HashSet<Order>(0);

    public OrderStatus() {
    }

	
    public OrderStatus(int orderStatusId, Serializable orderStatusName) {
        this.orderStatusId = orderStatusId;
        this.orderStatusName = orderStatusName;
    }
    public OrderStatus(int orderStatusId, Serializable orderStatusName, Set<Order> orders) {
       this.orderStatusId = orderStatusId;
       this.orderStatusName = orderStatusName;
       this.orders = orders;
    }
   
    public int getOrderStatusId() {
        return this.orderStatusId;
    }
    
    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
    public Serializable getOrderStatusName() {
        return this.orderStatusName;
    }
    
    public void setOrderStatusName(Serializable orderStatusName) {
        this.orderStatusName = orderStatusName;
    }
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }




}


