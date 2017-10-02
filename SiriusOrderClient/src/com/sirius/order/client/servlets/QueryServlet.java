package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sirius.adminws.officeadmin.wsdl.Holiday;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminClientDAO;
import com.sirius.employeews.employee.wsdl.EmployeeBean;
import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.locationws.location.wsdl.LocationBean;
import com.sirius.locationws.location.wsdl.LocationClientDAO;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (request.getParameter("query") != null){
			String query = request.getParameter("query");
			if(query.equals("getHolidays")){
				System.out.println("Getting holidays for location: "+session.getAttribute("activeUserLocation"));
				List<Holiday> holidays = new ArrayList<Holiday>();
				OfficeAdminClientDAO dao = new OfficeAdminClientDAO();
				holidays = dao.getAllHolidays((Integer) session.getAttribute("activeUserLocation"));
				JsonArrayBuilder builder = Json.createArrayBuilder();
				for(Holiday day : holidays)
		        {
					int dow = day.getDayOfWeek();
					String dayofweek = "Sunday";
					switch(dow){
					case 0: 
						dayofweek="Sunday";
						break;
					case 1: 
						dayofweek="Monday";
						break;
					case 2: 
						dayofweek="Tuesday";
						break;
					case 3: 
						dayofweek="Wednesday";
						break;
					case 4: 
						dayofweek="Thursday";
						break;
					case 5: 
						dayofweek="Friday";
						break;
					case 6: 
						dayofweek="Saturday";
						break;
					default: break;
					}
					
					String holidayDate = day.getDate();
					String[] dateParts = holidayDate.split("-");
					String month = "";
					if(dateParts[1].equals("01")){
						month = "January";
					}
					else if(dateParts[1].equals("02")){
						month = "February";
					}
					else if(dateParts[1].equals("03")){
						month = "March";
					}
					else if(dateParts[1].equals("04")){
						month = "April";
					}
					else if(dateParts[1].equals("05")){
						month = "May";
					}
					else if(dateParts[1].equals("06")){
						month = "June";
					}
					else if(dateParts[1].equals("07")){
						month = "July";
					}
					else if(dateParts[1].equals("08")){
						month = "August";
					}
					else if(dateParts[1].equals("09")){
						month = "September";
					}
					else if(dateParts[1].equals("10")){
						month = "October";
					}
					else if(dateParts[1].equals("11")){
						month = "November";
					}
					else {
						month = "December";
					}
					
		            builder.add(Json.createObjectBuilder()
		                            .add("FederalHoliday", day.getHolidayName())
		                            .add("Date", month+" "+dateParts[2]+", "+dateParts[0])
		                            .add("DayofWeek", dayofweek)
		                            .add("ID", day.getId()));
		        }
		        JsonArray output = builder.build();
		        PrintWriter out = response.getWriter();
		        JsonWriter writer = Json.createWriter(out);
		        writer.writeArray(output);
		        writer.close();
			}
			else if(query.equals("deleteHoliday")){
				OfficeAdminClientDAO dao = new OfficeAdminClientDAO();
				boolean status = false;
				String responseCode;
				status = dao.deleteHoliday(Integer.parseInt(request.getParameter("id")), (Integer) session.getAttribute("activeUserID"));
				if (status){
					responseCode = "true";
				}
				else{
					responseCode = "false";
				}
				PrintWriter writer = response.getWriter();
				writer.write(responseCode);				
			}
			else if(query.equals("addHoliday")){
				OfficeAdminClientDAO dao = new OfficeAdminClientDAO();
				boolean status = false;
				String responseCode;
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				status = dao.addHoliday(name, date, (Integer) session.getAttribute("activeUserID"), (Integer) session.getAttribute("activeUserLocation"));
				if (status){
					responseCode = "true";
				}
				else{
					responseCode = "false";
				}
				PrintWriter writer = response.getWriter();
				writer.write(responseCode);				
			}
			else if(query.equals("checkEmail")){
				EmployeeClientDAO dao = new EmployeeClientDAO();
				EmployeeBean emp = null;
				String email = request.getParameter("email");
				PrintWriter writer = response.getWriter();
				if(email != null){
					emp = dao.getEmployeeByEmail(email);
					if (emp.getEmail() == null){
						writer.print("false");
					}
					else{
						writer.print("true");
					}
				}
				else{
					writer.print("true");
				}
				writer.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
