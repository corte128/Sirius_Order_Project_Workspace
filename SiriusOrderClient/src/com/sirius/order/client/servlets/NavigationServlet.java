package com.sirius.order.client.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sirius.loctionws.location.wsdl.LocationBean;
import com.sirius.loctionws.location.wsdl.LocationClientDao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		LocationClientDao locationClient = new LocationClientDao();
		String action = request.getParameter("action");
		 if(action.equalsIgnoreCase("attendance")){
			 List<LocationBean> locationBeanList = locationClient.getLocations();
			 ArrayList<String> parsedLocationList = new ArrayList<String>();
			 
			 for (int i=0; i<locationBeanList.size(); i++){
				 LocationBean tempBean = new LocationBean();
				 tempBean = locationBeanList.get(i);
				 String parsedLocation = tempBean.getCity() + ", " + tempBean.getState();
				 parsedLocationList.add(parsedLocation);
			 }
			 request.setAttribute("locationList", parsedLocationList);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("jsps/authRequired/attendance.jsp");
			 dispatcher.forward(request, response);
			 
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
