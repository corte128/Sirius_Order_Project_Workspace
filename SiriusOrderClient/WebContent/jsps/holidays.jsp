<!DOCTYPE HTML><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<fmt:setBundle basename="com.sirius.order.client.properties.common"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/holidays.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/jquery-ui.structure.min.css"/>
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/jquery-ui.theme.min.css"/>
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/jquery.appendGrid-1.7.1.css"/>
<link rel="stylesheet" href="/SiriusOrderClient/css/font-awesome-4.7.0/css/font-awesome.min.css">
<title><fmt:message key="REGISTRATION_TITLE" /></title>
<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/jquery-ui-1.12.1.min.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/jquery.appendGrid-1.7.1.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/holidays.js"></script>
</head>
<body>  
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="holidays-content">
		<div id="holidays-table">
		  	<form action="" method="post">
				<table id="tblAppendGrid">
				</table>
		  	</form> 
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>