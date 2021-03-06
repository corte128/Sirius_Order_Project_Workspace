package com.sirius.order.client.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.New;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;

import com.sirius.attendancews.attendance.wsdl.AttendanceProxyDAO;
import com.sirius.attendancews.attendance.wsdl.AttendanceRecordBean;
import com.sirius.product.service.main.product.wsdl.ProductBean;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AttendanceProxyDAO attendanceService = new AttendanceProxyDAO();
		String action = request.getParameter("action");
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (action == null) {

		}

		if (action.equalsIgnoreCase("getAttendance")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String location = request.getParameter("location");
			String view = request.getParameter("view");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String range = request.getParameter("range");

			name = '%' + name + '%';
			email = '%' + email + '%';

			if (range.equalsIgnoreCase("thisWeek")) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				startDate = df.format(cal.getTime());

				cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				endDate = df.format(cal.getTime());

				

				
			} else if (range.equalsIgnoreCase("lastWeek")) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				cal.add(Calendar.DATE, -7);
				startDate = df.format(cal.getTime());

				cal.add(Calendar.DATE, 4);
				endDate = df.format(cal.getTime());

			} else if (range.equalsIgnoreCase("lastTenDays")) {
				endDate = df.format(cal.getTime());

				cal.add(Calendar.DATE, -10);
				startDate = df.format(cal.getTime());

			} else if (range.equalsIgnoreCase("thisMonth")) {
				cal.set(Calendar.DAY_OF_MONTH,
						cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				startDate = df.format(cal.getTime());

				cal.set(Calendar.DAY_OF_MONTH,
						cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = df.format(cal.getTime());
			} else if (range.equalsIgnoreCase("lastMonth")) {
				cal.add(Calendar.MONTH, -1);
				cal.set(Calendar.DATE, 1);
				startDate = df.format(cal.getTime());

				cal.set(Calendar.DATE,
						cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = df.format(cal.getTime());
			}
			// } else {
			// startDate = "1900-01-01";
			// endDate = "3000-12-31";
			// }
			if (!startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				startDate = "";
			}
			if (!endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				endDate = "";
			}
			if (range.equals("%")) {
				range = "";
			}

			if (startDate == "" && endDate == "" && range == "") {
				startDate = "1900-01-01";
				endDate = "3000-12-31";
			}

			ArrayList<AttendanceRecordBean> attendanceRecords = attendanceService
					.getAttendanceRecords(name, email, location, startDate,
							endDate);
			
			 
			if (view.equalsIgnoreCase("display")) {
				getJsonData(attendanceRecords, response);
			} else if (view.equalsIgnoreCase("pdf")) {
				
				
				List<List<AttendanceRecordBean>> subLists = separateList(attendanceRecords, 25); 
				
				
				
	
					
				
				
				PDPage myPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
				PDDocument mainDocument = new PDDocument();

				
				String ctx = request.getContextPath();

				String path = "C:/Users/IEUser/IBM/rationalsdp/Sirius_Order_Project_Workspace/SiriusOrderClient/WebContent/generatedPDF/attendance-pdf.pdf";
				PDPageContentStream contentStream = new PDPageContentStream(
						mainDocument, myPage);

				
			for(List<AttendanceRecordBean> list: subLists){
					myPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
					mainDocument.addPage(myPage);
					contentStream = new PDPageContentStream(
							mainDocument, myPage);
					String[][] contentForTable = new String[list.size()+1][4];
					Calendar calendar = Calendar.getInstance();
					String[] dateArray = new String[3];
					SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
					String formatedDate = "";
					for (int i = 0; i < list.size()+1; i++){
						if(i==0){
							contentForTable[0][0] = "Name";
							contentForTable[0][1] = "Email";
							contentForTable[0][2] = "Date";
							contentForTable[0][3] = "Location";
						}else{
						contentForTable[i][0] = list.get(i-1).getAttendantName();
						contentForTable[i][1] = list.get(i-1).getAttendantEmail();
						
						
						dateArray = list.get(i-1).getAttendantDate().split("-");
						calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]));
						calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1])-1);
						calendar.set(Calendar.DATE, Integer.parseInt(dateArray[2]));
						formatedDate = sFormat.format(calendar.getTime());
						
						contentForTable[i][2] = formatedDate;
						contentForTable[i][3] = list.get(i-1).getAttendantLocation();
						}
						
					}
					
					drawTable(myPage, contentStream, 550, 40, contentForTable);
					contentStream.close();
				}

			// close our contentStream
			contentStream.close();

			
			// save the document
			mainDocument.save(new File(path));

			// close the document

			mainDocument.close();
					
