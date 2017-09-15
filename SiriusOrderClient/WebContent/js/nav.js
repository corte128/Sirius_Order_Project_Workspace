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

function animateSideBarClose() 
{
    var elem = document.getElementById("navIconLinksSidebar"); 
    var pos = 70;
    
    while(pos >= 100)
    {
        pos += 1;
        elem.style.left = pos + '%';
    }
    console.log(0);
}

function openSidebar()
{
	document.getElementById("responsiveNavHamburgerButton").onclick = function(){closeSidebar()};
	document.getElementById("navIconLinksSidebar").style.display = "block";
	//document.getElementById("responsiveNavHamburgerButton").style.display = "none";
}
function closeSidebar()
{
	document.getElementById("responsiveNavHamburgerButton").onclick = function(){openSidebar()};
	animateSideBarClose();
	document.getElementById("navIconLinksSidebar").style.display = "none";
	//document.getElementById("responsiveNavHamburgerButton").style.display = "block";
}

