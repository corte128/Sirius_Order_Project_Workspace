package com.sirius.adminws.beans;

import java.math.BigDecimal;

public class OfficeBean{
	private String location = "";
	private String adminName = "";
	private String adminEmail = "";
	private int numberOfEmployees = 0;
	private BigDecimal recommendedBudget = null;
	private BigDecimal allottedBudget = null;

	// Location ------------------------------------
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// Admin Name ----------------------------------
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	// Admin Email ---------------------------------
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	// Number of Employees -------------------------
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	// Recommended Budget --------------------------
	public BigDecimal getRecommendedBudget() {
		return recommendedBudget;
	}

	public void setRecommendedBudget(BigDecimal recommendedBudget) {
		this.recommendedBudget = recommendedBudget;
	}

	// Allotted Budget -----------------------------
	public BigDecimal getAllottedBudget() {
		return allottedBudget;
	}

	public void setAllottedBudget(BigDecimal allottedBudget) {
		this.allottedBudget = allottedBudget;
	}

}
