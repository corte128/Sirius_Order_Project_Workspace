package com.sirius.wishlistws.jaxws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.sirius.wishlistws.beans.EmployeeBean;
import com.sirius.wishlistws.beans.EmployeeLikeBean;
import com.sirius.wishlistws.beans.ProductBean;
import com.sirius.wishlistws.db.WishlistServiceDAO;

@WebService(endpointInterface = "com.sirius.wishlistws.jaxws.Wishlist", portName = "wishlist", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", serviceName = "JaxwsWishlist")
public class JaxwsWishlistImpl implements Wishlist{

	@Override
	public void addToLikeTable(int employee_id, int product_id) {
		WishlistServiceDAO dao = new WishlistServiceDAO();
		dao.addToLikeTable(employee_id, product_id);
	}

	@Override
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(int product_id, int location_id) {
		WishlistServiceDAO dao = new WishlistServiceDAO();
		return dao.getAllEmployeesWhoLikedProduct(product_id, location_id);
	}

	@Override
	public List<ProductBean> getAllProductsEmployeeLiked(int employee_id) {
		WishlistServiceDAO dao = new WishlistServiceDAO();
		return dao.getAllProductsEmployeeLiked(employee_id);
	}

	@Override
	public void removeFromEmployeeWishlist(int employee_id, int product_id) {
		WishlistServiceDAO dao = new WishlistServiceDAO();
		dao.removeFromEmployeeWishlist(employee_id, product_id);
	}
	
	@Override
	public List<EmployeeLikeBean> getAllEmployeesWhoLikedProducts(List<ProductBean> productList, int location_id)
	{
		List<EmployeeLikeBean> output = new ArrayList<EmployeeLikeBean>();
		for(ProductBean product : productList)
		{
			int productId = product.getId();
			for(EmployeeBean emp : getAllEmployeesWhoLikedProduct(productId, location_id))
			{
				EmployeeLikeBean liker = new EmployeeLikeBean();
				liker.setProductId(productId);
				liker.setId(emp.getId());
				liker.setName(emp.getName());
				liker.setEmail(emp.getEmail());
				output.add(liker);
			}
		}
		return output;
	}
}
