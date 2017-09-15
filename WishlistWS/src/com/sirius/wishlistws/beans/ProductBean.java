package com.sirius.wishlistws.beans;

import java.math.BigDecimal;

public class ProductBean {
	private int id;
	private String name;
	private int type;
	private BigDecimal price;
	private String details;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", type=" + type
				+ ", price=" + price + ", details=" + details + "]";
	}
	
}
