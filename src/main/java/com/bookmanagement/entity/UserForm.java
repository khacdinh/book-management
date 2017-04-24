package com.bookmanagement.entity;

import org.springframework.stereotype.Component;

@Component
public class UserForm {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
