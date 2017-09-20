<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<fmt:setBundle basename="com.sirius.order.client.properties.common"/>

<html>
<head>
<title>reviewCart</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<fmt:message key="REVIEW_CART_BREAKROOM_LABEL" />
			<div>
			<img src="${product.getImage()}"></img>
			<span>Name</span>
			<span>$12.34</span>
			<input onchange="calcBreakroomTotals()" type="text" value="7"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
	<c:forEach var="product" items="${breakroomProducts}">
		<div>
			<img src="${product.getImage()}"></img>
			<span>${product.getName()}</span>
			<span>${product.getPrice()}</span>
			<input onchange="calcBreakroomTotals()" type="text" value="${product.getQuantity()}"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
	</c:forEach>
	<fmt:message key="REVIEW_CART_OFFICE_SUPPLIES_LABEL" />
	<c:forEach var="product" items="${officeSuppliesProducts}">
		<div>
			<img src="${product.getImage()}"></img>
			<span>${product.getName()}</span>
			<span>${product.getPrice()}</span>
			<input onchange="calcOfficeSuppliesTotals()" type="text" value="${product.getQuantity()}"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
	</c:forEach>
	<fmt:message key="REVIEW_CART_INK_AND_TONER_LABEL" />
	<c:forEach var="product" items="${inkAndTonerProducts}">
		<div>
			<img src="${product.getImage()}"></img>
			<span>${product.getName()}</span>
			<span>${product.getPrice()}</span>
			<input onchange="calcInkAndTonerTotals()" type="text" value="${product.getQuantity()}"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
	</c:forEach>
	
</body>
</html>