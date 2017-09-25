package com.sirius.wishlistws.wishlist.wsdl;

import java.util.List;

public class WishlistDAO {

    public static void addToLikeTable(int employeeId, int productId) {
    	WishlistProxy proxy = new WishlistProxy();
        proxy.addToLikeTable(employeeId,productId);
    }

    public static List<EmployeeBean> getAllEmployeesWhoLikedProduct(int productId, int location_id) {
    	WishlistProxy proxy = new WishlistProxy();
        return proxy.getAllEmployeesWhoLikedProduct(productId, location_id);
    }

    public static List<ProductBean> getAllProductsEmployeeLiked(int employeeId) {
    	WishlistProxy proxy = new WishlistProxy();
    	return proxy.getAllProductsEmployeeLiked(employeeId);
    }

    public static void removeFromEmployeeWishlist(int employeeId, int productId) {
    	WishlistProxy proxy = new WishlistProxy();
    	proxy.removeFromEmployeeWishlist(employeeId,productId);
    }
}
