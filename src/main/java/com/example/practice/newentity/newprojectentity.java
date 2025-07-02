package com.example.practice.newentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sign_in_data")
public class newprojectentity {
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "role")
	private Integer role;

	@Column(name = "name")
	private String name;

	

	@Column(name = "password")
	private String password;



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Integer getRole() {
		return role;
	}



	public void setRole(Integer role) {
		this.role = role;
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
