
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
//		var parentDiv = productDiv.parentElement.nodeName;
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
	
}

function closeSavedOrdersWindow(){
	var saveOrderModal = document.getElementById("saveOrderModal");
	saveOrderModal.style.display = "none";
}

function closeIncludeInOrderWindow(){
	 var includeSavedOrderModal = document.getElementById("includeSavedOrderModal");
	 includeSavedOrderModal.style.display = "none";
}
