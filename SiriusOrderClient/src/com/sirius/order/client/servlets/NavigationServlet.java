package com.sirius.order.client.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sirius.loctionws.location.wsdl.LocationBean;
import com.sirius.loctionws.location.wsdl.LocationClientDao;

/**
 * Servlet implementation class NavigationServlet
 */
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String contextPath = null;
	
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
		contextPath = request.getContextPath();
		String action = request.getParameter("action");
		LocationClientDao locationClient = new LocationClientDao();

		 if(action.equalsIgnoreCase("attendance")){
			 List<LocationBean> locationBeanList = locationClient.getLocations();
			 
			 request.setAttribute("locationList", locationBeanList);
			 HttpSession session =request.getSession();
			 session.setAttribute("locations", locationBeanList);
			 forwardToAttendance(request, response);
			 
		 }

		
		else if(action.equals("budget"))
		{
			forwardToBudget(request, response);
		}
	}
	private void forwardToAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LocationClientDao locationClient = new LocationClientDao();
		List<LocationBean> locationBeanList = locationClient.getLocations();
		ArrayList<String> parsedLocationList = new ArrayList<String>();
		 
		for (int i=0; i<locationBeanList.size(); i++)
		{
			LocationBean tempBean = locationBeanList.get(i);
			String parsedLocation = tempBean.getCity() + ", " + tempBean.getState();
			parsedLocationList.add(parsedLocation);
		}
		request.setAttribute("locationList", parsedLocationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(contextPath +  "/jsps/authRequired/attendance.jsp");
		dispatcher.forward(request, response);
	}
	private void forwardToBudget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LocationClientDao locationClient = new LocationClientDao();
		List<LocationBean> locationBeanList = locationClient.getLocations();
		 
		request.setAttribute("locations", locationBeanList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/budgetReport.jsp");
		dispatcher.forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
