package com.sirius.searchws.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class ActualvBudgetBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;
	private BigDecimal actual;
	private BigDecimal budget;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getActual() {
		return actual;
	}
	public void setActual(BigDecimal actual) {
		this.actual = actual;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	
	
}
