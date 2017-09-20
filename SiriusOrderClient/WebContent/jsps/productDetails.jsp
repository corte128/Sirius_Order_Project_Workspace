<!DOCTYPE HTML>
<html>
  <head>
    <link rel="stylesheet" type="text/css"
    	href="/SiriusOrderClient/css/productDetails.css">
    	<script src="/SiriusOrderClient/js/productSearch.js"></script>
    	
    	
  </head>
  <body>
    <div class="productDetailsMainContianer">
      <div class="breadCrumbs">
        <a href="https://www.google.com">welcome</a> > <a href="https://www.yahoo.com">${productType}</a> > <a href="https://www.amazon.com">${productName}</a>
      </div>
      <div class="imgAndOrderInfoContainer">
        <div class="productPictureContainer">
          <img src="${productImage}" alt="product image" height="200px" width="200px">
        </div>
        <div class="orderInfoContainer">
          <div class="likesAndPriceContiner">
            <div class="priceContiner">
              ${productPrice}
            </div>
            <div class="likesContainer">
            </div>
          </div>
          <div class="orderFormContainer">
          <form method="post">
            <label for="quantityToAdd">Quantity: </label>
            <input type="number" name="quantityToAdd" value="" required pattern="^[0-9]{1,2}$" maxlength="2" class="quantityInput">
            <button type="submit" onclick="addToCart(${productId})">Add to Cart</button>
          </form>
          </div>
        </div>
      </div>
      <div class="productDetailsText">
        <div class="titleText">Product Details</div>
        <div class="longDescrip">${productDetails}</div>
    </div>
    </div>
  </body>
</html>
