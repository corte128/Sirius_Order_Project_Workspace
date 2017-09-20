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
					String[] date = day.getDate().split("-");
					String month = "";
					switch (Integer.parseInt(date[1])){
					case 2: month="February";
					case 3: month="March";
					case 4: month="April";
					case 5: month="May";
					case 6: month="June";
					case 7: month="July";
					case 8: month="August";
					case 9: month="September";
					case 10: month="October";
					case 11: month="November";
					case 12: month="December";
						break;
					default: break;
					}
					int dow = day.getDayOfWeek();
					String dayofweek = "";
					switch(dow){
					case 1: break;
					default: break;
					}
					
		            builder.add(Json.createObjectBuilder()
		                            .add("FederalHoliday", day.getHolidayName())
		                            .add("Date", day.getDate())
		                            .add("DayofWeek", dow)
		                            .add("ID", day.getId()));
		        }
		        JsonArray output = builder.build();
		        PrintWriter out = response.getWriter();
		        JsonWriter writer = Json.createWriter(out);
		        writer.writeArray(output);
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
