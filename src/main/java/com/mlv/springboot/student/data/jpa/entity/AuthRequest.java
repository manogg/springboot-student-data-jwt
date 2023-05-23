package com.mlv.springboot.student.data.jpa.entity;

public class AuthRequest {

	private String name;
	private String password;

	public AuthRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public AuthRequest() {
		super();
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

}
