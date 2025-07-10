package com.example.practice.newentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name ="users")
public class login {
   @Id
   @Column(name ="email")
   private String email;
   
   @Column(name = "password")
	private String password;

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

	
}
