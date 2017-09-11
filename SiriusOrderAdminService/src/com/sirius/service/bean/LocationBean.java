package com.sirius.service.bean;

import java.math.BigDecimal;

/**
 * Location bean
 * 
 * @author Scout Martinelli
 */
public class LocationBean {
	private int id = 0;
	private String city = "";
	private String state = "";
	private int adminId = 0;
	private BigDecimal recommendedBudget;
	private BigDecimal alottedBudget;
	
	// id ---------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// city -------------------------------------
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	// state ------------------------------------
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	// admin id ---------------------------------
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	// recommended budget -----------------------
	public BigDecimal getRecommendedBudget() {
		return recommendedBudget;
	}
	public void setRecommendedBudget(BigDecimal recommendedBudget) {
		this.recommendedBudget = recommendedBudget;
	}
	
	// alloted budget ---------------------------
	public BigDecimal getAlottedBudget() {
		return alottedBudget;
	}
	public void setAlottedBudget(BigDecimal alottedBudget) {
		this.alottedBudget = alottedBudget;
	}
	
	// toString ---------------------------------
	@Override
	public String toString() {
		return "LocationBean [id=" + id + ", city=" + city + ", state=" + state
				+ ", adminId=" + adminId + ", recommendedBudget="
				+ recommendedBudget + ", alottedBudget=" + alottedBudget + "]";
	}
	
	

}
