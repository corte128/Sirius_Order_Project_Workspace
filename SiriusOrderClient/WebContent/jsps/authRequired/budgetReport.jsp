<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.common" />
<html>
<head>
<title><fmt:message key="BUDGET_REPORT_TITLE_LABEL" />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/button.css" />
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-touch.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-animate.js"></script>

<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/budgetReport.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<script src="/SiriusOrderClient/js/chartCreator.js"></script>
</head>
<body ng-app="chartApp">
	<header>
		<%@ include file="../header.jsp"%>
	</header>
	<main ng-controller="BudgetChartCtrl">
	<div class="budgetPageContainer">
		<div class="pageTitle">
			<h1><fmt:message key="BUDGET_REPORT_TITLE_LABEL" /></h1>
		</div>
		<div class="container-fluid">

			<div id="search-bar-container">
				<div class="search-bar-column">

					<div class="form-group">
						<label class="budget-search-box-label"><fmt:message
								key="BUDGET_REPORT_REPORT_TYPE_LABEL" />
						</label> <select class="budget-search-box-input" id="budgetSearchReportTypeInput">
							<option value="" selected>
								<fmt:message key="BUDGET_REPORT_SELECT_LABEL" />
								<fmt:message key="BUDGET_REPORT_REPORT_TYPE_LABEL" />
							</option>
							<option value="weekly">
								<fmt:message key="BUDGET_REPORT_WEEKLY_LABEL" />
							</option>
							<option value="monthly">
								<fmt:message key="BUDGET_REPORT_MONTHLY_LABEL" />
							</option>
						</select>
					</div>

					<div class="form-group">
						<label class="budget-search-box-label"><fmt:message
								key="BUDGET_REPORT_LOCATION_LABEL" />
						</label> <select class="budget-search-box-input" id="budgetSearchLocationInput">
							<option value="" selected>
								<fmt:message key="BUDGET_REPORT_SELECT_LABEL" />
								<fmt:message key="BUDGET_REPORT_LOCATION_LABEL" />
							</option>
							<c:choose>
								<c:when test="${sessionScope.activeUserType == 2}">
									<c:forEach var="location" items="${locations}">
										<c:if
											test="${sessionScope.activeUserLocation == location.getId()}">
											<option value="${location.getId()}" selected>${location.getCity()},
												${location.getState()}</option>
										</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach var="location" items="${locations}">
										<option value="${location.getId()}">${location.getCity()},
											${location.getState()}</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
					</div>

					<div class="form-group">
						<label class="budget-search-box-label"><fmt:message
								key="BUDGET_REPORT_VIEW_LABEL" /> </label> <select
							id="budgetSearchViewInput" class="budget-search-box-input">
							<option value="" selected>
								<fmt:message key="BUDGET_REPORT_SELECT_LABEL" />
								<fmt:message key="BUDGET_REPORT_REPORT_TYPE_LABEL" />
							</option>
							<option value="PDF">
								<fmt:message key="BUDGET_REPORT_PDF_LABEL" />
							</option>
							<option value="Display">
								<fmt:message key="BUDGET_REPORT_DISPLAY_LABEL" />
							</option>
						</select>
					</div>

				</div>

				<div class="search-bar-column">

					<div class="form-group">
						<label class="budget-search-box-label"><fmt:message
								key="BUDGET_REPORT_FROM_DATE_LABEL" />
						</label> <input class="budget-search-box-input" id="budgetSearchFromDateTypeInput" type="date"></input>
					</div>

					<div class="form-group">
						<label class="budget-search-box-label"><fmt:message
								key="BUDGET_REPORT_TO_DATE_LABEL" />
						</label> <input class="budget-search-box-input" id="budgetSearchToDateTypeInput" type="date"></input>
					</div>

					<button id="budgetReportGenerateReportButton"
						class="project-button" ng-click="generateChart()">
						<fmt:message key="BUDGET_REPORT_GENERATE_REPORT_LABEL" />
					</button>

				</div>
			</div>
			
			<div id="budgetChartGraph">
				<div id="budgetChartContainer"></div>
			
				<div id="budgetGridContainer">
					<table id="budgetGrid" class="display nowrap dataTable dtr-inline"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th></th>
								<th>Actual</th>
								<th>Budget</th>
								<th>Variance</th>
							</tr>
						</thead>
						<tbody id="budgetGridBody">
	
						</tbody>
					</table>
				</div>
			</div>

			<div class="hideDownloadDiv" id="downloadDiv">
				Your PDF has been generated <br /> <a
					href="/SiriusOrderClient/generatedPDF/budget-pdf.pdf"
					target="_blank">Click here to download</a>
			</div>
		</div>
	</div>
	<aside id="budgetNavContainer">
		<%@ include file="../nav.jsp"%>
	</aside>
	</main>
</body>
</html>