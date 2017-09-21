package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sirius.service.superadmin.superadmin.wsdl.OfficeBean;
import com.sirius.service.superadmin.superadmin.wsdl.SuperadminProxy;

/**
 * Servlet implementation class SuperAdminServlet
 */
public class SuperAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle sessionVariables = ResourceBundle
			.getBundle("com.sirius.order.client.properties.sessionVariables");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		JsonArrayBuilder jab = Json.createArrayBuilder();
		SuperadminProxy superAdminProxyObj = new SuperadminProxy();
		List<OfficeBean> offices = superAdminProxyObj.getOffices();
		
		if(action != null && action.equals("officeAdmin")){
			// adding offices to Json Object
			for (OfficeBean office : offices) {
				jab.add(office.getAdminName());
			}
			// writing to the response
			JsonArray array = jab.build();
			PrintWriter out = response.getWriter();
			JsonWriter writer = Json.createWriter(out);
			writer.writeArray(array);
			writer.close();
		}
		else{
			// adding offices to Json Object
			for (OfficeBean office : offices) {
				jab.add(Json
						.createObjectBuilder()
						.add("Location", office.getLocation())
						.add("Admin Name", office.getAdminName())
						.add("Admin Email", office.getAdminEmail())
						.add("No of Employees", office.getNumberOfEmployees())
						.add("Recommended Budget",
								office.getRecommendedBudget().doubleValue())
						.add("Allotted Budget",
								office.getAllottedBudget().doubleValue()));
			}
			// writing to the response
			JsonArray array = jab.build();
			PrintWriter out = response.getWriter();
			JsonWriter writer = Json.createWriter(out);
			writer.writeArray(array);
			writer.close();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		boolean flag = false;
		SuperadminProxy superAdminProxyObj = new SuperadminProxy();
		
		if(action!=null){
			if(action.equals("addLocation")){
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				Integer id = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
				if(city != null && state != null)
					flag = superAdminProxyObj.addLocation(city, state, id);
			}
			else if(action.equals("assignAdmin")){
				int locationId = Integer.parseInt(request.getParameter("locations"));
				String admin = request.getParameter("admin");
				Integer id = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
				//TODO: add service to get user by 
				if(locationId != 0 && admin != null){}
					//superAdminProxyObj.assignAdmin(locationId, adminId, id);
			}
		}
			
		response.sendRedirect("/SiriusOrderClient/NavigationServlet?action=superAdmin");
	}
}
