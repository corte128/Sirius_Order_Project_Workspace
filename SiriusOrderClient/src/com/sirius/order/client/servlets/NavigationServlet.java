package com.sirius.order.client.servlets;

import java.io.IOException;
import java.util.List;

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
import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.service.cart.cart.wsdl.CartServiceDAO;
import com.sirius.service.cart.cart.wsdl.OrderBean;



   
/**
 * Servlet implementation class NavigationServlet
 */
public class NavigationServlet extends HttpServlet {
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



		if(action.equalsIgnoreCase("attendance")){

			forwardToAttendance(request, response);
		}
		else if(action.equals("budget"))
		{
			forwardToBudget(request, response);
		}
		else if(action.equals("activateUsers"))
		{
			forwardToActivateUsers(request, response);
		}
		else if(action.equals("registration"))
		{
			forwardToRegistration(request, response);
		}
		else if(action.equals("superAdmin")){
			forwardToSuperAdmin(request,response);
		}
		else if(action.equals("cart"))
		{
			forwardToCart(request, response);
		}else if(action.equals("productDetails")){
			forwardToProductDetails(request, response);
		}else if(action.equalsIgnoreCase("generatePDF")){
			generatePDF(request, response);
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
//		int locationId = (Integer)session.getAttribute("activeUserLocation");
		int locationId = 1;
		List<OrderBean> breakroomProducts = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Breakroom");
		List<OrderBean> officeSuppliesProducts = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Office Supplies");
		List<OrderBean> inkAndTonerProducts = CartServiceDAO.getAllProductsInCartByProductType(locationId, "Ink & Toner");
		request.setAttribute("breakroomProducts", breakroomProducts);
		request.setAttribute("officeSuppliesProducts", officeSuppliesProducts);
		request.setAttribute("inkAndTonerProducts", inkAndTonerProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/authRequired/reviewCart.jsp");
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
