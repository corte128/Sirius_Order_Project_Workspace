function getProducts()
{	
	console.log("in products");
	var url = "/SiriusOrderClient/WishlistServlet";
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = JSON.parse(xhttp.responseText);
		$('#wishlist-product-card-container-id').empty();
		var productContainer = document.getElementById('wishlist-product-card-container-id');
		

		for(key in response)
		{
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
			spanHeart.setAttribute("class", "glyphicon glyphicon-heart clickable-like");
			spanHeart.onclick=(function() {
				var id = response[key].ID;
				return function(){
					addToWishlist(id);
				}
			})();
			/*==============================*/
			var spanPrice = document.createElement("span");
			spanPrice.innerHTML = '$' + response[key].Price;
			var numOfLikes = document.createElement("span");
			numOfLikes.id = "numOfLikes" + response[key].ID;
			numOfLikes.setAttribute("class", "num-of-likes")
			numOfLikes.innerHTML = response[key].Likers.length;
			var divLikesAndPrice = document.createElement("div");
			divLikesAndPrice.setAttribute("class", "likesAndPrice");
			divLikesAndPrice.appendChild(spanHeart);
			divLikesAndPrice.appendChild(numOfLikes);
			divLikesAndPrice.appendChild(spanPrice);
			
			var addToCartBtn = document.createElement("input");
			addToCartBtn.setAttribute("class", "addToCartBtn");
			addToCartBtn.type="button";
			addToCartBtn.onclick=(function (){
				var id = response[key].ID;
				return function(){
					addToCart(id);
				};
			})();
			addToCartBtn.value="Add To Cart";
			
			var productCard = document.createElement("div");
			productCard.setAttribute("class", "productCard");
			productCard.appendChild(imageContainer);
			productCard.appendChild(nameDiv);
			productCard.appendChild(divLikesAndPrice);
			productCard.appendChild(addToCartBtn);
			
			var productCardContainer = document.createElement("div");
			productCardContainer.setAttribute("class", "productContainerCard");

			productCardContainer.appendChild(productCard);
			
			productContainer.appendChild(productCardContainer);
		}
	};
	xhttp.send();
}

function addToWishlist(productID){
	console.log("in add to wishlist");
	//'/SiriusOrderClient/ProductSearchServlet?action=addToWishlist&id=' + response[key].ID
	var url = '/SiriusOrderClient/ProductSearchServlet?action=addToWishlist&id=' + productID;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = JSON.parse(xhttp.responseText);
		var numOfEmps = response.length;
		var likesContainer = document.getElementById('numOfLikes' + productID);
		likesContainer.innerHTML = numOfEmps;
		getProducts();
	};
	xhttp.send();
	
}


