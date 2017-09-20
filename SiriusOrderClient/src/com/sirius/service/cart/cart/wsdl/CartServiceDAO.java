package com.sirius.service.cart.cart.wsdl;

import java.util.List;

public class CartServiceDAO 
{
	public static boolean addProductToCart(OrderBean order, BudgetBean budget, int createdBy)
	{
		CartProxy client = new CartProxy();
		return client.addProductToCart(order, budget, createdBy);
	}
	public List<OrderBean> getAllProductsInCart(int locationId)
	{
		CartProxy client = new CartProxy();
		return client.getAllProductsInCart(locationId);
	}
	public boolean updateProductQuantityInCart(int locationId, int quantity,
			int productId, int updatedBy) 
	{
		CartProxy client = new CartProxy();
		return client.updateProductQuantityInCart(locationId, quantity, productId, updatedBy);
	}
	public boolean removeProductFromCart(int orderId, int updatedBy) 
	{
		CartProxy client = new CartProxy();
		return client.removeProductFromCart(orderId, updatedBy);
	}
	public boolean saveOrder(String orderName, BudgetBean budget, int locationId, int createdBy) 
	{
		CartProxy client = new CartProxy();
		return client.saveOrder(orderName, budget, locationId, createdBy);
	}
	public List<OrderBean> getOrderByOrderName(String orderName, int locationId)
	{
		CartProxy client = new CartProxy();
		return client.getOrderByOrderName(orderName, locationId);
	}
	public List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType)
	{
		CartProxy client = new CartProxy();
		return client.getAllProductsInCartByProductType(locationId, productType);
	}
}
