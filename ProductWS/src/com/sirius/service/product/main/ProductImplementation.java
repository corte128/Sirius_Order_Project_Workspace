package com.sirius.service.product.main;

import javax.jws.WebService;

import com.sirius.service.product.beans.ProductBean;

@WebService(endpointInterface="com.sirius.service.product.main.Product",
portName="product", targetNamespace ="http://main.service.product.sirius.com/product/wsdl",
wsdlLocation="WEB-INF/wsdl/ProductService.wsdl", serviceName="ProductService")
public class ProductImplementation implements Product
{
	public ProductBean getProductByID(int ID)
	{
		return ProductDAO.getProductByID(ID);
	}
	
	public ProductBean[] getAllProductsByType(int type)
	{
		return ProductDAO.getAllProductsByType(type);
	}
	
	public ProductBean[] getAllProductsByName(String name)
	{
		return ProductDAO.getAllProductsByName(name);
	}
	
	public ProductBean[] getAllProductsByNameAndType(String name, int type)
	{
		return ProductDAO.getAllProductsByNameAndType(name, type);
	}
}
