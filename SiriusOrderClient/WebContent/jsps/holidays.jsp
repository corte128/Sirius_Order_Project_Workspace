<!DOCTYPE HTML><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="com.sirius.order.client.properties.common" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet"
	href="/SiriusOrderClient/css/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery-ui.theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery.appendGrid-1.7.1.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title><fmt:message key="HOLIDAYS_TITLE" />
</title>

<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-ui-1.12.1.min.js"></script>
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery.appendGrid-1.7.1.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/holidays.js"></script>

<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/holidays.css" />
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/button.css" />
<c:if test="${activeUserID == null}">
	<jsp:forward page="/jsps/welcome.jsp" />
</c:if>
<c:if test="${activeUserType < 2}">
	<jsp:forward page="/jsps/welcome.jsp" />
</c:if>
</head>
<body onresize="resizeAppendGrid()">
	
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<div id="saveSuccess" style="display:none">
		<bean:message key="HOLIDAY_SAVE_SUCCESS" />
	</div>
	
	<div id="saveFailure" style="display:none">
		<bean:message key="HOLIDAY_SAVE_FAILURE" />
	</div>
	
	<div id="deleteSuccess" style="display:none">
		<bean:message key="HOLIDAY_DELETE_SUCCESS" />
	</div>

	<div id="holidays-content">
		<div id="holidays-table">
			<div id="holiday-table-title">
				<h1>
					<fmt:message key="HOLIDAYS_TABLE_TITLE" />
				</h1>
			</div>
			<form action="" method="post">
				<table id="tblAppendGrid" class="table">
				</table>
			</form>
		</div>
		<aside>
			<%@ include file="nav.jsp"%>
		</aside>
	</div>
</body>
</html>