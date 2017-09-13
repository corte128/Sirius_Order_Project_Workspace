package com.sirius.service.cart.bean;

import java.math.BigDecimal;

public class OrderBean {
	private int id = 0;
	private String orderName = "";
	private int productId = 0;
	private BigDecimal totalPrice = null;
	private int quantity = 0;
	
	// ID ---------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// Order Name -------------------------------
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	// Product ID ------------------------------
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	// Total Price -----------------------------
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	// Quantity --------------------------------
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
