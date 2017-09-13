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
req = urllib.request.urlopen("https://www.staples.com/Paper-Stationery/cat_SC1676")

#all breakroom stuff url
#req = urllib.request.urlopen("https://www.staples.com/Coffee-Water-Snacks/cat_SC1684")

soup = BeautifulSoup(req.read(), "html.parser")
allProducts = []

itemList = soup.find_all(itemtype="http://schema.org/Product")
print(itemList)

for item in itemList:
	print("item")
	print("\n")
	itemData = {}
	
	itemData['name'] = item.find(itemprop="name").text
	print(itemData['name'])
	
	itemData['image'] = item.find(itemprop="image")['src']
	print(itemData['image'])
	
	itemData['price'] = item.find(itemprop="priceCurrency").text + item.find(itemprop="price").text
	print(itemData['price'])
	
	itemData['type'] = "Breakroom"
	#itemData['type'] = "Office Supplies"
	print("\n")
	allProducts.append(itemData)
	
with open("paper.json", "w") as outfile:
    json.dump(allProducts, outfile, indent=4)
