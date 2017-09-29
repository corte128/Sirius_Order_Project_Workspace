package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sirius.adminws.officeadmin.wsdl.EmployeeBean;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminClientDAO;
import com.sirius.locationws.location.wsdl.LocationBean;
import com.sirius.locationws.location.wsdl.LocationClientDAO;
import com.sirius.locationws.location.wsdl.LocationProxy;
import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.service.cart.cart.wsdl.CartServiceDAO;
import com.sirius.service.cart.cart.wsdl.OrderBean;
import com.sirius.service.generate.cart.wsdl.GenerateCartClientDAO;
import com.sirius.wishlistws.wishlist.wsdl.WishlistDAO;

/**
 * Servlet implementation class NavigationServlet
 */
public class NavigationServlet extends HttpServlet {
	private static final ResourceBundle sessionVariables = ResourceBundle
			.getBundle("com.sirius.order.client.properties.sessionVariables");
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
//		GenerateCartClientDAO generate = new GenerateCartClientDAO();
//		generate.GenerateCart();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		OfficeAdminClientDAO oficeAdminClient = new OfficeAdminClientDAO();
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		Object userId = (Integer) request.getSession().getAttribute(
				sessionVariables.getString("ACTIVE_USER_ID"));
		if (session.getAttribute("activeUserLocation") != null)
			employees = oficeAdminClient
					.getUnapprovedEmployees((Integer) session
							.getAttribute("activeUserLocation"));
		int numUnapprovedEmployees = employees.size();
		session.setAttribute("numAlerts", numUnapprovedEmployees);

