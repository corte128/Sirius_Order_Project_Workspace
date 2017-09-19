package com.sirius.order.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sirius.searchws.search.wsdl.ActualvBudgetBean;

/**
 * Servlet implementation class BudgetServlet
 */
public class BudgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action").equals("searchBudget"))
		{
			getBudgetSearchJSON(request, response);
		}
	}

	/**
	 * returns a json list containing the budgets that match the search criteria
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getBudgetSearchJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<ActualvBudgetBean> budgetReports = null;
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for(ActualvBudgetBean budgetReport : budgetReports)
		{
			builder.add(Json.createObjectBuilder()
							.add("Time", budgetReport.getTime())
							.add("Actual", budgetReport.getActual())
							.add("Budget", budgetReport.getBudget()));
		}
		JsonArray output = builder.build();
		
		PrintWriter out = response.getWriter();
		JsonWriter writer = Json.createWriter(out);
		writer.writeArray(output);
		writer.close();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
