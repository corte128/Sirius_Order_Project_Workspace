
function calcBreakroomTotals()
{
	var prices = document.getElementsByClassName("breakroom-cart-product-price-container");
	var quantities = document.getElementsByClassName("breakroom-cart-product-quantity-input");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		var price = prices[i].innerText.slice(1);
		var quantity = Number(quantities[i].value);
		totalPrice += price * quantity;
		itemTotal += quantity;
	}
	totalPrice = totalPrice.toFixed(2);

	document.getElementById("breakroomTotalQuantityContainer").innerHTML = 'Breakroom Total(' + itemTotal + ' items)';
	document.getElementById("breakroomTotalPriceContainer").innerHTML = '$' + totalPrice;
}

function calcOfficeSuppliesTotals()
{
	var prices = document.getElementsByClassName("office-supplies-cart-product-price-container");
	var quantities = document.getElementsByClassName("office-supplies-cart-product-quantity-input");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		var price = prices[i].innerText.slice(1);
		var quantity = Number(quantities[i].value);
		totalPrice += price * quantity;
		itemTotal += quantity;
	}
	totalPrice = totalPrice.toFixed(2);
	
	document.getElementById("officeSuppliesTotalQuantityContainer").innerHTML = 'Office Supplies Total(' + itemTotal + ' items)';
	document.getElementById("officeSuppliesTotalPriceContainer").innerHTML = '$' + totalPrice;	
}

function calcInkAndTonerTotals()
{
	if(document.getElementById("inkAndTonerTotalQuantityContainer") != null)
	{
		var prices = document.getElementsByClassName("ink-cart-product-price-container");
		var quantities = document.getElementsByClassName("ink-cart-product-quantity-input");
	
		var totalPrice = 0;
		var itemTotal = 0;
		for(var i = 0; i < prices.length; ++i){
			var price = prices[i].innerText.slice(1);
			var quantity = Number(quantities[i].value);
			totalPrice += price * quantity;
			itemTotal += quantity;
		}
		totalPrice = totalPrice.toFixed(2);
		
		document.getElementById("inkAndTonerTotalQuantityContainer").innerHTML = 'Ink & Toner Total(' + itemTotal + ' items)';
		document.getElementById("inkAndTonerTotalPriceContainer").innerHTML = '$' + totalPrice;
	}
}

function removeFromCart(orderID){
	var url = '/SiriusOrderClient/CartServlet?action=removeFromCart&orderId=' + orderID;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var productDiv = document.getElementById("cartOrder" + orderID);
		productDiv.parentNode.removeChild(productDiv);
		calcTotals();
	};
	xhttp.send();
}

function calcTotals()
{
	calcBreakroomTotals();
	calcOfficeSuppliesTotals();
	calcInkAndTonerTotals();
}
function showIncludeOrderModal()
{
	document.getElementById("includeSavedOrderModal").style.display = 'block';
}
function showSaveOrderModal()
{
	document.getElementById("saveOrderModal").style.display = 'block';
}
function populateItemsFromOrders()
{
	var orderNameId = document.getElementById("includeSavedOrderModalOrderSelect").value + 'OrderContainer';
	var orderContainers = document.getElementsByClassName("order-container");
	for(var i = 0; i < orderContainers.length; ++i)
	{
		orderContainers[i].style.display = 'none';
	}
	document.getElementById(orderNameId).style.display = 'block';
}
function updateQuantity(productId, quantity)
{
	var url = "/SiriusOrderClient/CartServlet?action=changeQuantity&productID=" + productId +"&quantity="+quantity;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.send();
}
function calcBreakroomTotalsAndUpdate(productId, orderId)
{
	var quantity = document.getElementById("cartProductQuantityInput" + orderId).value;
	if(quantity <= 0)
	{
		removeFromCart(orderId);
	}
	else
	{
		calcBreakroomTotals();
		updateQuantity(productId, quantity);
	}
}
function calcOfficeSuppliesTotalsAndUpdate(productId, orderId)
{
	var quantity = document.getElementById("cartProductQuantityInput" + orderId).value;
	if(quantity <= 0)
	{
		removeFromCart(orderId);
	}
	else
	{
		calcOfficeSuppliesTotals();
		updateQuantity(productId, quantity);
	}
}
function calcInkAndTonerTotalsAndUpdate(productId, orderId)
{
	var quantity = document.getElementById("cartProductQuantityInput" + orderId).value;
	if(quantity <= 0)
	{
		removeFromCart(orderId);
	}
	else
	{
		calcInkAndTonerTotals();
		updateQuantity(productId, quantity);
	}
}
function selectAllOrderCheckboxes(orderName)
{
	var newCheckedValue = document.getElementById(orderName + "OrderTitleCheckbox").checked;
	var checkBoxes = document.getElementsByClassName("include-order-checkbox-" + orderName);
	for(key in checkBoxes)
	{
		checkBoxes[key].checked = newCheckedValue;
	}
}
function includeOrderInCart()
{
	var curOrderName = document.getElementById("includeSavedOrderModalOrderSelect").value;
	var checkBoxes = document.getElementsByClassName("include-order-checkbox-" + curOrderName);
	for(var index = 0; index < checkBoxes.length; ++index)
	{
		if(checkBoxes[index].checked == true)
		{
			var orderId = checkBoxes[index].id.substring(20);
			var productId = document.getElementById("includeOrderProductId" + orderId).value;
			var xhttp = new XMLHttpRequest();
			var url = "/SiriusOrderClient/CartServlet?action=addOrderToCart&productID=" + productId +"&quantity="+1;
			xhttp.open("GET", url, true);
			xhttp.onreadystatechange = function()
			{
				var response = JSON.parse(this.responseText);
				console.log(this.responseText);
				if(response.length > 0)
				{
					var data = response[0];
					var endDiv = null;
					var classType = '';
					var functionType = '';
					if(data.productType == 'Breakroom')
					{
						endDiv = document.getElementById("breakroomSummaryContainer");
						classType = 'breakroom';
						functionType = 'Breakroom';
					}
					else if(data.productType == 'Office Supplies')
					{
						endDiv = document.getElementById("officeSuppliesSummaryContainer");
						classType = 'office-supplies';
						functionType = 'OfficeSupplies';
					}
					else
					{
						endDiv = document.getElementById("inkAndTonerOrdersSummaryContainer");
						classType = 'ink-cart';
						functionType = 'InkAndToner';
					}
					endDiv.outerHTML = '<div id="cartOrder' + data.orderId + '" \
												class="cart-product-container cart-product-color-alternate-container"> \
											<div class="cart-product-image-container"> \
												<img src="' + data.productImage + '" /> \
											</div> \
											<div class="cart-product-name-container"> \
												' + data.productName + ' \
											</div> \
											<div class="cart-product-price-container \
												' + classType + '-cart-product-price-container"> \
												$' + data.productPrice + ' \
											</div> \
											<div class="cart-product-quantity-container"> \
												<input id="cartProductQuantityInput' + data.orderId + '" \
													class="' + classType + '-cart-product-quantity-input" \
													onchange="calc' + functionType + 'TotalsAndUpdate(' + data.productId + ', ' + data.orderId + ')" \
													type="text" value="' + data.quantity + '"/> \
											</div> \
											<div class="cart-product-action-container"> \
												<div class="glyphicon glyphicon-trash" onclick="removeFromCart(' + data.orderId + ')"></div> \
											</div> \
										</div>' + endDiv.outerHTML;
						
					
				}
			};
			xhttp.send();
		}	
	}
}