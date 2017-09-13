package com.sirius.service.cart;

import javax.jws.WebService;

import com.sirius.service.cart.bean.OrderBean;
import com.sirius.service.cart.database.CartDAO;

@WebService(endpointInterface="com.sirius.service.cart.CartInterface",
portName="cart", targetNamespace ="http://superadmin.service.sirius.com/superadmin/wsdl",
serviceName="CartService")
public class CartImplementation implements CartInterface {

	@Override
	public boolean addProductToCart(OrderBean order, int createdBy) {
		CartDAO.addProductToCart(order, createdBy);
		return false;
	}

	@Override
	public OrderBean[] getAllProductsInCart(String orderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProductQuantity(String orderName, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProductFromCart(String orderName, int productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveCart() {
		// TODO Auto-generated method stub
		return false;
	}
}
