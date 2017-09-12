package com.sirius.loginws.jaxws;

import javax.jws.WebService;

import com.sirius.loginws.db.LoginServiceDAO;

//TODO: change wsdl location
@WebService(endpointInterface = "com.sirius.loginws.jaxws.Login", portName = "login", targetNamespace = "http://loginws.sirius.com/login/wsdl", serviceName = "JaxwsLogin")
public class JaxwsLoginImpl implements Login{

	@Override
	public int getEmployeeByCredentials(String email, String password) {
		LoginServiceDAO dao = new LoginServiceDAO();
		return dao.getEmployeeByCredentials(email, password);
	}
	
}
