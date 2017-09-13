import json

with open('ink.bat', 'w+') as batch_file:
	print('batch file created')
	#batch_file.write("mysql -u wjc9578 -p Test_Database\n")
	batch_file.write("use Test_Database\n")

#check for duplicates
existingProducts = {}
with open('hp.json') as data_file:
	items = json.load(data_file)
	with open('ink.bat', 'a') as batch_file:
		for item in items:
			try:
				existingProducts[item['name']]
			except KeyError:
				batch_file.write('INSERT INTO product_tbl(product_name, product_type_id_fk, product_price, product_details, ' \
				+ 'product_image, created_by, created_date) VALUES("' + item['name'].replace('"', '\\"') + '", ' + '3' + ', ' \
				+ item['price'][1:].replace(',', '') + ', "' + item['details'].replace("\n", "<br />").replace('"', '\\"') \
				+ '", "' + item['image'] + '", 1, (SELECT CURDATE()));\n')
				existingProducts[item['name']] = 'true'
