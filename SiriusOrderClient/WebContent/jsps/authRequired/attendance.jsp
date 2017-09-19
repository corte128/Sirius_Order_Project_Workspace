<!DOCTYPE html>
<html ng-app="attendanceTable">

<head>
<meta charset="utf-8">
<title>Search for attendance records</title>
<link rel="stylesheet" type="text/css" href="css/attendance.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.attendance" />

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

<body>
	<div class="attendancePageContainer" ng-controller="AttendanceCtrl">
		<div id="attendanceSearchBox" class="searchBox">
			<form id="attendanceSearchForm" class="attendanceForm">

				<div class="formColumnOne">
					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_NAME" />
						</div>
						<input type="text" name="name" value="" />
					</div>
					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_EMAIL" />
						</div>
						<input type="text" name="email" />
					</div>

					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_LOCATION" />
						</div>
						<select id="locationSelect" name="location">
							<option value="%">location</option>
							<c:forEach items="${locationList}" var="location">
								<option value="${location}">${location}</option>
							</c:forEach>
						</select>
					</div>

					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_VIEW" />
						</div>
						<select name="view">
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
						<input type="date" name="startDate" />
					</div>
					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_TO_DATE" />
						</div>
						<input type="date" name="endDate" />
					</div>

					<div class="inputFieldContainer">
						<div class="inputFieldLabel">
							<fmt:message key="ATTENDANCE_RANGE" />
						</div>
						<select name="range">
							<option value="thisWeek">This Week</option>
							<option value="lastWeek">Last Week</option>
							<option value="lastTenDays">Last Ten Days</option>
							<option value="thisMonth">This Month</option>
							<option value="lastMonth">Last Month</option>
						</select>

					</div>
					<div id="submitBtn" class="inputFieldContainer">
					<button type="button" name="searchBtn" value="search" class="submitBtn" ng-click="getSearch()">Generate Report</button>
						
					</div>
				</div>
			</form>

		</div>
		<div id="grid1" ui-grid="gridOptions" class="grid"
					ui-grid-auto-resize></div>

	</div>
</body>

</html>