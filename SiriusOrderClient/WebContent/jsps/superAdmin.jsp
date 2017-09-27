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
<%-- 
	<script
	src="https://rawgit.com/bassjobsen/Bootstrap-3-Typeahead/master/bootstrap3-typeahead.min.js" /></script>
--%>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery.typeahead.css">
<script src="/SiriusOrderClient/js/jquery.typeahead.js"></script>
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
		
			<%-- ERROR CHECKING --%>
			<c:if test = "${sessionScope.locationAlreadyExists}">
				<div class="super-admin-error">
					<bean:message key="LOCATION_ALREADY_EXISTS_ERROR" />
				</div>
			</c:if>
			<c:if test = "${sessionScope.officeAdminAlreadyExists}">
				<div class="super-admin-error">
					<bean:message key="OFFICE_ADMIN_ALREADY_EXISTS_ERROR" />
				</div>
			</c:if>
			<c:if test = "${sessionScope.invalidBudget}">
				<div class="super-admin-error">
					<bean:message key="INVALID_BUDGET_ERROR" />
				</div>
			</c:if>
			
			<form class="form-container" id="setBudgetForm"
							action="/SiriusOrderClient/SuperAdminServlet?action=setBudget"
							name="setBudget" method="POST">
		
				<%-- BUDGET TABLE --%>
				<div class="super-admin-budget-table" ng-controller="superAdminCtrl">
					<div id="grid1" ui-grid="gridOptions" class="grid"
						ui-grid-auto-resize></div>
				</div>
	
				<%-- BUTTONS --%>
				<div class="super-admin-button-container">
					<button type="button" class="super-admin-button"
						data-toggle="modal" data-target="#assignAdminModal">
						<bean:message key="SUPER_ADMIN_ASSIGN_ADMIN_LABEL" />
					</button>
					<button type="button" class="super-admin-button"
						data-toggle="modal" data-target="#addLocationModal">
						<bean:message key="SUPER_ADMIN_ADD_LOCATION_LABEL" />
					</button>
					<button type="button" class="super-admin-button"
						data-toggle="modal" data-target="#assignBudgetModal">
						<bean:message key="SUPER_ADMIN_ASSIGN_BUDGET_LABEL" />
					</button>
				</div>
			</form>
		</div>
		
			<%--ADD LOCATION MODAL --%>
		<div class="modal fade" id="addLocationModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<form class="form-container" id="addLocationForm"
							action="/SiriusOrderClient/SuperAdminServlet?action=addLocation"
							name="addLocation" method="POST">
							<div class="super-admin-modal-group">
								<label for="recipient-name" class="super-admin-modal-label">
									<bean:message key="SUPER_ADMIN_CITY_LABEL" />: 
								</label> 
								<input type="text" class="super-admin-modal-text-input" id="location" name="city"/>
							</div>
							<div class="super-admin-modal-group">
								<label for="message-text" class="super-admin-modal-label"> 
								<bean:message key="SUPER_ADMIN_STATE_LABEL" />: </label> 
								<select name="state" class="super-admin-modal-text-input">
									<option value="AL">Alabama</option>
									<option value="AK">Alaska</option>
									<option value="AZ">Arizona</option>
									<option value="AR">Arkansas</option>
									<option value="CA">California</option>
									<option value="CO">Colorado</option>
									<option value="CT">Connecticut</option>
									<option value="DE">Delaware</option>
									<option value="DC">District Of Columbia</option>
									<option value="FL">Florida</option>
									<option value="GA">Georgia</option>
									<option value="HI">Hawaii</option>
									<option value="ID">Idaho</option>
									<option value="IL">Illinois</option>
									<option value="IN">Indiana</option>
									<option value="IA">Iowa</option>
									<option value="KS">Kansas</option>
									<option value="KY">Kentucky</option>
									<option value="LA">Louisiana</option>
									<option value="ME">Maine</option>
									<option value="MD">Maryland</option>
									<option value="MA">Massachusetts</option>
									<option value="MI">Michigan</option>
									<option value="MN">Minnesota</option>
									<option value="MS">Mississippi</option>
									<option value="MO">Missouri</option>
									<option value="MT">Montana</option>
									<option value="NE">Nebraska</option>
									<option value="NV">Nevada</option>
									<option value="NH">New Hampshire</option>
									<option value="NJ">New Jersey</option>
									<option value="NM">New Mexico</option>
									<option value="NY">New York</option>
									<option value="NC">North Carolina</option>
									<option value="ND">North Dakota</option>
									<option value="OH">Ohio</option>
									<option value="OK">Oklahoma</option>
									<option value="OR">Oregon</option>
									<option value="PA">Pennsylvania</option>
									<option value="RI">Rhode Island</option>
									<option value="SC">South Carolina</option>
									<option value="SD">South Dakota</option>
									<option value="TN">Tennessee</option>
									<option value="TX">Texas</option>
									<option value="UT">Utah</option>
									<option value="VT">Vermont</option>
									<option value="VA">Virginia</option>
									<option value="WA">Washington</option>
									<option value="WV">West Virginia</option>
									<option value="WI">Wisconsin</option>
									<option value="WY">Wyoming</option>
								</select>
							</div>
							<div class="modal-footer">
								<button type="button" class="super-admin-button"
									data-dismiss="modal">
									<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
								</button>
								<button form="addLocationForm" type="submit"
									class="super-admin-button">
									<bean:message key="SUPER_ADMIN_ADD_LABEL" />
								</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<%--ASSIGN ADMIN MODAL --%>
		<div class="modal fade" id="assignAdminModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<form class="form-container" id="addLocationForm"
							action="/SiriusOrderClient/SuperAdminServlet?action=assignAdmin"
							name="assignAdmin" method="POST">
							<div class="super-admin-modal-group">
								<label for="recipient-name" class="super-admin-modal-label">
									<bean:message key="SUPER_ADMIN_LOCATION_LABEL" />: 
								</label> 
								<select id="locationSelect" name="locations" class="super-admin-modal-text-input">
									<c:forEach items="${locations}" var="location">
										<option value="${location.id}">
											<c:out value="${location.city},${location.state}" />
										</option>
									</c:forEach>
								</select>
								<%-- <input type="text" class="form-control" id="location"> --%>
							</div>
							<div class="super-admin-modal-group">
								<label for="message-text" class="super-admin-modal-label"> 
									<bean:message key="SUPER_ADMIN_NAME_LABEL" />: 
								</label>
								<div class="typeahead__container">
									<div class="typeahead__field">
										<span class="typeahead__query"> 
										<input class="super-admin-modal-text-input" name="admin" id="typeaheadAdminInput"
											type="text" placeholder="Search" autocomplete="off">
										</span>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="super-admin-button"
									data-dismiss="modal">
									<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
								</button>
								<button type="submit" class="super-admin-button">
									<bean:message key="SUPER_ADMIN_ASSIGN_LABEL" />
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<%--ASSIGN BUDGET MODAL--%>
		<div class="modal fade" id="assignBudgetModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<form class="form-container" id="addLocationForm"
							action="/SiriusOrderClient/SuperAdminServlet?action=assignAdmin"
							name="assignAdmin" method="POST">
							<div class="super-admin-modal-group">
								<label for="recipient-name" class="super-admin-modal-label">
									<bean:message key="SUPER_ADMIN_ARE_YOU_SURE" /> 
								</label> 
							</div>
							<div class="modal-footer">
								<button type="button" class="super-admin-button"
									data-dismiss="modal">
									<bean:message key="SUPER_ADMIN_NO" />
								</button>
								<button type="submit" class="super-admin-button" form="setBudgetForm">
									<bean:message key="SUPER_ADMIN_YES" />
								</button>
							</div>
						</form>
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