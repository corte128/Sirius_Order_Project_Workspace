package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		SuperadminProxy superAdminProxyObj = new SuperadminProxy();
		List<OfficeBean> offices = superAdminProxyObj.getOffices();
		    
		//adding offices to Json Object
        for(OfficeBean office : offices){
        	jab.add(Json.createObjectBuilder().add("Location", office.getLocation())
                    .add("Admin Name", office.getAdminName())
                    .add("Admin Email", office.getAdminEmail())
                    .add("No of Employees", office.getNumberOfEmployees())
                    .add("Recommended Budget", office.getRecommendedBudget().doubleValue())
                    .add("Allotted Budget", office.getAllottedBudget().doubleValue()));
        }
		
        //writing to the response
        JsonArray array = jab.build();
        PrintWriter out = response.getWriter();
        JsonWriter writer = Json.createWriter(out);
        writer.writeArray(array);
        writer.close();
	}
}
