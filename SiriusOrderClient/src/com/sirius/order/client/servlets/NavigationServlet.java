package com.sirius.order.client.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
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
import com.sirius.product.service.main.product.wsdl.ProductProxy;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.service.cart.cart.wsdl.CartServiceDAO;
import com.sirius.service.cart.cart.wsdl.OrderBean;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		OfficeAdminClientDAO oficeAdminClient = new OfficeAdminClientDAO();
		List<EmployeeBean> employees = oficeAdminClient.getUnapprovedEmployees((Integer) session.getAttribute("activeUserLocation"));
		int numUnapprovedEmployees = employees.size();
		session.setAttribute("numAlerts", numUnapprovedEmployees);

		if(action.equalsIgnoreCase("attendance"))
		{
			forwardToAttendance(request, response);
		}
		else if(action.equals("budget"))
		{
			forwardToBudget(request, response);
		}
		else if(action.equals("visitors"))
		{
			if(session.getAttribute("activeUserType") == null || (Integer) session.getAttribute("activeUserType") == 1){
				response.sendRedirect("jsps/welcome.jsp");
			}
			else{
				response.sendRedirect("jsps/visitors.jsp");
			}
		}
		else if(action.equals("activateUsers"))
		{
			if(session.getAttribute("activeUserID") == null || (Integer) session.getAttribute("activeUserType") == 1){
				response.sendRedirect("jsps/welcome.jsp");
			}
			else{
				forwardToActivateUsers(request, response);
			}
		}
		else if(action.equals("holidays"))
		{
			if(session.getAttribute("activeUserID") == null || (Integer) session.getAttribute("activeUserType") == 1){
				response.sendRedirect("jsps/welcome.jsp");
			}
			else{
				response.sendRedirect("jsps/holidays.jsp");
			}
		}
		else if(action.equals("registration"))
		{
			forwardToRegistration(request, response);
		}
		else if(action.equals("superAdmin"))
		{
			forwardToSuperAdmin(request,response);
		}
		else if(action.equals("cart"))
		{
			forwardToCart(request, response);
		}
		else if(action.equals("productDetails"))
		{
			forwardToProductDetails(request, response);
		}
		else if(action.equalsIgnoreCase("generatePDF"))
		{
			generatePDF(request, response);
		}
		else if(action.equals("wishlist"))
		{
			forwardToWishlist(request,response);
		}
	}
	
	private void forwardToProductDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ProductBean product = new ProductBean();
		String id = request.getParameter("id");
		product = ProductSearchDAO.getProductByID(Integer.parseInt(id));
		request.setAttribute("productDetails", product.getDetails());
		request.setAttribute("productId", product.getId());
		request.setAttribute("productImage", product.getImage());
		request.setAttribute("productName", product.getName());
		request.setAttribute("productPrice", product.getPrice());
		request.setAttribute("productType", product.getType());
		if(product.getType().equals("Breakroom"))
		{
			request.setAttribute("productTypeId", 1);
		}
		else if(product.getType().equals("Office Supplies"))
		{
			request.setAttribute("productTypeId", 2);
		}
		else
		{
			request.setAttribute("productTypeId", 3);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/productDetails.jsp?id="+ product.getId());
		 dispatcher.forward(request, response);
	}
	
	private void forwardToAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		HttpSession session =request.getSession();
		 session.setAttribute("locations", locationBeanList);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/authRequired/attendance.jsp");
		 dispatcher.forward(request, response);
	}
	
	private void forwardToBudget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		request.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/authRequired/budgetReport.jsp");
		dispatcher.forward(request, response);

	}
	private void forwardToActivateUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		OfficeAdminClientDAO client = new OfficeAdminClientDAO();
		List<EmployeeBean> employees = client.getUnapprovedEmployees((Integer) session.getAttribute("activeUserLocation"));
		request.setAttribute("employees", employees);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/activateUsers.jsp");
		dispatcher.forward(request, response);
	}
	
	private void forwardToRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		LocationClientDAO dao = new LocationClientDAO();
		List<LocationBean> locations = dao.getLocations();
		session.setAttribute("locations", locations);
		response.sendRedirect("jsps/registration.jsp");
	}
	
	private void forwardToSuperAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LocationClientDAO locationClient = new LocationClientDAO();
		List<LocationBean> locationBeanList = locationClient.getLocations();

		request.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/superAdmin.jsp");
		dispatcher.forward(request, response);
	}

	private void forwardToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		int locationId = (Integer)session.getAttribute("activeUserLocation");
		
		List<OrderBean> breakroomOrders = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Breakroom");
		List<OrderBean> officeSuppliesOrders = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Office Supplies");
		List<OrderBean> inkAndTonerOrders = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Ink & Toner");
		
		List<ProductBean>  breakroomProducts = new ArrayList<ProductBean>();
		List<ProductBean>  officeSuppliesProducts = new ArrayList<ProductBean>();
		List<ProductBean>  inkAndTonerProducts = new ArrayList<ProductBean>();
		
		for(OrderBean order : breakroomOrders)
		{
			breakroomProducts.add(ProductSearchDAO.getProductByID(order.getProductId()));
		}
		for(OrderBean order : officeSuppliesOrders)
		{
			officeSuppliesProducts.add(ProductSearchDAO.getProductByID(order.getProductId()));
		}
		for(OrderBean order : inkAndTonerOrders)
		{
			inkAndTonerProducts.add(ProductSearchDAO.getProductByID(order.getProductId()));
		}
		
		request.setAttribute("breakroomProducts", breakroomProducts);
		request.setAttribute("breakroomOrders", breakroomOrders);
		request.setAttribute("officeSuppliesProducts", officeSuppliesProducts);
		request.setAttribute("officeSuppliesOrders", officeSuppliesOrders);
		request.setAttribute("inkAndTonerProducts", inkAndTonerProducts);
		request.setAttribute("inkAndTonerOrders", inkAndTonerOrders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/authRequired/reviewCart.jsp");
		dispatcher.forward(request, response);
	}
	
	private void forwardToWishlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LocationProxy lpObj = new LocationProxy();
		int locationId = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_LOCATION"));
		String location = lpObj.getLocationStringByLocationId(locationId);
		request.setAttribute("location", location);
		
		//TODO: Fix it so both the product and wishlist are using the same bean
        List<com.sirius.wishlistws.wishlist.wsdl.ProductBean> wsProducts = new ArrayList<com.sirius.wishlistws.wishlist.wsdl.ProductBean>();
        List<ProductBean> products = new ArrayList<ProductBean>();
        ProductProxy ppObj = new ProductProxy();
        int employeeId = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
        wsProducts = WishlistDAO.getAllProductsEmployeeLiked(employeeId);
        
        for(com.sirius.wishlistws.wishlist.wsdl.ProductBean wlObj : wsProducts){
        	products.add(ppObj.getProductByID(wlObj.getId()));
        }
        
        request.setAttribute("ProductAmount", products.size());
		request.setAttribute("Products", products);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/wishlist.jsp");
		dispatcher.forward(request, response);
	}
	
	private void generatePDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
