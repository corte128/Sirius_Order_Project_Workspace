<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<fmt:setBundle basename="com.sirius.order.client.properties.common" />
<%@ page import="java.util.Date"%>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/header.css">


<div class="headerContainer">
	<c:set var="now" value="<%= new java.util.Date()%>" />


	<div class="headerRight">
		<div class="headerRightLinks">


			<c:if test="${sessionScope.activeUserName == null}">
				<html:link href="/SiriusOrderClient/jsps/login.jsp">
					<fmt:message key="HEADER_LOGIN" />
				</html:link>
				<html:link
					href="/SiriusOrderClient/NavigationServlet?action=registration">
					<fmt:message key="HEADER_REGISTER" />
				</html:link>
			</c:if>
			<c:if test="${sessionScope.activeUserName != null}">
				<p id="welcomeMessage">
					<fmt:message key="HEADER_WELCOME" />
					${sessionScope.activeUserName}
				</p>

			</c:if>
			<table class="headerNavTable">
				<tr>
					<c:if test="${sessionScope.activeUserType == '2'}">
						<td class="headerLink"><html:link
								href="/SiriusOrderClient/NavigationServlet?action=activateUsers">
								<c:if test="${numAlerts== null}">
									<fmt:message key="HEADER_ALERTS" />
								</c:if>
								<c:if test="${numAlerts!= null}">
									${numAlerts} <fmt:message key="HEADER_ALERTS" />
								</c:if>
							</html:link>
					</c:if>
					<c:if test="${sessionScope.activeUserType == '3'}">
						<td class="headerLink"><html:link
								href="/SiriusOrderClient/NavigationServlet?action=superAdmin"> 
								<fmt:message key="HEADER_ADMIN" />
							</html:link>
						</td>
					</c:if>
					<c:if test="${sessionScope.activeUserName != null}">
						<td class="headerLink"><a
							href="/SiriusOrderClient/LogoutServlet"><fmt:message
									key="HEADER_LOGOUT" /> </a>
						</td>
					</c:if>
				</tr>
			</table>

		</div>
	</div>
</div>
