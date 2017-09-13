from bs4 import BeautifulSoup
import requests
import urllib.request
import json

allProducts = []

def getProducts(url):
	req = urllib.request.urlopen(url)

	soup = BeautifulSoup(req.read(), "html.parser")



	itemList = soup.find_all("div", class_="stp--new-product-tile-container desktop")
	print(itemList)
	for item in itemList:
		try:
			print("\n")
			itemData = {}
			
			itemData['name'] = item.find("img")['title']
			print(itemData['name'])
			
			#itemData['Image'] = itemContents[1]['src']
			itemData['image'] = item.find("img")['src']
			print(itemData['image'])
			
			#itemData['Price'] = "$" + itemContents[1].find_all()[3].text
			itemData['price'] = item.find(property="price").text
			print(itemData['price'])
			
			itemData['details'] = item.find("ul", class_="product-bullets").text
			print(itemData['details'])
			
			itemData['type'] = "Ink and Toner"
			print("\n")
			allProducts.append(itemData)
		except AttributeError:
			continue
	
#HP url
linksReq = urllib.request.urlopen("https://www.staples.com/HP-ink-cartridges-toner-cartridges/cat_CG812")

linksSoup = BeautifulSoup(linksReq, "html.parser")

linksContainers = linksSoup.find_all(class_="category-search-result-container")
for linksContainer in linksContainers:
	print(linksContainer.find_all("a"))
	links = linksContainer.find_all("a")

	for link in links:
		print(link['href'])
		getProducts("https://www.staples.com" + link['href'])
		
	with open("hp.json", "w") as outfile:
			json.dump(allProducts, outfile, indent=4)
