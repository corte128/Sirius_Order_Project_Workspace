package com.sirius.service.generate;

import javax.jws.WebService;

import com.sirius.service.generate.database.GenerateDAO;

@WebService(endpointInterface="com.sirius.service.generate.GenerateInterface",
portName="generate", targetNamespace ="http://generate.service.sirius.com/cart/wsdl",
serviceName="GenerateCartService")
public class GenerateImplementation implements GenerateInterface {

	@Override
	public boolean generateCart() {
		return GenerateDAO.generateCart();
	}

}
