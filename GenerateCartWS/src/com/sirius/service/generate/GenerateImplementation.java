package com.sirius.service.generate;

import javax.jws.WebService;

import com.sirius.service.generate.database.GenerateDAO;

@WebService(endpointInterface="com.sirius.service.generate.GenerateInterface",
portName="generate", targetNamespace ="http://generate.service.sirius.com/cart/wsdl",
serviceName="GenerateCartService")
public class GenerateImplementation implements GenerateInterface {

	@Override
	public boolean generateCart() {
		//GenerateCart gcObj = new GenerateCart(10,35,30);
		//gcObj.start();
		return GenerateDAO.generateCart();
	}

}
