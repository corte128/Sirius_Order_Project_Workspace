
function calcBreakroomTotals()
{
	var prices = document.getElementsByClassName("breakroom-cart-product-price-container");
	var quantities = document.getElementsByClassName("breakroom-cart-product-quantity-input");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		var price = prices[i].getAttribute("value");
		var quantity = Number(quantities[i].value);
		totalPrice += price * quantity;
		itemTotal += quantity;
	}
	var budget = document.getElementById("breakroomBudgetAmountValue").value;
	if(parseFloat(totalPrice) > parseFloat(budget))
	{
		document.getElementById("breakroomTotalPriceContainer").style.color = 'red';
		document.getElementById("cartGrandTotalContainer").style.color = 'red';
		var difference = totalPrice - budget;
		document.getElementById("breakroomAboveBudgetAmountContainer").innerText = Number(difference).toLocaleString(undefined, { style: 'currency', currency: 'USD' });
		
		document.getElementById("breakroomAboveBudgetContainer").style.display = 'block';
	}
	else
	{
		document.getElementById("breakroomTotalPriceContainer").style.color = 'black';
		document.getElementById("cartGrandTotalContainer").style.color = 'white';
		document.getElementById("breakroomAboveBudgetContainer").style.display = 'none';
	}
	if(document.getElementById("breakroomTotalQuantityContainer") != null)
	{
		document.getElementById("breakroomTotalQuantityContainer").innerHTML = 'Breakroom Total(' + itemTotal + ' items)';
		document.getElementById("breakroomTotalPriceContainer").innerHTML = totalPrice;
	}
}

function calcOfficeSuppliesTotals()
{
	var prices = document.getElementsByClassName("office-supplies-cart-product-price-container");
	var quantities = document.getElementsByClassName("office-supplies-cart-product-quantity-input");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		var price = prices[i].getAttribute("value");
		var quantity = Number(quantities[i].value);
		totalPrice += price * quantity;
		itemTotal += quantity;
	}
	if(document.getElementById("officeSuppliesTotalQuantityContainer") != null)
	{	
		document.getElementById("officeSuppliesTotalQuantityContainer").innerHTML = 'Office Supplies Total(' + itemTotal + ' items)';
		document.getElementById("officeSuppliesTotalPriceContainer").innerHTML = totalPrice;	
	}
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
			var price = prices[i].getAttribute("value");
			var quantity = Number(quantities[i].value);
			totalPrice += price * quantity;
			itemTotal += quantity;
		}
		
		document.getElementById("inkAndTonerTotalQuantityContainer").innerHTML = 'Ink & Toner Total(' + itemTotal + ' items)';
		document.getElementById("inkAndTonerTotalPriceContainer").innerHTML = totalPrice;
			
	}
}
function calcTaxAndGrandTotals()
{
	var breakroomPrices = document.getElementsByClassName("breakroom-cart-product-price-container");
	var breakroomQuantities = document.getElementsByClassName("breakroom-cart-product-quantity-input");
	
	var officePrices = document.getElementsByClassName("office-supplies-cart-product-price-container");
	var officeQuantities = document.getElementsByClassName("office-supplies-cart-product-quantity-input");
	
	var inkPrices = document.getElementsByClassName("ink-cart-product-price-container");
	var inkQuantities = document.getElementsByClassName("ink-cart-product-quantity-input");
	
	var totalPrice = 0;
	for(var i = 0; i < breakroomPrices.length; ++i)
	{
		var price = breakroomPrices[i].getAttribute("value");
		var quantity = Number(breakroomQuantities[i].value);
		totalPrice += price * quantity;
	}
	for(var i = 0; i < officePrices.length; ++i)
	{
		var price = officePrices[i].getAttribute("value");
		var quantity = Number(officeQuantities[i].value);
		totalPrice += price * quantity;
	}
	for(var i = 0; i < inkPrices.length; ++i)
	{
		var price = inkPrices[i].getAttribute("value");
		var quantity = Number(inkQuantities[i].value);
		totalPrice += price * quantity;
	}
	
	var tax = totalPrice*0.0625;
	totalPrice = totalPrice + tax;
	tax = tax.toFixed(2);
	totalPrice = totalPrice.toFixed(2);
	
	document.getElementById("cartTaxAmountContainer").innerText = Number(tax).toLocaleString(undefined, { style: 'currency', currency: 'USD' });
	document.getElementById("cartGrandTotalAmountContainer").innerText = Number(totalPrice).toLocaleString('en-US', { style: 'currency', currency: 'USD' });
}
function removeFromCart(orderID)
{
	var url = '/SiriusOrderClient/CartServlet?action=removeFromCart&orderId=' + orderID;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var productDiv = document.getElementById("cartOrder" + orderID);
		productDiv.parentElement.removeChild(productDiv);
		calcTotals();
		formatPrices();
	};
	xhttp.send();
}

