package com.sirius.service.generate.beans;

public class KnapsackBean{
	private int xCor = -1;
	private int yCor = -1;
	private int weight = -1;
	private int productId = -1;
	
	public int getxCor() {
		return xCor;
	}
	public void setxCor(int xCor) {
		this.xCor = xCor;
	}
	
	public int getyCor() {
		return yCor;
	}
	public void setyCor(int yCor) {
		this.yCor = yCor;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "KnapsackBean [xCor=" + xCor + ", yCor=" + yCor + ", weight="
				+ weight + ", productId=" + productId + "]\n";
	}
	
}
