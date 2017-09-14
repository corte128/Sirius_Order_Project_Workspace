package com.sirius.product.service.test;

import com.sirius.product.service.main.ProductImplementation;

public class DBTester {
	public static void main(String[] args) {
		ProductImplementation pObj = new ProductImplementation();

		pObj.getProductByID(1);
	}
}
