<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="com.sirius.order.client.properties.common"/>
<html>
	<head>
		<title>budgetReport</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/budgetReport.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		<script src="/SiriusOrderClient/js/chartCreator.js"></script>
	</head>
	<body>
		<header>
			<%@ include file="header.jsp" %>
		</header>
		<main>
			<div class="container-fluid">
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
									<option value="Weekly">
										<fmt:message key="BUDGET_REPORT_WEEKLY_LABEL" />
									</option>
									<option value="Monthly">
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
							
							<div id="budgetReportGenerateReportButton">
								<fmt:message key="BUDGET_REPORT_GENERATE_REPORT_LABEL" />
							</div>
						</div>
					</div>
					<aside id="budgetNavContainer">
						<%@ include file="nav.jsp" %>
					</aside>
				</div>
			</div>
			<div id="budgetChartContainer">
			</div>
		</main>
	</body>
</html>