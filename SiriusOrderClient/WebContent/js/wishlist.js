$( document ).ready(function() {
	getProducts();
});

function addToCart(productID)
{
	$('#addConfirmed' + productID).fadeIn('fast').delay(1000).fadeOut('slow')
	var xhttp = new XMLHttpRequest();
	var quantityElement = document.getElementById("quantityToAdd");
    var quantity = 1;
   
    if (quantityElement != null)
    {
        quantity = quantityElement.value;
        if (quantity < 0 ){
        	var errorMessageDiv = document.getElementById("errorMessage");
        	errorMessageDiv.style.display= "block";
        }else{
        	var errorMessageDiv = document.getElementById("errorMessage");
        	errorMessageDiv.style.display= "none";
        }
        
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
	var successElement = document.getElementById("successMessage");
	if (successElement != null && quantity > 0){
		$('#successMessage').fadeIn('fast').delay(3000).fadeOut('slow')
	}
}

function getProducts()
{	
	console.log("in products");
	var url = "/SiriusOrderClient/WishlistServlet";
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = JSON.parse(xhttp.responseText);
		
		$('#productContainer').empty();
		var productContainer = document.getElementById('productContainer');
		
		var profileImage = document.getElementById('profileImage').value;
		var profileName = document.getElementById('profileName').value;
		var wishlistWelcome = document.getElementById('wishlistWelcome').value;
		var wishlistWishlistLabel = document.getElementById('wishlistWishlistLabel').value;
		var wishlistYouHave = document.getElementById('wishlistYouHave').value;
		var wishlistInWishlist = document.getElementById('wishlistInWishlist').value;
		var wishlistLocationLabel = document.getElementById('wishlistLocationLabel').value;
		var wishlistLocation = document.getElementById('wishlistLocation').value;
		
		if(profileImage != ""){
			profileImage = 'data:image/jpeg;base64,' + profileImage;
		}else{
			profileImage = '/SiriusOrderClient/assets/default.png';
		}
		
		productContainer.innerHTML = ' <div class="wishlist-card-container" id="profile"> \
			<div class="productCard"> \
				<div class="wishlist-profile-image-welcome"> \
					<div class="wishlist-profile-image"> \
						<img class="employee-profile-photo" alt=""' + '\
							src="'+profileImage+'" /> \
					</div> \
					<div class="wishlist-profile-welcome"> \
						'+ wishlistWelcome +' \
						'+ profileName +' \
					</div> \
				</div> \
				<div class="wishlist-profile-title wishlist-profile-title-wishlist"> \
					'+ wishlistWishlistLabel +' \
				</div> \
				<div class="wishlist-profile-text wishlist-profile-text-wishlist"> \
					'+ wishlistYouHave +' \
					<span id="productAmount"> \
					'+response.length+'\
					</span> \
					'+ wishlistInWishlist +' \
				</div> \
				<div class="wishlist-profile-title wishlist-profile-title-location"> \
					'+ wishlistLocationLabel +' \
				</div> \
				<div class="wishlist-profile-text wishlist-profile-text-location"> \
					'+ wishlistLocation +' \
				</div> \
			</div>\
		</div>';
		
		var user = document.getElementById("userType").value;
		document.getElementById('productAmount').innerHTML = response.length;
		
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
			numOfLikes.setAttribute("class", "num-of-likes");
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
			
			var productAddedDiv = document.createElement("span");
			productAddedDiv.setAttribute("class", "confirm-popup");
			productAddedDiv.id = 'addConfirmed' + response[key].ID;
			productAddedDiv.innerHTML = 'Product Added!';
			
			productCard.appendChild(productAddedDiv);
			
			var productCardContainer = document.createElement("div");
			productCardContainer.setAttribute("class", "wishlist-card-container");
			productCardContainer.appendChild(productCard);
			
			
			productContainer.appendChild(productCardContainer);
			resizeDisplay();
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

function createModal(likesModalID){
	var size = document.getElementById("numOfLikes" + likesModalID).innerText;
	if(size > 0){
		document.getElementById('likesModal'+likesModalID).style.display = 'block';
	}
}

function deleteModal(likesModalID){
	document.getElementById(likesModalID).style.display = 'none';
}

function resizeDisplay(){
	var mqls = [
	            window.matchMedia("(min-width: 768px)"),
	            window.matchMedia("(max-width: 1024px)")
	            ];
	
    if(mqls[0].matches && mqls[1].matches){
    	document.getElementById("profile").style.display = "none";
    	document.getElementById("profileCard").style.display = "flex";
    }else{
    	document.getElementById("profile").style.display = "block";
    	document.getElementById("profileCard").style.display = "none";
    }
}

