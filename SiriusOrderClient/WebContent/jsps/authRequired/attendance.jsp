<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Search for attendance records</title>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/attendance.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.attendance" />
<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-touch.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script>
	
<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
 
<script
	src="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.js"></script>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.css" />
<script src="/SiriusOrderClient/js/attendanceTable.js"></script>
</head>
	
<body ng-app="attendanceTable">
	<header>
		<%@ include file="/jsps/header.jsp"%>
	</header>
	<div class="flexContainer">
		

		<fmt:setBundle
			basename="com.sirius.order.client.properties.attendance" />
		<div class="attendancePageContainer" ng-controller="AttendanceCtrl">
			<div id="attendanceSearchBox" class="searchBox">
				<form id="attendanceSearchForm" class="attendanceForm">

					<div class="formColumnOne">
						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_NAME" />
							</div>
							<input id="name" type="text" name="name" value='' />
						</div>
						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_EMAIL" />
							</div>
							<input id="email" type="text" name="email" value='' />
						</div>

						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_LOCATION" />
							</div>
							<c:if test = "${sessionScope.activeUserType == 3 }">
							<select id="locationSelect" name="location">
								<option value="%">select</option>
								<c:forEach items="${locations}" var="location">
									<option value="${location.getId()}">${location.getCity()}
										, ${location.getState()}</option>
								</c:forEach>
							</select>
							</c:if>
							
							<c:if test = "${sessionScope.activeUserType==2 }">
								<c:forEach items="${locations}" var="location">
									<c:if test="${ activeUserLocation == location.getId()}">
									<input type="text" value="${location.getCity()}, ${location.getState()}" readonly>
									<input  id="locationSelect" type="hidden" value="${location.getId()}">
									</c:if>
								</c:forEach>
							</c:if>
						</div>

						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_VIEW" />
							</div>
							<select id="view" name="view">
								<option value="%">select</option>
								<option value="display">Display</option>
								<option value="PDF">PDF</option>
							</select>
						</div>

					</div>
					<div class="formColumnTwo">
						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_FROM_DATE" />
							</div>
							<input id="startDate" type="date" name="startDate"
								pattern="\d{4}-\d{2}-\d{2}" placeholder="yyyy-mm-dd" />
						</div>
						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_TO_DATE" />
							</div>
							<input id="endDate" type="date" name="endDate"
								pattern="\d{4}-\d{2}-\d{2}" placeholder="yyyy-mm-dd" />
						</div>

						<div class="inputFieldContainer">
							<div class="inputFieldLabel">
								<fmt:message key="ATTENDANCE_RANGE" />
							</div>
							<select id="range" name="range">
								<option value="%">select</option>
								<option value="thisWeek">This Week</option>
								<option value="lastWeek">Last Week</option>
								<option value="lastTenDays">Last Ten Days</option>
								<option value="thisMonth">This Month</option>
								<option value="lastMonth">Last Month</option>
							</select>

						</div>
						<div id="submitBtn" class="inputFieldContainer">
							<button type="button" name="searchBtn" value="search"
								class="submitBtn" ng-click="getSearch()">Generate
								Report</button>

						</div>
					</div>
				</form>

			</div>
			<div class="hideDownloadDiv" id="downloadDiv">
				your pdf has been generated <a href="/SiriusOrderClient/generatedPDF/test-pdf.pdf" target="_blank">click here to download</a>
			</div>
			<div id="grid1" ui-grid="gridOptions" ui-grid-pagination  ui-grid-auto-resize class="grid"></div>

		</div>
		<aside>
			<%@ include file="/jsps/nav.jsp"%>
		</aside>
	</div>
</body>

</html>