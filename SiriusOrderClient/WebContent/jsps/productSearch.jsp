<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>productSearch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productSearch.css">
<script type="text/javascript" src="/SiriusOrderClient/js/productSearch.js"></script>

</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
	<div>
		<div class="searchContainer">
			<form method="get" action="/SiriusOrderClient/ProductSearchServlet" class="search">
				<select class="categorySelect" name="category">
					<option value="0">All</option>
					<option value="3">Ink & Toner</option>
					<option value="1">Breakroom</option>
					<option value="2">Office Supplies</option>
				</select>
				<input type="text" class="search searchBar" name="search"></input>
				<input type="submit" class="search" value="Search"/>
			</form>
		</div>
		<div class="productContainer">
			<c:forEach var="product" items="${Products}">
				<c:set var="currentProduct" value="${product}" scope="request"/>
				<div class="productContainerCard">
					<jsp:include page="productCard.jsp"></jsp:include>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>