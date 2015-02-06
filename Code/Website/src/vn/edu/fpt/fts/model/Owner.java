package vn.edu.fpt.fts.model;

// Generated Feb 4, 2015 3:30:12 PM by Hibernate Tools 3.6.0

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Owner generated by hbm2java
 */
public class Owner implements java.io.Serializable {

	private int ownerId;
	private Serializable email;
	private Serializable firstName;
	private Serializable lastName;
	private Integer sex;
	private Serializable phone;
	private Serializable address;
	private boolean isActive;
	private Serializable createBy;
	private Date createTime;
	private Serializable updateBy;
	private Date updateTime;
	private Set<Goods> goodses = new HashSet<Goods>(0);
	private Set<Account> accounts = new HashSet<Account>(0);

	public Owner() {
	}

	public Owner(int ownerId, Serializable email, Serializable phone,
			boolean isActive, Serializable createBy, Date createTime,
			Serializable updateBy, Date updateTime) {
		this.ownerId = ownerId;
		this.email = email;
		this.phone = phone;
		this.isActive = isActive;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

	public Owner(int ownerId, Serializable email, Serializable firstName,
			Serializable lastName, Integer sex, Serializable phone,
			Serializable address, boolean isActive, Serializable createBy,
			Date createTime, Serializable updateBy, Date updateTime,
			Set<Goods> goodses, Set<Account> accounts) {
		this.ownerId = ownerId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.isActive = isActive;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.goodses = goodses;
		this.accounts = accounts;
	}

	public int getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Serializable getEmail() {
		return this.email;
	}

	public void setEmail(Serializable email) {
		this.email = email;
	}

	public Serializable getFirstName() {
		return this.firstName;
	}

	public void setFirstName(Serializable firstName) {
		this.firstName = firstName;
	}

	public Serializable getLastName() {
		return this.lastName;
	}

	public void setLastName(Serializable lastName) {
		this.lastName = lastName;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Serializable getPhone() {
		return this.phone;
	}

	public void setPhone(Serializable phone) {
		this.phone = phone;
	}

	public Serializable getAddress() {
		return this.address;
	}

	public void setAddress(Serializable address) {
		this.address = address;
	}

	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Serializable getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Serializable createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Serializable getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Serializable updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}