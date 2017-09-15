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
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/login.css">
<title><fmt:message key="LOGIN_TITLE" /></title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" flush="true" />
	</header>
	<div id="login-content">	
		<div id="login-form">
			<div id="login-labels">
				<br />
				<label class="input_label"><fmt:message key="LOGIN_EMAIL_LABEL" /></label>
				<br />
				<label class="input_label"><fmt:message key="LOGIN_PASSWORD_LABEL" /></label>
				<html:errors  />
			</div>
			
			<div id="login-inputs-container">
				<div id="login-inputs">
					<div id="login_title"><fmt:message key="LOGIN_TITLE" /></div>
					<html:form action="/Login">
						<html:text name="LoginForm" property="email" />
						<br/>
						<html:password name="LoginForm" property="password" />
						<br/>
						<html:submit value="Login" />
					</html:form>
				</div>
			</div>
		</div>
		<aside>
			<jsp:include page="nav.jsp" flush="true" />
		</aside>
	</div>
</body>
</html>