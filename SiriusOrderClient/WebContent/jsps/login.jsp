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
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/login.css">
<meta name="viewport" content="width=device-width" />
<title><fmt:message key="LOGIN_TITLE" /></title>
</head>
<body>    
	<header>  
		<%@ include file="header.jsp" %>
	</header>  
	<div id="login-content">	  
		<div id="login-form">
			<div id="login-labels">
				<br /> 
				<label class="input-label"><fmt:message key="LOGIN_EMAIL_LABEL" /></label>
				<br />
				<label class="input-label"><fmt:message key="LOGIN_PASSWORD_LABEL" /></label>
			</div>
			
			<div id="login-inputs-container">
				<div id="login-inputs">
					<div id="login-title"><fmt:message key="LOGIN_TITLE" /></div>
					<html:form action="/Login">
						<input id="input-field" required type="email" name="email" placeholder=<fmt:message key="LOGIN_EMAIL_LABEL" /> />
						<br />
						<input id="input-field" required type="password" name="password" placeholder=<fmt:message key="LOGIN_PASSWORD_LABEL" /> />
						<br />
						<html:submit styleId="login-button" value="Login" />
						<c:if test="${errs.contains('LOGIN_CREDENTIALS')}">
						<div id="errors"><fmt:message key="LOGIN_CREDENTIALS" /></div>
						</c:if>
					</html:form>
				</div>
			</div>
		</div>
<%-- 		<aside>
			<%@ include file="nav.jsp" %>
		</aside>--%>
	</div>
</body>
</html>