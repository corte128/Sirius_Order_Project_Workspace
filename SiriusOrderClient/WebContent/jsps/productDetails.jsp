<!DOCTYPE HTML>
<html>
<head>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/productDetails.css">
<script src="/SiriusOrderClient/js/productSearch.js"></script>
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>

</head>
<body>
	<header>
		<%@ include file="/jsps/header.jsp"%>
	</header>
	<div class="flexBoxContainer">
		<div class="productDetailsPageDiv">
			<div class="productDetailsMainContianer">
				<div class="breadCrumbs">
					<a href="jsps/welcome.jsp">welcome</a> >
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
								<span class="glyphicon glyphicon-heart clickable-like" onclick='addToWishlist(${currentProduct.getId()})'></span>
								<span class="likesCount">${numLikes}</span> 
							</span>
						</div>
						<div class="orderFormContainer">
							<form>
								<label class = "productLabel" for="quantityToAdd">Quantity: </label> <input
									type="number" name="quantityToAdd" value="" required
									pattern="^[0-9]{1,2}$" maxlength="2" class="quantityInput">
								<button id="addToCartButton" onclick="addToCart(${productId})">Add
									to Cart</button>
							</form>
						</div>
					</div>
				</div>
				<div class="productDetailsText">
					<div class="titleText">Product Details</div>
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
