<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html ng-app="superAdminTable">
<head>
<title>superAdmin</title>
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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%--<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/superAdmin.css">
<script src="/SiriusOrderClient/js/superAdminTable.js"></script>
</head>
<body>
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<div class="super-admin-container-nav">

		<%-- SUPER ADMIN --%>
		<div class="super-admin-container">
			<%-- BUDGET TABLE --%>
			<div class="super-admin-budget-table" ng-controller="superAdminCtrl">
				<div id="grid1" ui-grid="gridOptions" class="grid"
					ui-grid-auto-resize></div>
			</div>

			<%-- BUTTONS --%>
			<div class="super-admin-button-container">
				<button type="button" class="btn super-admin-button"
					data-toggle="modal" data-target="#assignAdminModal">
					<bean:message key="SUPER_ADMIN_ASSIGN_ADMIN_LABEL" />
				</button>
				<button type="button" class="btn super-admin-button"
					data-toggle="modal" data-target="#addLocationModal">
					<bean:message key="SUPER_ADMIN_ADD_LOCATION_LABEL" />
				</button>
				<button type="button" class="btn super-admin_button">
					<bean:message key="SUPER_ADMIN_ASSIGN_BUDGET_LABEL" />
				</button>
			</div>
		</div>

		<%--ASSIGN ADMIN MODAL --%>
		<div class="modal fade" id="assignAdminModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<bean:message key="SUPER_ADMIN_ASSIGN_ADMIN_LABEL" />
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="form-container">
							<div class="form-group">
								<label for="recipient-name" class="form-control-label">
									<bean:message key="SUPER_ADMIN_LOCATION_LABEL" />: </label> 
									<select id="locationSelect" name="locations"
									class="locations_select">
									<c:forEach items="${locations}" var="location">
										<option value="${location.id}">
											<c:out value="${location.city},${location.state}" />
										</option>
									</c:forEach>
									</select>
									<%-- <input type="text" class="form-control" id="location"> --%>
							</div>
							<div class="form-group">
								<label for="message-text" class="form-control-label"> <bean:message
										key="SUPER_ADMIN_NAME_LABEL" />: </label> 
										<input class="form-control" id="admin_name"></input>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
						</button>
						<button type="button" class="btn super-admin_button">
							<bean:message key="SUPER_ADMIN_ASSIGN_LABEL" />
						</button>
					</div>
				</div>
			</div>
		</div>

		<%--ADD LOCATION MODAL --%>
		<div class="modal fade" id="addLocationModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<bean:message key="SUPER_ADMIN_ADD_LOCATION_LABEL" />
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="form-container">
							<div class="form-group">
								<label for="recipient-name" class="form-control-label">
									<bean:message key="SUPER_ADMIN_CITY_LABEL" />: </label> <input
									type="text" class="form-control" id="location">
							</div>
							<div class="form-group">
								<label for="message-text" class="form-control-label"> <bean:message
										key="SUPER_ADMIN_STATE_LABEL" />: </label> <input
									class="form-control" id="admin_name"></input>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
						</button>
						<button type="button" class="btn super-admin_button">
							<bean:message key="SUPER_ADMIN_ADD_LABEL" />
						</button>
					</div>
				</div>
			</div>
		</div>

		<%-- NAV BAR --%>
		<aside class="super-admin-nav">
			<%@ include file="nav.jsp"%>
		</aside>
	</div>
</body>
</html>