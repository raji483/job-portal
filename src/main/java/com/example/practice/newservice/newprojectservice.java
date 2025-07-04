package com.example.practice.newservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.practice.newpayload.loginpayload;
import com.example.practice.newpayload.newprojectpayload;

@Service
public interface newprojectservice {
	
	ResponseEntity<?> newdatapost(newprojectpayload reqBody);
	ResponseEntity<?> login(loginpayload reqBody);
		

}
