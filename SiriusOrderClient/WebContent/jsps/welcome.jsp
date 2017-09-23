<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/welcome.css">
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>  
	<fmt:setBundle basename="com.sirius.order.client.properties.common" /> 
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="welcome-container">
		<div class="welcome-content">
			<%-- CAROUSEL --%>
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<!-- <ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol> -->

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="/SiriusOrderClient/assets/border_1.png" alt="Los Angeles"
							style="width: 100%;">
					</div>

					<div class="item">
						<img src="/SiriusOrderClient/assets/border_2.png" alt="Chicago"
							style="width: 100%;">
					</div>

					<div class="item">
						<img src="/SiriusOrderClient/assets/border_3.png" alt="New york"
							style="width: 100%;">
					</div>
				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span> </a> <a class="right carousel-control"
					href="#myCarousel" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span> </a>
			</div>

			<%-- OFFICE OPTIONS --%>
			<div class="supply-category-card-container">
				<html:link page="/Welcome.do?type=3&action=productSearch"
					styleClass="supply-category-card">
					<div class="supply-category-card-title">
						<fmt:message key="WELCOME_INK_AND_TONER_LABEL" />
					</div>
					<div class="supply-category-card-border">
						<img src="/SiriusOrderClient/assets/yellow_border.png" />
					</div>
					<div class="supply-category-card-picture">
						<img src="/SiriusOrderClient/assets/ink_and_toner.png" />
					</div>
				</html:link>
				<html:link page="/Welcome.do?type=1&action=productSearch"
					styleClass="supply-category-card">
					<div class="supply-category-card-title">
						<fmt:message key="WELCOME_BREAKROOM_LABEL" />
					</div>
					<div class="supply-category-card-border">
						<img src="/SiriusOrderClient/assets/orange_border.png" />
					</div>
					<div class="supply-category-card-picture">
						<img src="/SiriusOrderClient/assets/breakroom.png" />
					</div>
				</html:link>
				<html:link page="/Welcome.do?type=2&action=productSearch"
					styleClass="supply-category-card">
					<div class="supply-category-card-title">
						<fmt:message key="WELCOME_OFFICE_SUPPLIES_LABEL" />
					</div>
					<div class="supply-category-card-border">
						<img src="/SiriusOrderClient/assets/grey_border.png" />
					</div>
					<div class="supply-category-card-picture">
						<img src="/SiriusOrderClient/assets/office_supplies.png" />
					</div>
				</html:link>
			</div>

		</div>
		
		<%-- ASIDE --%>
		<aside>
			<%@ include file="nav.jsp"%>
		</aside>
	</div>
</body>
</html>