		if (userId == null && !action.equalsIgnoreCase("registration"))
		{
			response.sendRedirect("jsps/login.jsp");
		} 
		else if (action.equalsIgnoreCase("attendance"))
		{
			forwardToAttendance(request, response);
		}
		else if (action.equals("budget"))
		{
			forwardToBudget(request, response);
		}
		else if (action.equals("visitors"))
		{
			if (session.getAttribute("activeUserType") == null
					|| (Integer) session.getAttribute("activeUserType") == 1)
			{
				response.sendRedirect("jsps/welcome.jsp");
			}
			else
			{
				response.sendRedirect("jsps/visitors.jsp");
			}
		}
		else if (action.equals("activateUsers"))
		{
			if (session.getAttribute("activeUserID") == null
					|| (Integer) session.getAttribute("activeUserType") == 1)
			{
				response.sendRedirect("jsps/welcome.jsp");
			}
			else 
			{
				forwardToActivateUsers(request, response);
			}
		} else if (action.equals("holidays")) {
			if (session.getAttribute("activeUserID") == null
					|| (Integer) session.getAttribute("activeUserType") == 1) {
				response.sendRedirect("jsps/welcome.jsp");
			} else {
				response.sendRedirect("jsps/holidays.jsp");
			}
		}
		else if (action.equals("registration"))
		{
			forwardToRegistration(request, response);
		}
		else if (action.equals("superAdmin")) 
		{
			forwardToSuperAdmin(request, response);
		}
		else if (action.equals("cart"))
		{
			forwardToCart(request, response);
		}
		else if (action.equals("productDetails"))
		{
			forwardToProductDetails(request, response);
		} 
		else if (action.equalsIgnoreCase("getLikers")) 
		{
			getLikers(request, response);
		} 
		else if (action.equals("wishlist")) 
		{
			forwardToWishlist(request, response);
		}
	}

	private void forwardToProductDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductBean product = new ProductBean();
		String id = request.getParameter("id");
		Integer locationId = (Integer) session
				.getAttribute("activeUserLocation");
		// int locationId = Integer.parseInt(locationIdString);
		product = ProductSearchDAO.getProductByID(Integer.parseInt(id));
		request.setAttribute("productDetails", product.getDetails());
		request.setAttribute("productId", product.getId());
		request.setAttribute("productImage", product.getImage());
		request.setAttribute("productName", product.getName());
		request.setAttribute("productPrice", product.getPrice());
		request.setAttribute("productType", product.getType());

		List<com.sirius.wishlistws.wishlist.wsdl.EmployeeBean> emps = WishlistDAO
				.getAllEmployeesWhoLikedProduct(Integer.parseInt(id),
						locationId);
		List<String> empNames = new ArrayList<String>();
		for (com.sirius.wishlistws.wishlist.wsdl.EmployeeBean emp : emps) {
			empNames.add(emp.getName());
		}

		request.setAttribute("LikesForProduct", empNames);
		request.setAttribute("numLikes", empNames.size());

		if (product.getType().equals("Breakroom")) {
			request.setAttribute("productTypeId", 1);
		} else if (product.getType().equals("Office Supplies")) {
			request.setAttribute("productTypeId", 2);
		} else {
			request.setAttribute("productTypeId", 3);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/productDetails.jsp?id="
						+ product.getId());
		dispatcher.forward(request, response);
	}

	private void forwardToAttendance(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		HttpSession session = request.getSession();
		session.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/authRequired/attendance.jsp");
		dispatcher.forward(request, response);
	}

	private void forwardToBudget(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		request.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/authRequired/budgetReport.jsp");
		dispatcher.forward(request, response);

	}

	private void forwardToActivateUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OfficeAdminClientDAO client = new OfficeAdminClientDAO();
		List<EmployeeBean> employees = client
				.getUnapprovedEmployees((Integer) session
						.getAttribute("activeUserLocation"));
		request.setAttribute("employees", employees);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/activateUsers.jsp");
		dispatcher.forward(request, response);
	}

	private void forwardToRegistration(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LocationClientDAO dao = new LocationClientDAO();
		List<LocationBean> locations = dao.getLocations();
		session.setAttribute("locations", locations);
		response.sendRedirect("jsps/registration.jsp");
	}

	private void forwardToSuperAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		request.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/superAdmin.jsp");
		dispatcher.forward(request, response);
	}

	private void forwardToCart(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int locationId = (Integer) session.getAttribute("activeUserLocation");

		List<OrderBean> breakroomOrders = CartServiceDAO
				.getAllProductsInCartByProductType(locationId, "Breakroom");
		List<OrderBean> officeSuppliesOrders = CartServiceDAO
				.getAllProductsInCartByProductType(locationId, "Office Supplies");
		List<OrderBean> inkAndTonerOrders = CartServiceDAO
				.getAllProductsInCartByProductType(locationId, "Ink & Toner");

		List<ProductBean> breakroomProducts = new ArrayList<ProductBean>();
		List<ProductBean> officeSuppliesProducts = new ArrayList<ProductBean>();
		List<ProductBean> inkAndTonerProducts = new ArrayList<ProductBean>();

		for (OrderBean order : breakroomOrders) {
			breakroomProducts.add(ProductSearchDAO.getProductByID(order
					.getProductId()));
		}
		for (OrderBean order : officeSuppliesOrders) {
			officeSuppliesProducts.add(ProductSearchDAO.getProductByID(order
					.getProductId()));
		}
		for (OrderBean order : inkAndTonerOrders) {
			inkAndTonerProducts.add(ProductSearchDAO.getProductByID(order
					.getProductId()));
		}

//		List<OrderBean> savedOrders = CartServiceDAO.getAllSavedOrders(locationId);
//		Map<String, List<OrderBean>> mapOfList = new HashMap<String, List<OrderBean>>();
//		for (OrderBean order : savedOrders)
//		{
//			String orderName = order.getOrderName();
//			if(!mapOfList.containsKey(orderName)) 
//			{
//				mapOfList.put(orderName, new ArrayList<OrderBean>());
//			}
//			mapOfList.get(orderName).add(order);
//		}
//		
//		int iterator = 1;
//		for(Map.Entry<String, List<OrderBean>> str : mapOfList.entrySet())
//		{
//			System.out.println(iterator + ") " + str);
//			iterator++;
//		}
		 
		request.setAttribute("breakroomProducts", breakroomProducts);
		request.setAttribute("breakroomOrders", breakroomOrders);
		request.setAttribute("officeSuppliesProducts", officeSuppliesProducts);
		request.setAttribute("officeSuppliesOrders", officeSuppliesOrders);
		request.setAttribute("inkAndTonerProducts", inkAndTonerProducts);
		request.setAttribute("inkAndTonerOrders", inkAndTonerOrders);


		List<OrderBean> savedOrders = CartServiceDAO.getAllSavedOrders(locationId);
 		Map<String, List<OrderBean>> mapOfOrders = new HashMap<String, List<OrderBean>>();
 		Map<String, List<ProductBean>> mapOfProducts = new HashMap<String, List<ProductBean>>();
 		for(OrderBean order: savedOrders){
 			String orderName = order.getOrderName();
 			if(!orderName.equals("cart"))
 			{
				if(!mapOfOrders.containsKey(orderName))
 				{
 					mapOfOrders.put(orderName, new ArrayList<OrderBean>());
 				}
 				mapOfOrders.get(orderName).add(order);
 				
 				if(!mapOfProducts.containsKey(orderName))
 				{
 					mapOfProducts.put(orderName, new ArrayList<ProductBean>());
 				}
 				mapOfProducts.get(orderName).add(ProductSearchDAO.getProductByID(order.getProductId()));
 			}
 		}
		request.setAttribute("savedOrders", mapOfOrders);
 		request.setAttribute("savedProducts", mapOfProducts);
 		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/authRequired/reviewCart.jsp");
		dispatcher.forward(request, response);
	}

	private void forwardToWishlist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(
				sessionVariables.getString("ACTIVE_USER_LOCATION")) != null) {
			LocationProxy lpObj = new LocationProxy();
			int locationId = (Integer) request.getSession().getAttribute(
					sessionVariables.getString("ACTIVE_USER_LOCATION"));
			String location = lpObj.getLocationStringByLocationId(locationId);
			request.setAttribute("location", location);

			// TODO: Fix it so both the product and wishlist are using the same
			// bean
			// List<com.sirius.wishlistws.wishlist.wsdl.ProductBean> wsProducts
			// = new
			// ArrayList<com.sirius.wishlistws.wishlist.wsdl.ProductBean>();
			// List<ProductBean> products = new ArrayList<ProductBean>();
			// List<com.sirius.wishlistws.wishlist.wsdl.EmployeeBean> emps = new
			// ArrayList<com.sirius.wishlistws.wishlist.wsdl.EmployeeBean>();
			// int employeeId = (Integer)
			// request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
			//
			// wsProducts = WishlistDAO.getAllProductsEmployeeLiked(employeeId);
			//
			// for(com.sirius.wishlistws.wishlist.wsdl.ProductBean wlObj :
			// wsProducts){
			// products.add(ProductSearchDAO.getProductByID(wlObj.getId()));
			// emps = WishlistDAO.getAllEmployeesWhoLikedProduct(wlObj.getId());
			// }
			//
			//
			// request.setAttribute("ProductAmount", products.size());
			// request.setAttribute("Products", products);
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsps/wishlist.jsp");
		dispatcher.forward(request, response);
	}

	private void getLikers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		Integer locationId = (Integer) session
				.getAttribute("activeUserLocation");

		JsonArrayBuilder builder = Json.createArrayBuilder();
		List<com.sirius.wishlistws.wishlist.wsdl.EmployeeBean> emps = WishlistDAO
				.getAllEmployeesWhoLikedProduct(Integer.parseInt(id),
						locationId);

		for (com.sirius.wishlistws.wishlist.wsdl.EmployeeBean emp : emps) {

			builder.add(Json.createObjectBuilder().add("ID", emp.getId())
					.add("Name", emp.getName()));
		}
		JsonArray output = builder.build();

		PrintWriter out = response.getWriter();
		JsonWriter writer = Json.createWriter(out);
		writer.writeArray(output);
		writer.close();
	}

	private Map<String, List<OrderBean>> convertSavedOrdersListToMap(
			List<OrderBean> orders) {

		Map<String, List<OrderBean>> mapOfOrders = new HashMap<String, List<OrderBean>>();
		for (OrderBean order : orders)
		{
			String orderName = order.getOrderName();
			if(!mapOfOrders.containsKey(orderName)) 
			{
				mapOfOrders.put(orderName, new ArrayList<OrderBean>());
			}
			mapOfOrders.get(orderName).add(order);
		}
		return mapOfOrders;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}