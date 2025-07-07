package com.example.practice.newentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")

public class addressentity {
	@Id
	@Column(name = "id")
	  private int id;
	
	@Column(name = "address")
	  private String address;
	
	@Column(name = "city")
	  private String city;
	
	@Column(name = "state")
	  private String state;
	
	@Column(name = "pincode")
	  private String pincode;
	
	@Column(name = "phone_number")
	  private String phone_number;
	
	@Column(name = "email")
	  private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
