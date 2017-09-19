package com.sirius.order.client.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.disthub2.impl.formats.Envelop.Constants.querySubscriptionsReply_type;

/**
 * Servlet implementation class AttendanceServlet
 */
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action==null){
			
		}
		
		
		
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String view = request.getParameter("view");
		String startDate = request.getParameter("endDate");
		String endDate = request.getParameter("endDate");
		String range = request.getParameter("range");
		
		String[] locationArray = location.split(", ");
		String state = locationArray[0];
		String city = locationArray[1];
		
		name = '%'+name+'%';
		email = '%'+email+'%';
		state = '%'+state+'%';
		city = '%'+city+'%';
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
