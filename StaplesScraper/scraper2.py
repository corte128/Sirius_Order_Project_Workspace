from bs4 import BeautifulSoup
import requests
import urllib.request
import json

#coffee url
#req = urllib.request.urlopen("https://www.staples.com/Coffee-Water-Snacks/cat_CG1038")

#drinks url
#req = urllib.request.urlopen("https://www.staples.com/Beverages/cat_CG1251")

#snacks url
#req = urllib.request.urlopen("https://www.staples.com/Snacks-Meals/cat_CG3609")

#writing supplies url
#req = urllib.request.urlopen("https://www.staples.com/Writing-Supplies/cat_CG11")

#tea url
#req = urllib.request.urlopen("https://www.staples.com/Tea-Hot-Chocolate/cat_CG3654")

#paper url
#req = urllib.request.urlopen("https://www.staples.com/Paper-Stationery/cat_SC1676")

#all breakroom stuff url
#req = urllib.request.urlopen("https://www.staples.com/Coffee-Water-Snacks/cat_SC1684")

#office basics url
req = urllib.request.urlopen("https://www.staples.com/Office-Basics/cat_CG1036")

#towels url
#req = urllib.request.urlopen("https://www.staples.com/Paper-Towels-Tissues-Dispensers/cat_CG3930")

#calendar url
req = urllib.request.urlopen("https://www.staples.com/Calendars-Planners/cat_CG12")
allProducts = []

def getProductData(url, price):
	productReq = urllib.request.urlopen(url)
	productSoup = BeautifulSoup(productReq, "html.parser")
	itemData = {}
			
	itemData['name'] = productSoup.find("div", class_="stp--grid").find("h1").text
	itemData['image'] = productSoup.find("img", class_="stp--sku-image")['src']
	itemData['price'] = price
	itemData['details'] = productSoup.find("ul", class_="stp--bulleted-list").text
	print(itemData['name'])
	print(itemData['image'])
	print(itemData['price'])
	print(itemData['details'])
	itemData['type'] = "Office Supplies"
	#itemData['type'] = "Breakroom"
	allProducts.append(itemData)
	
soup = BeautifulSoup(req.read(), "html.parser")
#print(soup.prettify())
itemList = soup.find_all(itemtype="http://schema.org/Product")
#print(itemList)

for item in itemList:
	
	price = item.find(itemprop="priceCurrency").text + item.find(itemprop="price").text
	getProductData("https://www.staples.com" + item.find("a")['href'], price)
	
	
with open("calendar.json", "w+") as outfile:
    json.dump(allProducts, outfile, indent=4)