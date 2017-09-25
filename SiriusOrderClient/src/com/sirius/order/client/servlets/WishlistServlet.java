package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.wishlistws.wishlist.wsdl.EmployeeBean;
import com.sirius.wishlistws.wishlist.wsdl.WishlistDAO;

/**
 * Servlet implementation class WishlistServlet
 */
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle sessionVariables = ResourceBundle
			.getBundle("com.sirius.order.client.properties.sessionVariables");
       
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
		//String action = request.getParameter("action");

		//TODO: Fix it so both the product and wishlist are using the same bean
	    List<com.sirius.wishlistws.wishlist.wsdl.ProductBean> wsProducts = new ArrayList<com.sirius.wishlistws.wishlist.wsdl.ProductBean>();
	    List<ProductBean> products = new ArrayList<ProductBean>();
	    List<EmployeeBean> emps = new ArrayList<EmployeeBean>();
	    int employeeId = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
	   
	    wsProducts = WishlistDAO.getAllProductsEmployeeLiked(employeeId);
	    
	    for(com.sirius.wishlistws.wishlist.wsdl.ProductBean wlObj : wsProducts){
	    	products.add(ProductSearchDAO.getProductByID(wlObj.getId()));
	    	emps = WishlistDAO.getAllEmployeesWhoLikedProduct(wlObj.getId());
	    }
	    
		request.setAttribute("Products", products);
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (ProductBean record : products)
		{
			JsonArrayBuilder likesBuilder = Json.createArrayBuilder();
			
			for(EmployeeBean emp : emps){
				likesBuilder.add(emp.getName());
			}
			builder.add(Json.createObjectBuilder()
					.add("Image", record.getImage())
					.add("Name", record.getName())
					.add("ID", record.getId())
					.add("Price", record.getPrice())
					.add("Likers", likesBuilder));
		}
		JsonArray output = builder.build();

		PrintWriter out = response.getWriter();
		JsonWriter writer = Json.createWriter(out);
		writer.writeArray(output);
		writer.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
