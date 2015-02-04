package vn.edu.fpt.fts.model;
// Generated Feb 4, 2015 3:30:12 PM by Hibernate Tools 3.6.0


import java.io.Serializable;

/**
 * Account generated by hbm2java
 */
public class Account  implements java.io.Serializable {


     private int userId;
     private Owner owner;
     private Employee employee;
     private Driver driver;
     private Role role;
     private Serializable password;

    public Account() {
    }

    public Account(int userId, Owner owner, Employee employee, Driver driver, Role role, Serializable password) {
       this.userId = userId;
       this.owner = owner;
       this.employee = employee;
       this.driver = driver;
       this.role = role;
       this.password = password;
    }
   
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Owner getOwner() {
        return this.owner;
    }
    
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Driver getDriver() {
        return this.driver;
    }
    
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public Serializable getPassword() {
        return this.password;
    }
    
    public void setPassword(Serializable password) {
        this.password = password;
    }




}


