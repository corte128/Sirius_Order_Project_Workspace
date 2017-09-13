package com.sirius.product.service.main;

import javax.jws.WebService;

import com.sirius.product.service.beans.ProductBean;

@WebService(endpointInterface="com.sirius.product.service.main.Product",
portName="product", targetNamespace ="http://main.service.product.sirius.com/product/wsdl",
serviceName="ProductService")
public class ProductImplementation implements Product
{
	public ProductBean getProductByID(int ID)
	{
		return ProductDAO.getProductByID(ID);
	}
	
	public ProductBean[] getAllProductsByType(String type)
	{
		return ProductDAO.getAllProductsByType(type);
	}
}
