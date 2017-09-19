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
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/visitors.css">
	<title><fmt:message key="VISITORS_TITLE" /></title>
</head>
<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="visitors-content">
		<div id="visitors-form">
			<div id="visitors-labels">
				<br />
				<label><fmt:message key="VISITORS_FROM_DATE_LABEL" /></label>
				<br />
				<label><fmt:message key="VISITORS_TO_DATE_LABEL" /></label>
				<br />
				<label><fmt:message key="VISITORS_COUNT_LABEL" /></label>
				<br />
				<label><fmt:message key="VISITORS_COMMENT_LABEL" /></label>
			</div>
			<div id="visitors-input-fields">
				<html:form action="/Visitors">
					<input required type="date" name="from_date" />
					<br />
					<input required type="date" name="to_date" />
					<br />
					<html:text property="count"></html:text>
					<html:textarea property="comment"></html:textarea>
					<html:submit styleId="visitor-add-button" value=Add><fmt:message key="VISITORS_ADD_BUTTON" /></html:submit>
				</html:form>
			</div>
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>