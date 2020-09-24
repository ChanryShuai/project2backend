package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="user_login")
public class LoginDTO {
	
	@Id
	private String username;
	private String password; 
	
	public LoginDTO () {}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password; 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	 
	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}

	
	
	

}
