<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html ng-app="superAdminTable">
<head>
<title>superAdmin</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
    src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
<script
    src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-touch.js"></script>
<script
    src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.3.3.js"></script>
<script
    src="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.js"></script>
<link rel="stylesheet" type="text/css"
    href="/SiriusOrderClient/angular/bower_components/angular-ui-grid/ui-grid.css" />
<script src="/SiriusOrderClient/js/superAdminTable.js"></script>
</head>
<body>
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<%-- NAV BAR --%>
	<aside>
		<%@ include file="nav.jsp" %>
	</aside>

	<%-- SUPER ADMIN --%>
	<div class="super-admin-container">
		<%-- BUDGET TABLE --%>
		<div class="super-admin-budget-table" ng-controller="SuperAdminCtrl">
			<div id="grid1" ui-grid="gridOptions" class="grid">
			
			</div>
			<%--<table class="table table-responsive table-striped">
				<thead>
					<tr>
						<td>
							<bean:message key="SUPER_ADMIN_LOCATION_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_NAME_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_EMAIL_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_EMPLOYEE_NUMBERS_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_RECOMMENDED_BUDGET_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_ALLOTTED_BUDGET_LABEL" />
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${offices}"var="office" >
						<tr>
							<td>
								${office.location}
							</td>
							<td>
								${office.adminName}
							</td>
							<td>
								${office.adminEmail}
							</td>
							<td>
								${office.numberOfEmployees}
							</td>
							<td>
								${office.recommendedBuget}
							</td>
							<td>
								${office.allottedBudget}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table> --%>
		</div>

		<%-- BUTTONS --%>
		<div class="super-admin-button-container">
			<button>
				<bean:message key="SUPER_ADMIN_ASSIGN_ADMIN_LABEL" />
			</button>
		</div>
		<div class="super-admin-button-container">
			<button>
				<bean:message key="SUPER_ADMIN_ADD_LOCATION_LABEL" />
			</button>
		</div>
	</div>

</body>
</html>