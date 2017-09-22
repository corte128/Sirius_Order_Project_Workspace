package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductProxy;
import com.sirius.wishlistws.wishlist.wsdl.WishlistDAO;

/**
 * Servlet implementation class WishlistServlet
 */
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("addToWishlist")){
			int id = Integer.parseInt(request.getParameter("id"));
			int userId = (Integer) request.getSession().getAttribute("activeUserID");
			WishlistDAO.addToLikeTable(userId, id);
		}
		else{
			//TODO: Fix it so both the product and wishlist are using the same bean
	        List<com.sirius.wishlistws.wishlist.wsdl.ProductBean> wsProducts = new ArrayList<com.sirius.wishlistws.wishlist.wsdl.ProductBean>();
	        List<ProductBean> products = new ArrayList<ProductBean>();
	        ProductProxy ppObj = new ProductProxy();
	        int employeeId = 0;
	        wsProducts = WishlistDAO.getAllProductsEmployeeLiked(employeeId);
	        
	        for(com.sirius.wishlistws.wishlist.wsdl.ProductBean wlObj : wsProducts){
	        	products.add(ppObj.getProductByID(wlObj.getId()));
	        }
	        
			request.setAttribute("Products", products);
			
			JsonArrayBuilder builder = Json.createArrayBuilder();
			for (ProductBean product : products) {
				builder.add(Json.createObjectBuilder()
						.add("Image", product.getImage())
						.add("Name", product.getName())
						.add("ID", product.getId())
						.add("Price", product.getPrice()));
			}
			JsonArray output = builder.build();

			PrintWriter out = response.getWriter();
			JsonWriter writer = Json.createWriter(out);
			writer.writeArray(output);
			writer.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
