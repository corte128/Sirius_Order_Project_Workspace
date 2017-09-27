/**
 * 
 */


function addToCart(productID)
{
	var xhttp = new XMLHttpRequest();
	var quantityElement = document.getElementById("quantityToAdd");
    var quantity = 1;
   
    if (quantityElement != null)
    {
    	
        quantity = quantityElement.value;
        
    }
	var url = "/SiriusOrderClient/CartServlet?action=addToCart&productID=" + productID +"&quantity="+quantity;
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
//		TO DO alert user
	};
	xhttp.send();
}

 function simpleAlert(){
	console.log("THIS IS BROKE");
	alert("this one is working");
}

function selectedOption(value)
{
	alert("something is working");
	$("#category").val(value);
}

function searchProducts()
{	
	var search = document.getElementById("search");
	var category = document.getElementById("category");
	
	var url = "/SiriusOrderClient/ProductSearchServlet?action=searchProducts&category=" + category.value + "&search=" +  search.value;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = JSON.parse(xhttp.responseText);
		$('#productContainer').empty();
		var productContainer = document.getElementById('productContainer');
		
		var user = document.getElementById("userType").value;

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
				};
			})();
			/*==============================*/
			var divPrice = document.createElement("div");
			divPrice.setAttribute("class", "price-tag");
			divPrice.innerHTML = '$ ' + response[key].Price;
			var numOfLikes = document.createElement("span");
			numOfLikes.id = "numOfLikes" + response[key].ID;
			numOfLikes.setAttribute("class", "num-of-likes")
			numOfLikes.innerHTML = response[key].Likers.length;
			numOfLikes.onmouseover = (function (){
				var id = response[key].ID;
				return function(){
					createModal(id);
				};
			})();
			
			numOfLikes.onmouseout = (function (){
				var id = response[key].ID;
				return function(){
					deleteModal('likesModal' + id);
				};
			})();
					
			var divLikesAndPrice = document.createElement("div");
			divLikesAndPrice.setAttribute("class", "likesAndPrice");
			divLikesAndPrice.appendChild(spanHeart);
			divLikesAndPrice.appendChild(numOfLikes);
			divLikesAndPrice.appendChild(divPrice);
			
			var likesModal = document.createElement("div");
			likesModal.setAttribute("class", 'likes-modal');
			likesModal.id = 'likesModal' + response[key].ID;
			for(nameKey in response[key].Likers){
				var innerModalDiv = document.createElement("div");
				innerModalDiv.innerHTML = response[key].Likers[nameKey];
				likesModal.appendChild(innerModalDiv);
			}
			divLikesAndPrice.appendChild(likesModal);
			
			var addToCartBtnContainer = document.createElement("div");
			if(user == 2){
				addToCartBtnContainer.setAttribute("class", "add-to-cart-btn-container");
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
				addToCartBtnContainer.appendChild(addToCartBtn);
			}

			var productCard = document.createElement("div");
			productCard.setAttribute("class", "productCard");
			productCard.appendChild(imageContainer);
			productCard.appendChild(nameDiv);
			productCard.appendChild(divLikesAndPrice);
			productCard.appendChild(addToCartBtnContainer);
			
			var productCardContainer = document.createElement("div");
			productCardContainer.setAttribute("class", "productContainerCard");
			productCardContainer.appendChild(productCard);
			
			productContainer.appendChild(productCardContainer);
		}
	};
	xhttp.send();
}

function verticalHandler(){
	
//	var container = document.getElementById("");
//	var contentHeight = container.offsetHeight;
	
}

function addToWishlist(productID){
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
		
		var likesModal = document.getElementById("likesModal" + productID);
		likesModal.innerHTML = '';
		for(key in response){
			var innerModalDiv = document.createElement("div");
			innerModalDiv.innerHTML = response[key];
			likesModal.appendChild(innerModalDiv);
		}
	};
	xhttp.send();
}

//function getAllLikers(productID){
//	var url = '/SiriusOrderClient/NavigationServlet?action=getLikers&id=' + productID;
//	var xhttp = new XMLHttpRequest();
//	xhttp.open("GET", url, true);
//	xhttp.onreadystatechange = function()
//	{
//		var response = JSON.parse(xhttp.responseText);
//		var likesModal = document.getElementById("likesModal" + productID);
//		likesModal.innerHTML = '';
//		for(item in response){
//			var innerModalDiv = document.createElement("div");
//			innerModalDiv.innerHTML = item.Name; 
//			likesModal.appendChild(innerModalDiv);
//		}
//	}
//}



function createModal(likesModalID){
	var size = document.getElementById("numOfLikes" + likesModalID).innerText;
	if(size > 0){
		document.getElementById('likesModal'+likesModalID).style.display = 'block';
	}
}

function deleteModal(likesModalID){
	document.getElementById(likesModalID).style.display = 'none';
}

