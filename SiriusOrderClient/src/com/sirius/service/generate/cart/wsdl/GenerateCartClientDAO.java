package com.sirius.service.generate.cart.wsdl;

public class GenerateCartClientDAO {
	
	public boolean GenerateCart(){
		GenerateProxy client = new GenerateProxy();
		return client.generateCart();
	}
	
}
