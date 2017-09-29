package com.sirius.service.cart.cart.wsdl;

import java.util.List;

public class CartServiceDAO 
{
	public static boolean addProductToCart(OrderBean order, BudgetBean budget, int createdBy)
	{
		CartProxy client = new CartProxy();
		return client.addProductToCart(order, budget, createdBy);
	}
	public static List<OrderBean> getAllProductsInCart(int locationId)
	{
		CartProxy client = new CartProxy();
		return client.getAllProductsInCart(locationId);
	}
	public static boolean updateProductQuantityInCart(int locationId, int quantity,
			int productId, int updatedBy) 
	{
		CartProxy client = new CartProxy();
		return client.updateProductQuantityInCart(locationId, quantity, productId, updatedBy);
	}
	public static boolean removeProductFromCart(int orderId, int updatedBy) 
	{
		CartProxy client = new CartProxy();
		return client.removeProductFromCart(orderId, updatedBy);
	}
	public static boolean saveOrder(List<Integer> productIdList, String orderName, BudgetBean budget, int locationId, int createdBy) 
	{
		CartProxy client = new CartProxy();
		return client.saveOrder(productIdList, orderName, budget, locationId, createdBy);
	}
	public static List<OrderBean> getOrderByOrderName(String orderName, int locationId)
	{
		CartProxy client = new CartProxy();
		return client.getOrderByOrderName(orderName, locationId);
	}
	public static List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType)
	{
		CartProxy client = new CartProxy();
		return client.getAllProductsInCartByProductType(locationId, productType);
	}
	public static int getProductQuantityInCartByProductId(int locationId, int productId) 
	{
		CartProxy client = new CartProxy();
		return client.getProductQuantityInCartByProductId(locationId, productId);
	}
	
	public static List<OrderBean> getAllSavedOrders(int locationId){
		CartProxy client = new CartProxy();
		return client.getAllSavedOrders(locationId);
	}
	
	public static BudgetBean getMostRecentBudgetByLocation(int locationId)
	{
		CartProxy client = new CartProxy();
		return client.getMostRecentBudgetByLocation(locationId);
	}
}
