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
	
	public ProductBean[] getAllProductsByType(String type)
	{
		return ProductDAO.getAllProductsByType(type);
	}
}
