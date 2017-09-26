package com.sirius.service.cart;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.sirius.service.cart.bean.BudgetBean;
import com.sirius.service.cart.bean.OrderBean;
import com.sirius.service.cart.database.CartDAO;

@WebService(endpointInterface="com.sirius.service.cart.CartInterface",
portName="cart", targetNamespace ="http://generate.service.sirius.com/cart/wsdl",
serviceName="CartService")
public class CartImplementation implements CartInterface {
	
	@Override
	public boolean addProductToCart(OrderBean order, BudgetBean budget, int createdBy) {
		return CartDAO.addProductToCart(order, budget, createdBy);
	}

	@Override
	public List<OrderBean> getAllProductsInCart(int locationId) {
		return CartDAO.getAllProductsInCart(locationId);
	}

	@Override
	public boolean updateProductQuantityInCart(int locationId, int quantity,
			int productId, int updatedBy) {
		return CartDAO.updateProductQuantity(locationId, productId, quantity, updatedBy);
	}

	@Override
	public boolean removeProductFromCart(int orderId, int updatedBy) {
		return CartDAO.removeProductFromCart(orderId, updatedBy);
	}

	@Override
	public boolean saveOrder(String orderName, BudgetBean budget, int locationId, int createdBy) {
		return CartDAO.saveOrder(orderName, budget, locationId, createdBy);
	}

	@Override
	public List<OrderBean> getOrderByOrderName(String orderName, int locationId) {
		return CartDAO.getOrderByOrderName(orderName, locationId);
	}
	
	@Override
	public List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType)
	{
		return CartDAO.getAllProductsInCartByProductType(locationId, productType);
	}
	
	@Override
	public int getProductQuantityInCartByProductId(int locationId, int productId)
	{
		return CartDAO.getProductQuantityInCartByProductId(locationId, productId);
	}

	@Override
	public List<OrderBean> getAllSavedOrders(int locationId) {
		return CartDAO.getAllSavedOrders(locationId);
	}
}
