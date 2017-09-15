package com.sirius.order.client.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    
    public void init(ServletConfig config){
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	DBQuerying db = new DBQuerying();
    	String action = request.getParameter("button");
//        if(action.equals("Search")){
//            HttpSession session = request.getSession();
//            //Object[] objects = db.searchEmployees(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("roles"));
//            List<Employee> objects = EmployeePortalClientDAO.searchEmployees(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("roles"));
//            
//    		session.setAttribute("Employees", objects);
//    		RequestDispatcher dispatcher = request.getRequestDispatcher("jsps/productSearch.jsp");
//    		dispatcher.forward(request, response);
//        }
	}
	
}
