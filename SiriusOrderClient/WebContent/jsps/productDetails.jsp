<!DOCTYPE HTML>
<html>
<head>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/productDetails.css">
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="/SiriusOrderClient/js/productSearch.js"></script>
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>

</head>
<body>
	<header>
		<%@ include file="/jsps/header.jsp"%>
	</header>
	<fmt:setBundle basename="com.sirius.order.client.properties.common" />
	
	<div id="likedLimit">
						You have 5 products in Wishlist already.
	</div>
	<div class="flexBoxContainer">
		<div class="productDetailsPageDiv">
			<div class="pageTitle">
						<h1>Product Details</h1>
					</div>
			<div id="successMessage" class="successMessage">
				<fmt:message key="PRODUCT_DETAILS_SUCCESS" />
			</div>
			<div id="errorMessage" class="errorMessage">
				<fmt:message key="PRODUCT_DETAILS_ERROR" />
			</div>
			<div class="productDetailsMainContianer">
				<div class="breadCrumbs">
					<a href="/SiriusOrderClient/jsps/welcome.jsp"><fmt:message key="PRODUCT_DETAILS_WELCOME" /></a> >
					<html:link
						page="/Welcome.do?type=${productTypeId}&action=productSearch">${productType}</html:link>
					> <a href="#">${productName}</a>
				</div>
				<div class="imgAndOrderInfoContainer">
					<div class="productPictureContainer">
						<img src="${productImage}" alt="product image" height="200px"
							width="200px">
					</div>
					<div class="orderInfoContainer">
						<div class="likesAndPriceContiner">
							<span class="priceContiner">$ ${productPrice}</span> 
							<span class="likesContainer"> 
								<span class="glyphicon glyphicon-heart clickable-like" onclick='addToWishlist(${productId})'></span>
								<span class="likesCount" id="numOfLikes${productId}" onmouseover="createModal(${productId})" onmouseout="deleteModal('likesModal${productId}')">${numLikes}</span> 
							</span>
						<div id="likesModal${productId}" class="likes-modal">
										<c:forEach var="user" items="${LikesForProduct}">
											<div>
												${user}
											</div>
										</c:forEach>
									</div>
						</div>
						<c:if test="${sessionScope.activeUserType == 2}">
						<div class="orderFormContainer">
							
								<label class = "productLabel" for="quantityToAdd"><fmt:message key="PRODUCT_DETAILS_QUANTITY" /> </label> <input
									type="number" id = "quantityToAdd" name="quantityToAdd" value="" required
									pattern="^[0-9]{1,2}$" maxlength="2" class="quantityInput">
								<button id="addToCartButton" onclick="addToCart(${productId})"><fmt:message key="PRODUCT_DETAILS_BUTTON_LABEL" /></button>
							
						</div>
						</c:if>
					</div>
				</div>
				<div class="productDetailsText">
					
					<div class="longDescrip">${productDetails}</div>
				</div>
			</div>
			
		</div>
		<aside>
			<%@ include file="/jsps/nav.jsp"%>
		</aside>
	</div>
</body>
</html>
