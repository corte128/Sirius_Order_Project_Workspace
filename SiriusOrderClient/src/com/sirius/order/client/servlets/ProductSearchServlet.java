package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sirius.attendancews.attendance.wsdl.AttendanceRecordBean;
import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.wishlistws.wishlist.wsdl.WishlistDAO;

/**
 * Servlet implementation class ProductSearchServlet
 */
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("addToWishlist")){
			int id = Integer.parseInt(request.getParameter("id"));
			int userId = (Integer) request.getSession().getAttribute("activeUserID");
			WishlistDAO.addToLikeTable(userId, id);
		}
		else{
	        String name = request.getParameter("search");
	        int category = Integer.parseInt(request.getParameter("category"));
	        List<ProductBean> objects = null;
	        if(category == 0){
	        	objects = ProductSearchDAO.getAllProductsByName(name);
	        }
	        else{
	            objects = ProductSearchDAO.getAllProductsByNameAndType(name, category);
	        }
	        List<List<ProductBean>> lists = separateList(objects, 20);
	        //break objects down for lazy loading into separate lists
	        		
			request.setAttribute("Products", objects);
			
			
			JsonArrayBuilder builder = Json.createArrayBuilder();
			for (ProductBean record : objects) {
				builder.add(Json.createObjectBuilder()
						.add("Image", record.getImage())
						.add("Name", record.getName())
						.add("ID", record.getId())
						.add("Price", record.getPrice()));
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
        HttpSession session = request.getSession();
        String name = request.getParameter("search");
        int category = Integer.parseInt(request.getParameter("category"));
        List<ProductBean> objects = null;
        if(category == 0){
        	objects = ProductSearchDAO.getAllProductsByName(name);
        }
        else{
            objects = ProductSearchDAO.getAllProductsByNameAndType(name, category);
        }
        List<List<ProductBean>> lists = separateList(objects, 20);
        //break objects down for lazy loading into separate lists
        		
		request.setAttribute("Products", objects);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsps/productSearch.jsp");
		dispatcher.forward(request, response);
	}
	
	private List<List<ProductBean>> separateList(List<ProductBean> objects, int numOfObjects){
		/**/
		List<List<ProductBean>> lists = new ArrayList<List<ProductBean>>();
		int length = objects.size();
		for(int i = 0; i < length; i+= numOfObjects){
			int endpoint = (i+numOfObjects);
			if(endpoint >= length) {
				endpoint = length;
			}
			lists.add(objects.subList(i, endpoint));
		}
		return lists;
	}

}
