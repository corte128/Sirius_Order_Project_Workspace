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
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/SiriusOrderClient/js/calcCartTotal.js"></script>
</head> 
<body onload="calcTotals()">
	<div id="cartContentRows">
		<c:if test="${breakroomOrders.size() > 0}">
			<div class="cart-section-title-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
				</div>
				<div class="cart-section-title-text-container">
					<fmt:message key="REVIEW_CART_BREAKROOM_LABEL" />
				</div>
				<span class="include-saved-order-container" onclick="showIncludeOrderModal()">
					<fmt:message key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" />
				</span>
			</div>
			<c:set var="breakroomIndex" value="0" scope="page" />
		</c:if>
		<c:forEach var="product" items="${breakroomProducts}">
			<div id="cartOrder${breakroomOrders.get(breakroomIndex).getId()}" 
				class="cart-product-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
					<img src="${product.getImage()}" />
				</div>
				<div class="cart-product-name-container">
					${product.getName()}
				</div>
				<div class="cart-product-price-container 
					breakroom-cart-product-price-container">
					$${product.getPrice()}
				</div>
				<div class="cart-product-quantity-container">
					<input onchange="calcBreakroomTotals()" 
						class="breakroom-cart-product-quantity-input"
						onchange="calcBreakroomTotals()"
					 	type="text" value="${breakroomOrders.get(breakroomIndex).getQuantity()}"/>
				</div>
				<div class="cart-product-action-container">
					<div class="glyphicon glyphicon-trash" onclick="removeFromCart(${breakroomOrders.get(breakroomIndex).getId()})"></div>
				</div>
			</div>
			<c:set var="breakroomIndex" value="${breakroomIndex + 1}" scope="page"/>
		</c:forEach>
		<c:if test="${breakroomOrders.size() > 0}">
			<div id="breakroomSummaryContainer" class="cart-product-color-alternate-container cart-summary-container">
				<div class="cart-product-image-container">
				</div>
				<div id="breakroomTotalQuantityContainer" 
					class="cart-product-name-container">
				</div>
				<div id="breakroomTotalPriceContainer" 
					class="cart-product-price-container">
				</div>
				<div class="cart-product-quantity-container">
				</div>
				<div class="cart-product-action-container">
				</div>
				<span class="save-order-container" onclick="showSaveOrderModal()">
					<fmt:message key="CART_SAVE_THE_ORDER_LABEL" />
				</span>
			</div>
		</c:if>
		
		<c:if test="${officeSuppliesOrders.size() > 0}">
			<div class="cart-section-title-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
				</div>
				<div class="cart-section-title-text-container">
					<fmt:message key="REVIEW_CART_OFFICE_SUPPLIES_LABEL" />
				</div>
				<span class="include-saved-order-container" onclick="showIncludeOrderModal()">
					<fmt:message key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" />
				</span>
			</div>
			<c:set var="officeSuppliesIndex" value="0" scope="page" />
		</c:if>
		<c:forEach var="product" items="${officeSuppliesProducts}">
			<div id="cartOrder${officeSuppliesOrders.get(officeSuppliesIndex).getId()}" 
				class="cart-product-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
					<img src="${product.getImage()}" />
				</div>
				<div class="cart-product-name-container">
					${product.getName()}
				</div>
				<div class="cart-product-price-container
					office-supplies-cart-product-price-container">
					$${product.getPrice()}
				</div>
				<div class="cart-product-quantity-container">
					<input onchange="calcOfficeSuppliesTotals()" 
						class="office-supplies-cart-product-quantity-input"
						onchange="calcOfficeSuppliesTotals()"
						type="text" value="${officeSuppliesOrders.get(officeSuppliesIndex).getQuantity()}"/>
				</div>
				<div class="cart-product-action-container">
					<div class="glyphicon glyphicon-trash" onclick="removeFromCart(${officeSuppliesOrders.get(officeSuppliesIndex).getId()})"></div>
				</div>
			</div>
			<c:set var="officeSuppliesIndex" value="${officeSuppliesIndex + 1}" scope="page"/>
		</c:forEach>
		<c:if test="${officeSuppliesOrders.size() > 0}">
			<div id="officeSuppliesSummaryContainer" class="cart-product-color-alternate-container cart-summary-container">
				<div class="cart-product-image-container">
				</div>
				<div id="officeSuppliesTotalQuantityContainer" 
					class="cart-product-name-container">
				</div>
				<div id="officeSuppliesTotalPriceContainer" 
					class="cart-product-price-container">
				</div>
				<div class="cart-product-quantity-container">
				</div>
				<div class="cart-product-action-container">
				</div>
				<span class="save-order-container" onclick="showSaveOrderModal()">
					<fmt:message key="CART_SAVE_THE_ORDER_LABEL" />
				</span>
			</div>
		</c:if>
		
		<c:if test="${inkAndTonerOrders.size() > 0}">
			<div class="cart-section-title-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
				</div>
				<div class="cart-section-title-text-container">
					<fmt:message key="REVIEW_CART_INK_AND_TONER_LABEL" />
				</div>
				<span class="include-saved-order-container" onclick="showIncludeOrderModal()">
					<fmt:message key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" />
				</span>
			</div>
			<c:set var="inkIndex" value="0" scope="page" />
		</c:if>
		<c:forEach var="product" items="${inkAndTonerProducts}">
			<div id="cartOrder${inkAndTonerOrders.get(inkIndex).getId()}" 
				class="cart-product-container cart-product-color-alternate-container">
				<div class="cart-product-image-container">
					<img src="${product.getImage()}" />
				</div>
				<div class="cart-product-name-container">
					${product.getName()}
				</div>
				<div class="cart-product-price-container
					ink-cart-product-price-container">
					$${product.getPrice()}
				</div>
				<div class="cart-product-quantity-container">
					<input onchange="calcInkAndTonerTotals()" 
						class="ink-cart-product-quantity-input"
						onchange="calcInkAndTonerTotals()"
						type="text" value="${inkAndTonerOrders.get(inkIndex).getQuantity()}"/>
				</div>
				<div class="cart-product-action-container">
					<div class="glyphicon glyphicon-trash" onclick="removeFromCart(${inkAndTonerOrders.get(inkIndex).getId()})"></div>
				</div>
			</div>
			<c:set var="inkIndex" value="${inkIndex + 1}" scope="page"/>
		</c:forEach>
	</div>	
	<c:if test="${inkAndTonerOrders.size() > 0}">
		<div id="inkAndTonerOrdersSummaryContainer" class="cart-product-color-alternate-container cart-summary-container">
			<div class="cart-product-image-container">
			</div>
			<div id="inkAndTonerTotalQuantityContainer" 
				class="cart-product-name-container">
			</div>
			<div id="inkAndTonerTotalPriceContainer" 
				class="cart-product-price-container">
			</div>
			<div class="cart-product-quantity-container">
			</div>
			<div class="cart-product-action-container">
			</div>
			<span class="save-order-container" onclick="showSaveOrderModal()">
				<fmt:message key="CART_SAVE_THE_ORDER_LABEL" />
			</span>
		</div>
	</c:if>
		
	<div id="includeSavedOrderModal" class="cart-modal">
		<div id="includeSavedOrderModalTitleContainer">
			<span id="includeSavedOrderModalTitleTextContainer">
				<fmt:message key="CART_SAVED_ORDERS_LABEL" />
			</span>
			<select onchange="populateItemsFromOrders">
			
			</select>
		</div>
	</div>
</body>
</html>