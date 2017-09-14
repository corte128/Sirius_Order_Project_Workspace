package com.sirius.service.cart.bean;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetBean {
	private int id = 0;
	private int orderId = 0;
	private int locationId = 0;
	private Date budgetDate = null;
	private BigDecimal budgetAllotted = null;
	private BigDecimal budgetRecommended = null;
	
	// ID -----------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// Order ID -----------------------------------
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	// Location ID --------------------------------
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	// Budget Date --------------------------------
	public Date getBudgetDate() {
		return budgetDate;
	}
	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}
	
	// Budget Allotted ----------------------------
	public BigDecimal getBudgetAllotted() {
		return budgetAllotted;
	}
	public void setBudgetAllotted(BigDecimal budgetAllotted) {
		this.budgetAllotted = budgetAllotted;
	}
	
	// Budget Recommended -------------------------
	public BigDecimal getBudgetRecommended() {
		return budgetRecommended;
	}
	public void setBudgetRecommended(BigDecimal budgetRecommended) {
		this.budgetRecommended = budgetRecommended;
	}
	
	

}
