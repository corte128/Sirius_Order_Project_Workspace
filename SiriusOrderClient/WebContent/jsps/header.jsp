<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<fmt:setBundle basename="com.sirius.order.client.properties.common" />
<%@ page import="java.util.Date"%>
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/header.css">

<header>
	<div class="headerContainer">
		<c:set var="now" value="<%= new java.util.Date()%>" />

		<div class="headerLeft">
			
		</div>

		<div class="headerCenter">
			
		</div>
		<div class="headerRight">
			<div class="headerRightLinks">


				<c:if test="${sessionScope.activeUserName == null}">
					<html:link
						href="http://www.login.com/">
						<fmt:message key="HEADER_LOGIN" />
					</html:link>
					<span>|</span>
					<html:link href="https://www.register.com/">
						<fmt:message key="HEADER_REGISTER" />
					</html:link>
				</c:if>
				<c:if test="${sessionScope.activeUserName != null}">
					<p>
						<fmt:message key="HEADER_WELCOME" />
						${sessionScope.activeUserName}
					</p>
					<p>
						<a href="/SiriusOrderClient/LogoutServlet"><fmt:message
								key="HEADER_LOGOUT" />
						</a>
				</c:if>
			</div>
			<div class="headerRightTime">
				
			</div>
		</div>
	</div>
</header>
