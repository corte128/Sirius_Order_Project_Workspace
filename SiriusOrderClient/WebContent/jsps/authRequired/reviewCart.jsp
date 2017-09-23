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
	<link rel="stylesheet" href="/SiriusOrderClient/css/reviewCart.css">
</head>
<body>
	<c:if test="${breakroomOrders.size() > 0}">
		<fmt:message key="REVIEW_CART_BREAKROOM_LABEL" />
		<c:set var="breakroomIndex" value="0" scope="page" />
	</c:if>
	
	<c:forEach var="product" items="${breakroomProducts}">
		<div id="cartOrder${breakroomOrders.get(breakroomIndex).getId()}" class="breakroom-cart-product-container">
			<div class="breakroom-cart-product-image-container">
				<img src="${product.getImage()}"></img>
			</div>
			<div class="breakroom-cart-product-name-container">
				${product.getName()}
			</div>
			<div class="breakroom-cart-product-price-container">
				$${product.getPrice()}
			</div>
			<div class="breakroom-cart-product-quantity-container">
				<input onchange="calcBreakroomTotals()" type="text" value="${breakroomOrders.get(breakroomIndex).getQuantity()}"/>
			</div>
			<div class="breakroom-cart-product-action-container">
				<a href="#" class="glyphicon glyphicon-trash"></a>
			</div>
		</div>
		<c:set var="breakroomIndex" value="${breakroomIndex + 1}" scope="page"/>
	</c:forEach>
	<c:if test="${officeSuppliesOrders.size() > 0}">
		<fmt:message key="REVIEW_CART_OFFICE_SUPPLIES_LABEL" />
		<c:set var="officeSuppliesIndex" value="0" scope="page" />
	</c:if>
	<c:forEach var="product" items="${officeSuppliesProducts}">
		<div class="office-supplies-cart-product-container">
			<img src="${product.getImage()}"></img>
			<span>${product.getName()}</span>
			<span>$${product.getPrice()}</span>
			<input onchange="calcOfficeSuppliesTotals()" type="text" value="${officeSuppliesOrders.get(officeSuppliesIndex).getQuantity()}"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
		<c:set var="officeSuppliesIndex" value="${officeSuppliesIndex + 1}" scope="page"/>
	</c:forEach>
	<c:if test="${inkAndTonerOrders.size() > 0}">
		<fmt:message key="REVIEW_CART_INK_AND_TONER_LABEL" />
		<c:set var="inkIndex" value="0" scope="page" />
	</c:if>
	<c:forEach var="product" items="${inkAndTonerProducts}">
		<div class="ink-and-toner-cart-product-container">
			<img src="${product.getImage()}"></img>
			<span>${product.getName()}</span>
			<span>$${product.getPrice()}</span>
			<input onchange="calcInkAndTonerTotals()" type="text" value="${inkAndTonerOrders.get(inkIndex).getQuantity()}"/>
			<a href="#" class="glyphicon glyphicon-trash"></a>
		</div>
		<c:set var="inkIndex" value="${inkIndex + 1}" scope="page"/>
	</c:forEach>
	
</body>
</html>