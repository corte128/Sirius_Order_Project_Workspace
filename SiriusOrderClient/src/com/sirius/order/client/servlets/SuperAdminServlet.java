package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
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
	private static final Logger logger = Logger
			.getLogger(SuperAdminServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		JsonArrayBuilder jab = Json.createArrayBuilder();
		SuperadminProxy superAdminProxyObj = new SuperadminProxy();
		
		
		if(action != null && action.equals("officeAdmin")){
			// adding offices to Json Object
			List<String> admins = superAdminProxyObj.getOfficeAdminNames();
			for (String admin : admins) {
				jab.add(admin);
			}
			// writing to the response
			JsonArray array = jab.build();
			PrintWriter out = response.getWriter();
			JsonWriter writer = Json.createWriter(out);
			writer.writeArray(array);
			writer.close();
		}
		else{
			// gets office list
			List<OfficeBean> offices = superAdminProxyObj.getOffices();
			// adding offices to Json Object
			for (OfficeBean office : offices) {
				jab.add(Json
						.createObjectBuilder()
						.add("Location Id", office.getLocationId())
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
		SuperadminProxy superAdminProxyObj = new SuperadminProxy();
		
		if(action!=null){
			if(action.equals("addLocation")){
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				Integer id = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
				if(city != null && state != null)
					superAdminProxyObj.addLocation(city, state, id);
			}
			else if(action.equals("setBudget")){
				Map<String, String[]> parameters = request.getParameterMap();

				List<Integer> locationIds = new ArrayList<Integer>();
				List<BigDecimal> budgets = new ArrayList<BigDecimal>();
				
				for(String parameter : parameters.keySet()) {
					//locations
					 if(parameter.toLowerCase().startsWith("loc")) {
					        String[] values = parameters.get(parameter);
					        for(String strVal : values){
					        	int id = Integer.parseInt(strVal);
					        	locationIds.add(id);
					        }
					    }
					//budgets
				    if(parameter.toLowerCase().startsWith("budget")) {
				        String[] values = parameters.get(parameter);
				        for(String strVal : values){
				        	DecimalFormat dfObj = new DecimalFormat();
				        	dfObj.setParseBigDecimal(true);
				        	try {
								budgets.add((BigDecimal) dfObj.parse(strVal));
							} catch (ParseException e) {
								logger.log(Level.SEVERE,"Parse exception for budget",e);
							}
				        }
				    }
				}
				
				for(int index = 0; index < locationIds.size(); index++)
					superAdminProxyObj.setBudgetByLocation(budgets.get(index), locationIds.get(index));
			}
			else if(action.equals("assignAdmin")){
				int locationId = Integer.parseInt(request.getParameter("locations"));
				String admin = request.getParameter("admin");
				int adminId = 0;
				Integer id = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
				
				if(admin != null)
					adminId = superAdminProxyObj.getEmployeeIdByName(admin);
				
				if(locationId != 0 && admin != null){}
					superAdminProxyObj.assignAdmin(locationId, adminId, id);
			}
		}
			
		response.sendRedirect("/SiriusOrderClient/NavigationServlet?action=superAdmin");
	}
}
