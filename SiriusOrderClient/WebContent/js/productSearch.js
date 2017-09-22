/**
 * 
 */
function addToCart(productID)
{
	var xhttp = new XMLHttpRequest();
	var quantity = getElementbyID("quantityToAdd").value;
	if (quantity == null){
		quantity = 1;
	}
	var url = "/SiriusOrderClient/CartServlet?query=addToCart&productID=" + productID +"&quantity="+quantity;
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
//		var response = xhttp.responseText;
//		if(response != null){
//			console.log(response);
//			$('#tblAppendGrid').appendGrid('load', JSON.parse(response));
//		}
//		else{
//			console.log("Results are empty");
//		}
		//TO DO alert user
	};
	xhttp.send();
}

function searchProducts(){
	
	var search = document.getElementById("search");
	var category = document.getElementById("category");
	alert(search.value + ", " + category.value);
	var url = "/SiriusOrderClient/ProductSearchServlet?query=searchProducts&category=" + category.value + "&search=" +  search.value;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = JSON.parse(xhttp.responseText);
		$('#productContainer').empty();
		var productCardContainer = document.getElementById('productContainer');
		
		var bootStrap = document.createElement("link");
		bootStrap.rel = "stylesheet";
		bootStrap.type = "text/css";
		bootStrap.href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css";
		productCardContainer.appendChild(bootStrap);
		
		var style = document.createElement("link");
		style.rel = "stylesheet";
		style.type = "text/css";
		style.href = "/SiriusOrderClient/css/productCard.css";
		productCardContainer.appendChild(style);
		for(key in response){
			var image = document.createElement("img");
			image.src = response[key].Image;
			image.setAttribute("class", "productImage");
			image.alt=response[key].Name;
			var link = document.createElement("a");
			link.href='/SiriusOrderClient/NavigationServlet?action=productDetails&id=' + response[key].ID;
			link.appendChild(image);
			var imageContainer = document.createElement("div");
			imageContainer.setAttribute("class", "imageContainer");
			imageContainer.appendChild(link);
			
			var name = document.createElement("p");
			name.setAttribute("class", "nameLabel");
			name.innerHTML = response[key].Name;
			var nameDiv = document.createElement("div");
			nameDiv.setAttribute("class", "productLabel");
			nameDiv.appendChild(name);
			
			var spanHeart = document.createElement("span");
			spanHeart.setAttribute("class", "glyphicon glyphicon-heart");
			var spanPrice = document.createElement("span");
			spanPrice.innerHTML = '$' + response[key].Price;
			var divLikesAndPrice = document.createElement("div");
			divLikesAndPrice.setAttribute("class", "likesAndPrice");
			divLikesAndPrice.appendChild(spanHeart);
			divLikesAndPrice.appendChild(spanPrice);
			
			var addToCartBtn = document.createElement("input");
			addToCartBtn.type="button";
			addToCartBtn.onclick="addToCart(" + response[key].id + ")";
			addToCartBtn.value="Add To Cart";
			
			var productCard = document.createElement("div");
			productCard.setAttribute("class", "productCard");
			productCard.appendChild(imageContainer);
			productCard.appendChild(nameDiv);
			productCard.appendChild(divLikesAndPrice);
			productCard.appendChild(addToCartBtn);

			productCardContainer.appendChild(productCard);
		}
	};
	xhttp.send();
}

function vericalHandler(){
	
	var container = document.getElementById("");
	var contentHeight = container.offsetHeight;
	
}

function selectedOption(value){
	alert(value);
	$("#category").val(value);
}

