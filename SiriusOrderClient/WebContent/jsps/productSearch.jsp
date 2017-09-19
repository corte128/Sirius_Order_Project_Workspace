<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>
<title>productSearch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productSearch.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
	<div>
		<div class="searchContainer">
			<html:form action="/ProductSearch" styleClass="search">
				<html:select styleClass="categorySelect" property="category">
					<option value="all">All</option>
					<option value="ink_and_toner">Ink & Toner</option>
					<option value="breakroom">Breakroom</option>
					<option value="office_supplies">Office Supplies</option>
				</html:select>
				<input type="text" class="search searchBar"></input>
				<html:submit styleClass="search" value="Search" />
			</html:form>
		</div>
			    <c:forEach var="product" items="${Products}">
			    	<c:set var="currentProduct" value="${product}"></c:set>
					<jsp:include page="productCard.jsp"></jsp:include>
				</c:forEach>
	</div>
</body>
</html>