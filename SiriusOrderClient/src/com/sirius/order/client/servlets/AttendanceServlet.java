package com.sirius.order.client.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

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

				System.out.println(startDate);

				System.out.println(endDate);
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
//				} else {
//				startDate = "1900-01-01";
//				endDate = "3000-12-31";
//			}
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

//			List<List> dataList = new ArrayList<List>();
//			dataList.add(new ArrayList<String>(Arrays.asList("Name", "Email",
//					"Date", "Location")));
//			for (AttendanceRecordBean record : attendanceRecords) {
//				dataList.add(new ArrayList<String>(Arrays.asList(
//						record.getAttendantName(), record.getAttendantEmail(),
//						record.getAttendantDate(),
//						record.getAttendantLocation())));
//			}

			if (view.equalsIgnoreCase("display")) {
				JsonArrayBuilder builder = Json.createArrayBuilder();
				for (AttendanceRecordBean record : attendanceRecords) {
					builder.add(Json.createObjectBuilder()
							.add("Name", record.getAttendantName())
							.add("Email", record.getAttendantEmail())
							.add("Date", record.getAttendantDate())
							.add("Location", record.getAttendantLocation()));
				}
				JsonArray output = builder.build();

				PrintWriter out = response.getWriter();
				JsonWriter writer = Json.createWriter(out);
				writer.writeArray(output);
				writer.close();
			} else if (view.equalsIgnoreCase("pdf")) {
				PDPage myPage = new PDPage();
				PDDocument mainDocument = new PDDocument();
				
				mainDocument.addPage(myPage);
				String ctx = request.getContextPath();
				
				
				String path = "C:/Users/IEUser/IBM/rationalsdp/Sirius_Order_Project_Workspace/SiriusOrderClient/WebContent/generatedPDF/test-pdf.pdf";
				PDPageContentStream contentStream = new PDPageContentStream(mainDocument, myPage);
				
				//set a count to keep track of the pages we need to create
				int count =0;
				//start the creation of the contentStream
				contentStream.beginText();
				
				//set the font
				contentStream.setFont(PDType1Font.HELVETICA, 16);
				
				
				//set the leading
				contentStream.setLeading(14.5f);
				
				//set the position for the first line
				contentStream.newLineAtOffset(25, 725);
				//format our strings so we get a nice output
				String formatString = "%-s%-s%-s%-s";
				
				// this will be our header
				String headerTextString = String.format(formatString, "Name", "Email", "Date", "Location");
				
				//add the content of header to the document
				contentStream.showText(headerTextString);
				contentStream.newLine();
				// now we set the text smaller for the actual content
				contentStream.setFont(PDType1Font.HELVETICA, 14);
				//next we get the content and output it line by line
				
				
				for(AttendanceRecordBean record: attendanceRecords){
					String contentToAdd = String.format(formatString, record.getAttendantName(), record.getAttendantEmail(), record.getAttendantDate(), record.getAttendantLocation());
					contentStream.showText(contentToAdd);
					contentStream.newLine();
					count++;
					if (count==47){
						contentStream.endText();
						contentStream.close();
						myPage = new PDPage();
						mainDocument.addPage(myPage);
						contentStream = new PDPageContentStream(mainDocument, myPage);
						contentStream.beginText();
						contentStream.setFont(PDType1Font.HELVETICA, 14);
						contentStream.setLeading(14.5f);
						contentStream.newLineAtOffset(25, 725);
						count =0;
					}
				}
				
				//end the text
				contentStream.endText();
				
				//close our contentStream
				contentStream.close();
				
				
				//save the document
				mainDocument.save(new File(path));
				
				//close the document
				
				mainDocument.close();
//				// Dummy Table
//				float margin = 50;
//				// starting y position is whole page height subtracted by top
//				// and bottom margin
//				float yStartNewPage = myPage.getMediaBox().getHeight()
//						- (2 * margin);
//				// we want table across whole page width (subtracted by left and
//				// right margin ofcourse)
//				float tableWidth = myPage.getMediaBox().getWidth()
//						- (2 * margin);
//
//				boolean drawContent = true;
//				float yStart = yStartNewPage;
//				float bottomMargin = 70;
//				// y position is your coordinate of top left corner of the table
//				float yPosition = 550;
//
//				BaseTable dataTable = new BaseTable(yStart, yStartNewPage,
//						bottomMargin, tableWidth, margin, mainDocument, myPage,
//						true, drawContent);
//				DataTable t = new DataTable(dataTable, myPage);		//TODO: crashes here
//				t.addListToTable(dataList, DataTable.HASHEADER);
//				dataTable.draw();
//				mainDocument.addPage(myPage);
//				mainDocument.save("C:/pdfTestFiles/testPDF.pdf");
//				mainDocument.close();

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

}
