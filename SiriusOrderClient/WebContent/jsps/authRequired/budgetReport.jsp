<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.common"/>
<html>
	<head>
		<title><fmt:message key="BUDGET_REPORT_TITLE_LABEL" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
  		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-touch.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-animate.js"></script>
    	<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    	<script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    	<script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    
    	<script src="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.js"></script>
    	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.css"/>
    
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/budgetReport.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script src="/SiriusOrderClient/angular/bower_components/ui-bootstrap-custom-2.5.0.min.js"></script>
    	<script src="/SiriusOrderClient/angular/bower_components/ui-bootstrap-custom-tpls-2.5.0.min.js"></script>
    	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/angular/bower_components/ui-bootstrap-custom-2.5.0-csp.css"/>
    	
    	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
    	<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
		
  		<script src="/SiriusOrderClient/js/chartCreator.js"></script>
	</head>
	<body ng-app="chartApp">
		<header>
			<%@ include file="../header.jsp" %>
		</header>
		<main ng-controller="BudgetChartCtrl">
			<div class="container-fluid" style="padding: 0;">
				<div id="budgetSearchBoxAndNavContainer">
					<div id="budgetSearchBoxContainer">
						<div class="row">
							<div class="budget-search-box-label-container col-md-3 col-sm-3 col-xs-6">
								<fmt:message key="BUDGET_REPORT_REPORT_TYPE_LABEL" />
							</div>
							<div class="budget-search-box-input-container col-md-3 col-sm-3 col-xs-6">
								<select id="budgetSearchReportTypeInput">
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
							
							<div class="budget-search-box-label-container col-md-3 col-sm-3 col-xs-6">
								<fmt:message key="BUDGET_REPORT_FROM_DATE_LABEL" />
							</div>
							<div class="budget-search-box-input-container col-md-3 col-sm-3 col-xs-6">
								<input id="budgetSearchFromDateTypeInput" type="date"></input>
							</div>
						</div>
						<div class="row">
							<div class="budget-search-box-label-container col-md-3 col-sm-3 col-xs-6">
								<fmt:message key="BUDGET_REPORT_LOCATION_LABEL" />
							</div>
							<div class="budget-search-box-input-container col-md-3 col-sm-3 col-xs-6">
								<select id="budgetSearchLocationInput">
									<c:choose>
										<c:when test="${sessionScope.activeUserType} == 2">
											<option value="${sessionScope.activeUserLocation}" selected>
												${sessionScope.activeUserLocation}
											</option>
										</c:when>
										<c:otherwise>
											<option value="" selected>
												<fmt:message key="BUDGET_REPORT_SELECT_LABEL" />
												 <fmt:message key="BUDGET_REPORT_LOCATION_LABEL" />
											</option>
											<c:forEach var="location" items="${locations}">
												<option value="${location.getId()}">${location.getCity()}, ${location.getState()}</option>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							
							<div class="budget-search-box-label-container col-md-3 col-sm-3 col-xs-6">
								<fmt:message key="BUDGET_REPORT_TO_DATE_LABEL" />
							</div>
							<div class="budget-search-box-input-container col-md-3 col-sm-3 col-xs-6">
								<input id="budgetSearchToDateTypeInput" type="date"></input>
							</div>
						</div>
						<div class="row">
							<div class="budget-search-box-label-container col-md-3 col-sm-3 col-xs-6">
								<fmt:message key="BUDGET_REPORT_VIEW_LABEL" />
							</div>
							<div class="budget-search-box-input-container col-md-3 col-sm-3 col-xs-6">
								<select id="budgetSearchViewInput">
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
							
							<div id="budgetReportGenerateReportButton" class="col-md-6 col-sm-6 col-xs-12" ng-click="generateChart()">
								<fmt:message key="BUDGET_REPORT_GENERATE_REPORT_LABEL" />
							</div>
						</div>
					</div>
					<aside id="budgetNavContainer">
						<%@ include file="../nav.jsp" %>
					</aside>
				</div>
			</div>
			<div class="row">
				<div id="budgetChartContainer">
				</div>
				<div id="budgetGridContainer">
					<!--<div id="grid1" ui-grid="gridOptions" class="grid" ui-grid-auto-resize></div>
					-->
					<table id="budgetGrid" class="display nowrap dataTable dtr-inline" 
						cellspacing="0" width="100%"
					>
						<thead>
				            <tr>
				                <th> </th>
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
		</main>
	</body>
</html>