//				// set a count to keep track of the pages we need to create
//				int count = 0;
//				// start the creation of the contentStream
//				contentStream.beginText();
//
//				// set the font
//				contentStream.setFont(PDType1Font.HELVETICA, 16);
//
//				// set the leading
//				contentStream.setLeading(14.5f);

//				// set the position for the first line
//				contentStream.newLineAtOffset(25, 725);
//				// format our strings so we get a nice output
//				String formatString = "%-s%-s%-s%-s";
//
//				// this will be our header
//				String headerTextString = String.format(formatString, "Name",
//						"Email", "Date", "Location");
//
//				// add the content of header to the document
//				contentStream.showText(headerTextString);
//				contentStream.newLine();
//				// now we set the text smaller for the actual content
//				contentStream.setFont(PDType1Font.HELVETICA, 14);
//				// next we get the content and output it line by line
				
				
				
//				for (AttendanceRecordBean record : attendanceRecords) {
//					String contentToAdd = String.format(formatString,
//							record.getAttendantName(),
//							record.getAttendantEmail(),
//							record.getAttendantDate(),
//							record.getAttendantLocation());
//					contentStream.showText(contentToAdd);
//					contentStream.newLine();
//					count++;
//					if (count == 47) {
//						contentStream.endText();
//						contentStream.close();
//						myPage = new PDPage();
//						mainDocument.addPage(myPage);
//						contentStream = new PDPageContentStream(mainDocument,
//								myPage);
//						contentStream.beginText();
//						contentStream.setFont(PDType1Font.HELVETICA, 14);
//						contentStream.setLeading(14.5f);
//						contentStream.newLineAtOffset(25, 725);
//						count = 0;
//					}
//				}

				// end the text
//				contentStream.endText();
				
			
				

//				// close our contentStream
//				contentStream.close();
//
//				// save the document
//				mainDocument.save(new File(path));
//
//				// close the document
//
		getJsonData(attendanceRecords, response);

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
	
	private List<List<AttendanceRecordBean>> separateList(List<AttendanceRecordBean> objects, int numOfObjects){
		/**/
		List<List<AttendanceRecordBean>> lists = new ArrayList<List<AttendanceRecordBean>>();
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
	private void getJsonData(ArrayList<AttendanceRecordBean> attendanceRecords, HttpServletResponse response)throws ServletException, IOException{
		JsonArrayBuilder builder = Json.createArrayBuilder();
		Calendar calendar = Calendar.getInstance();
		String[] dateArray = new String[3];
		SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
		String formatedDate = "";
		for (AttendanceRecordBean record : attendanceRecords) {
			
			
			dateArray = record.getAttendantDate().split("-");
			
			calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]));
			calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1])-1);
			calendar.set(Calendar.DATE, Integer.parseInt(dateArray[2]));
			formatedDate = sFormat.format(calendar.getTime());
			
			builder.add(Json.createObjectBuilder()
					.add("Name", record.getAttendantName())
					.add("Email", record.getAttendantEmail())
					.add("Date", formatedDate)
					.add("Location", record.getAttendantLocation()));
		}
		JsonArray output = builder.build();

		PrintWriter out = response.getWriter();
		JsonWriter writer = Json.createWriter(out);
		writer.writeArray(output);
		writer.close();	
	}
}