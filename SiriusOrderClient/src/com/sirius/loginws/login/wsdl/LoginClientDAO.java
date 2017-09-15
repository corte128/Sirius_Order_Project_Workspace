package com.sirius.loginws.login.wsdl;

public class LoginClientDAO {
	
	public int getEmployeeByCredentials(String email, String password){
		LoginProxy client = new LoginProxy();
		return client.getEmployeeByCredentials(email, password);
	}
	
}
