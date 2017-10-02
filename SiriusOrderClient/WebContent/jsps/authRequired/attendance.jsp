<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Search for attendance records</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.attendance" />
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
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
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/attendance.css">

</head>
<c:choose>
	<c:when
		test="${sessionScope.activeUserType == null || sessionScope.activeUserType == 1}">
		<c:redirect url="/jsps/login.jsp" />
	</c:when>
	<c:otherwise>
		<body ng-app="attendanceTable">
			<header>
				<%@ include file="/jsps/header.jsp"%>
			</header>
			<div class="flexContainer">


				<fmt:setBundle
					basename="com.sirius.order.client.properties.attendance" />



				<div class="attendancePageContainer" ng-controller="AttendanceCtrl">
					<div class="pageTitle">
						<h1>Attendance</h1>
					</div>
					<div class="errorMessage" id="viewSelectError">
						<p>
							<fmt:message key="ATTENDANCE_VIEW_ERROR" />
						</p>
					</div>
					<div class="errorMessage" id="dateSelectError">
						<p>
							<fmt:message key="ATTENDANCE_DATE_ERROR" />
						</p>
					</div>



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
									<c:if test="${sessionScope.activeUserType == 3 }">
										<select id="locationSelect" name="location">
											<option value="%">select</option>
											<c:forEach items="${locations}" var="location">
												<option value="${location.getId()}">${location.getCity()}
													, ${location.getState()}</option>
											</c:forEach>
										</select>
									</c:if>

									<c:if test="${sessionScope.activeUserType==2 }">
										<c:forEach items="${locations}" var="location">
											<c:if test="${ activeUserLocation == location.getId()}">
												<input type="text"
													value="${location.getCity()}, ${location.getState()}"
													readonly>
												<input id="locationSelect" type="hidden"
													value="${location.getId()}">
											</c:if>
										</c:forEach>
									</c:if>
								</div>

								<div class="inputFieldContainer">
									<div class="inputFieldLabel">
										<fmt:message key="ATTENDANCE_VIEW" />
									</div>
									<select id="view" name="view" required>
										<option>
											<fmt:message key="ATTENDANCE_SELECT" />
										</option>
										<option value="display">
											<fmt:message key="ATTENDANCE_DISPLAY" />
										</option>
										<option value="PDF">
											<fmt:message key="ATTENDANCE_PDF" />
										</option>
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
										<option value="%">
											<fmt:message key="ATTENDANCE_SELECT" />
										</option>
										<option value="thisWeek">
											<fmt:message key="ATTENDANCE_THIS_WEEK" />
										</option>
										<option value="lastWeek">
											<fmt:message key="ATTENDANCE_LAST_WEEK" />
										</option>
										<option value="lastTenDays">
											<fmt:message key="ATTENDANCE_TEN_DAYS" />
										</option>
										<option value="thisMonth">
											<fmt:message key="ATTENDANCE_THIS_MONTH" />
										</option>
										<option value="lastMonth">
											<fmt:message key="ATTENDANCE_LAST_MONTH" />
										</option>
									</select>

								</div>
								<div id="submitBtn" >
									<button type="button" name="searchBtn" value="search"
										class="submitBtn" ng-click="getSearch()">
										<fmt:message key="ATTENDANCE_BUTTON_LABEL" />
									</button>

								</div>
							</div>
						</form>

					</div>
					<div class="noInfoMessage" id="noInfoFoundError">
						<p>
							<fmt:message key="ATTENDANCE_NO_INFO" />
						</p>
					</div>
					<div class="hideDownloadDiv" id="downloadDiv">
						<fmt:message key="ATTENDANCE_PDF_GENERATED" />
						<br /> <a
							href="/SiriusOrderClient/generatedPDF/attendance-pdf.pdf"
							target="_blank"><fmt:message key="ATTENDANCE_CLICK_DOWNLOAD" />
						</a>
					</div>
					<div id="displayDiv" ui-grid="gridOptions" ui-grid-pagination
						ui-grid-auto-resize class="grid"></div>

				</div>
				<aside>
					<%@ include file="/jsps/nav.jsp"%>
				</aside>
			</div>
		</body>
	</c:otherwise>
</c:choose>
</html>