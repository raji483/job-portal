package com.example.practice.newcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.newpayload.newprojectpayload;
import com.example.practice.newservice.newprojectservice;

@RestController
@CrossOrigin


public class newprojectcontroller {

	@Autowired
	newprojectservice service;
	
	@PostMapping("/user-signin")
	public ResponseEntity<?> newdatapost(@RequestBody newprojectpayload reqBody) {

		return service.newdatapost(reqBody);
	}
	
}
