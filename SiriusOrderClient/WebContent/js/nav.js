var adminModal = null;
var adminIcon = null;
window.onload = function(){
	adminModal = document.getElementById("navAdminSubModal");
	reportsModal = document.getElementById("navReportsSubModal");
}
function showAdminModal()
{
	adminModal.style.display = 'block';
}
function closeAdminModal()
{
	adminModal.style.display = 'none';
}

function showReportsModal()
{
	reportsModal.style.display = 'block';
}
function closeReportsModal()
{
	reportsModal.style.display = 'none';
}

function openSidebar()
{
	document.getElementById("navIconLinksSidebar").style.display = "block";
	document.getElementById("responsiveNavHamburgerButton").style.display = "none";
}
function closeSidebar()
{
	document.getElementById("navIconLinksSidebar").style.display = "none";
	document.getElementById("responsiveNavHamburgerButton").style.display = "block";
}