package com.sirius.product.service.main.product.wsdl;

import java.util.List;


public class ProductSearchDAO {

	public static ProductBean getProductByID(int ID){
		ProductProxy productProxy = new ProductProxy();
		return productProxy.getProductByID(ID);
	}
	
	public static List<ProductBean> getAllProductsByType(String type){
		ProductProxy productProxy = new ProductProxy();
		return productProxy.getAllProductsByName(type);
	}
	
	public static List<ProductBean> getAllProductsByName(String name){
		ProductProxy productProxy = new ProductProxy();
		return productProxy.getAllProductsByName(name);
	}
	
	public static List<ProductBean> getAllProductsByNameAndType(String name, String type){
		ProductProxy productProxy = new ProductProxy();
		return productProxy.getAllProductsByNameAndType(name, type);
	}
	
	
}