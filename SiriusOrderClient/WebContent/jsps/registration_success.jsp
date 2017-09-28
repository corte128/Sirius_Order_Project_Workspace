<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head> 
<fmt:setBundle basename="com.sirius.order.client.properties.common"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/registration_success.css">
<link rel="stylesheet" href="/SiriusOrderClient/css/font-awesome-4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width" />
<title><fmt:message key="REGISTRATION_TITLE" /></title> 
</head>   
<body>
	<header>  
		<%@ include file="header.jsp" %>
	</header>
	<div id="success-content">
		<div id="check-icon">
			<i class="fa fa-check-circle-o fa-2x" aria-hidden="true"></i>
		</div>
		<div id="success-title"><fmt:message key="REGISTRATION_SUCCESS_TITLE" /></div>
		<p><fmt:message key="REGISTRATION_SUCCESS_CONTENT" /></p>
	</div>
</body>
</html>