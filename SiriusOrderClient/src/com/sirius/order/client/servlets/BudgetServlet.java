package com.sirius.order.client.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.sirius.attendancews.attendance.wsdl.AttendanceRecordBean;
import com.sirius.searchws.search.wsdl.ActualvBudgetBean;
import com.sirius.searchws.search.wsdl.SearchClientDAO;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		String view = request.getParameter("view");

		int locationId = Integer.parseInt(request.getParameter("locationId"));
		String reportType = request.getParameter("reportType");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromDate = null;
		Date toDate = null;
		boolean isValidDate = true;
		try 
		{
			toDate = df.parse(request.getParameter("toDate"));
			fromDate = df.parse(request.getParameter("fromDate"));
		} 
		catch (ParseException e) 
		{
			isValidDate = false;
		}
		List<ActualvBudgetBean> budgetReports = null;
		if(isValidDate){
			budgetReports = SearchClientDAO.budgetSearch(locationId, fromDate, toDate, reportType);
		}
		if(budgetReports != null){
			if(view.equalsIgnoreCase("Display")){
				JsonArrayBuilder builder = Json.createArrayBuilder();
				for(ActualvBudgetBean budgetReport : budgetReports)
				{
					builder.add(Json.createArrayBuilder().add(budgetReport.getTime())
						.add(budgetReport.getBudget())
						.add(budgetReport.getActual()));
				}
				JsonArray output = builder.build();
				
				PrintWriter out = response.getWriter();
				JsonWriter writer = Json.createWriter(out);
				writer.writeArray(output);
				writer.close();
			}
			else{
				PDPage myPage = new PDPage();
				PDDocument mainDocument = new PDDocument();
				
				String path = "C:/Users/IEUser/IBM/rationalsdp/Sirius_Order_Project_Workspace/SiriusOrderClient/WebContent/generatedPDF/budget-pdf.pdf";
				PDPageContentStream contentStream = new PDPageContentStream(mainDocument, myPage);
				List<List<ActualvBudgetBean>> subLists = separateList(budgetReports, 35); 
				
				for(List<ActualvBudgetBean> list: subLists){
					myPage = new PDPage();
					mainDocument.addPage(myPage);
					contentStream = new PDPageContentStream(
							mainDocument, myPage);
					String[][] contentForTable = new String[list.size()+1][4];
					for (int i = 0; i < list.size()+1; i++){
						if(i==0){
							contentForTable[0][0] = "Date Range";
							contentForTable[0][1] = "Actual";
							contentForTable[0][2] = "Budget";
							contentForTable[0][3] = "Variance";
						}else{
							contentForTable[i][0] = list.get(i-1).getTime();
							contentForTable[i][1] = addPaddingToString(NumberFormat.getCurrencyInstance().format(list.get(i-1).getActual()), 30);
							contentForTable[i][2] = addPaddingToString(NumberFormat.getCurrencyInstance().format(list.get(i-1).getBudget()), 30);
							contentForTable[i][3] = addPaddingToString(NumberFormat.getCurrencyInstance().format((list.get(i-1).getActual().subtract(list.get(i-1).getBudget()))), 30);
						}
					}
					
					drawTable(myPage, contentStream, 750, 30, contentForTable);
					contentStream.close();
				}
				contentStream.close();
				mainDocument.save(new File(path));
				mainDocument.close();
			}
		}
		else{
			JsonObjectBuilder objBuilder = Json.createObjectBuilder();
			objBuilder.add("invalidDate", true);
			JsonObject output = objBuilder.build();
			PrintWriter out = response.getWriter();
			JsonWriter writer = Json.createWriter(out);
			writer.write(output);
			writer.close();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private List<List<ActualvBudgetBean>> separateList(List<ActualvBudgetBean> objects, int numOfObjects){
		/**/
		List<List<ActualvBudgetBean>> lists = new ArrayList<List<ActualvBudgetBean>>();
		int length = objects.size();
		for(int i = 0; i < length; i+= numOfObjects){
			int endpoint = (i+numOfObjects);
			if(endpoint >= length) {
				endpoint = length;
			}
			lists.add(objects.subList(i, endpoint));
		}
		return lists;
	}
	
	private String addPaddingToString(String input, int limit){
		int padding = limit - input.length();
		String output = "";
		for(int i = 0; i < padding; i++){
			output += " ";
		}
		return output + input;
	}
	
	private void drawTable(PDPage page, PDPageContentStream contentStream,
			float y, float margin, String[][] content) throws IOException {
		final int rows = content.length;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;
		final float cellMargin = 5f;

		// draw the rows
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			nextx += colWidth;
		}

		// now add the text
		contentStream.setFont(PDType1Font.HELVETICA, 12);

		float textx = margin + cellMargin;
		float texty = y - 15;
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx, texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;
		}
	}

	
}