function calcTotals()
{
	calcBreakroomTotals();
	calcOfficeSuppliesTotals();
	calcInkAndTonerTotals();
	calcTaxAndGrandTotals();
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
		calcTaxAndGrandTotals();
		updateQuantity(productId, quantity);
		formatPrices();
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
		calcTaxAndGrandTotals();
		updateQuantity(productId, quantity);
		formatPrices();
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
		calcTaxAndGrandTotals();
		updateQuantity(productId, quantity);
		formatPrices();
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
function selectAllSaveCheckboxes()
{
	var newCheckedValue = document.getElementById("saveOrderTitleCheckbox").checked;
	var checkBoxes = document.getElementsByClassName("save-order-checkbox");
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
			xhttp.open("GET", url, false);
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
						if(endDiv == null)
						{
							
						}
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
												' + classType + '-cart-product-price-container" \
												value="' + data.productPrice + '"> \
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
						
					var saveOrderModalOptionsContainer = document.getElementById("cartOrderContainer");
					saveOrderModalOptionsContainer.innerHTML += '<div class="order-item-container \
																	cart-modal-color-alternate-container"> \
																	<div class="order-item-input-container"> \
																		<input id="saveOrderCheckbox' + data.productId + '" ' +  
																				'class="save-order-checkbox" \
																				type="checkbox" /> \
																	</div> \
																	<div class="order-title-text-container"> \
																		' + data.productName + ' \
																	</div> \
																</div>';
					
					
				}
			};
			xhttp.send();
		}	
	}
	calcTotals();
	formatPrices();
	closeIncludeInOrderWindow();
}
function saveOrder()
{
	var curOrderName = document.getElementById("saveOrderModalOrderInput").value;
	var checkBoxes = document.getElementsByClassName("save-order-checkbox");
	var xhttp = new XMLHttpRequest();
	var url = "/SiriusOrderClient/CartServlet?action=saveOrderFromCart&orderName=" + curOrderName;
	for(var index = 0; index < checkBoxes.length; ++index)
	{
		if(checkBoxes[index].checked == true)
		{
			var productId = checkBoxes[index].id.substring(17);
			url += "&productID=" + productId;
		}
	}
	xhttp.open("GET", url, false);
	xhttp.send();	
	closeSavedOrdersWindow();	
}
function closeSavedOrdersWindow(){
	var saveOrderModal = document.getElementById("saveOrderModal");
	saveOrderModal.style.display = "none";
}

function closeIncludeInOrderWindow(){
	 var includeSavedOrderModal = document.getElementById("includeSavedOrderModal");
	 includeSavedOrderModal.style.display = "none";
}

function formatPrices(){
	var priceContainers = document.getElementsByClassName('cart-product-price-container');
	for(index in priceContainers){
		if(!isNaN(Number(priceContainers[index].innerText)))
		{
			priceContainers[index].innerText
				= Number(priceContainers[index].innerText)
					.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
		}
	}
};
(function loader()
{
	calcTotals();
	formatPrices();
	document.getElementById("breakroomBudgetAmountContainer").innerText = 
		Number(document.getElementById("breakroomBudgetAmountContainer").innerText)
			.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
})();