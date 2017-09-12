package com.sirius.service.bean;

import com.sirius.loginws.enums.EmployeeStatus;

public class EmployeeBean {
	int id;
	String name;
	String password;
	String email;
	String role;
	int location;
	int numberOfLikes;
	byte[] picture;
	EmployeeStatus isValid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getNumberOfLikes() {
		return numberOfLikes;
	}
	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public EmployeeStatus isValid() {
		return isValid;
	}
	public void setValid(EmployeeStatus isValid) {
		this.isValid = isValid;
	}
	
}
