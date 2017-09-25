
function calcBreakroomTotals()
{
	alert(0);
	var prices = document.getElementsByClassName("breakroom-cart-product-price-container");
	var quantities = document.getElementsByClassName("breakroom-cart-product-quantity-input");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		var price = prices[i].innerText.slice(1);;
		var quantity = Number(quantities[i].value);
		console.log(price);
		console.log(quantity);
		totalPrice += (price * quantity);
		itemTotal += quantity;
	}
	totalPrice = totalPrice.toFixed(2);
	

	document.getElementById("breakroomTotalQuantityContainer").innerHTML = 'Breakroom Total(' + itemTotal + ' items)';
	document.getElementById("breakroomTotalPriceContainer").innerHTML = '$' + totalPrice;
}

function calcOfficeSuppliesTotals()
{
	var prices = document.getElementsByClassName("office-supplies-cart-product-price-container");
	var quantities = document.getElementsByClassName("office-supplies-cart-product-price-container");

	var totalPrice = 0;
	var itemTotal = 0;
	
	for(var i = 0; i < prices.length; ++i){
		price = prices[i].slice(1);
		totalPrice += price * quantities[i];
		itemTotal += quantities[i];
	}
	
	document.getElementById("officeSuppliesTotalQuantityContainer").innerHTML = 'Office Supplies Total(' + quantity + ' items)';
	document.getElementById("officeSuppliesTotalPriceContainer").innerHTML = '$' + totalPrice;	
}

function calcInkAndTonerTotals()
{
	var prices = document.getElementsByClassName("ink-and-toner-cart-product-price-container");
	var quantities = document.getElementsByClassName("ink-and-toner-supplies-cart-product-price-container");

	var totalPrice = 0;
	var itemTotal = 0;
	
	for(var i = 0; i < prices.length; ++i){
		price = prices[i].slice(1);
		totalPrice += price * quantities[i];
		itemTotal += quantities[i];
	}
	
	document.getElementById("inkAndTonerTotalQuantityContainer").innerHTML = 'Ink & Toner Total(' + quantity + ' items)';
	document.getElementById("inkAndTonerTotalPriceContainer").innerHTML = '$' + totalPrice;
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
	};
	xhttp.send();
}

function calcTotals()
{
	calcBreakroomTotals();
	calcOfficeSuppliesTotals();
	calcInkAndTonerTotals();
}
