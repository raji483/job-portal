package com.example.practice.newcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.newpayload.loginpayload;
import com.example.practice.newpayload.newprojectpayload;
import com.example.practice.newrepo.newprojectrepo;
import com.example.practice.newservice.newprojectservice;
import com.example.practice.utils.CommonQueryAPIUtils;

@RestController
@CrossOrigin

public class newprojectcontroller {

	@Autowired
	newprojectservice service;
	
	@Autowired
	newprojectrepo repo;

	@GetMapping("/raji")
	String raji() {
		return "hi user ";

	}

	@PostMapping("/user-signin")
	public ResponseEntity<?> newdatapost(@RequestBody newprojectpayload reqBody) {

		return service.newdatapost(reqBody);
	}

	@PostMapping("/user-login")
	public ResponseEntity<?> login(@RequestBody loginpayload reqBody) {

		return service.login(reqBody);
	}
	
	
	
	
	
	@GetMapping("/products-data")
	Map<String, Object> getproducts() {
		System.err.println("hiiii");
		
		return CommonQueryAPIUtils.apiService("data", repo.getproducts());

	}

}
