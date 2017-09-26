package com.sirius.service.generate;

import java.math.BigDecimal;

import javax.jws.WebService;

import com.sirius.service.generate.database.GenerateDAO;

@WebService(endpointInterface="com.sirius.service.generate.GenerateInterface",
portName="generate", targetNamespace ="http://generate.service.sirius.com/cart/wsdl",
serviceName="GenerateCartService")
public class GenerateImplementation implements GenerateInterface {

	@Override
	public BigDecimal generateBudget(int locationId) {
		return GenerateDAO.generateBudget(locationId);
	}

	@Override
	public boolean generateCart(int locationId) {
		// TODO Auto-generated method stub
		return false;
	}

}
