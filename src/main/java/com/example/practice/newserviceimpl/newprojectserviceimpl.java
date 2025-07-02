package com.example.practice.newserviceimpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.practice.newentity.newprojectentity;
import com.example.practice.newpayload.newprojectpayload;
import com.example.practice.newrepo.newprojectrepo;
import com.example.practice.newservice.newprojectservice;
import com.example.practice.utils.CommonQueryAPIUtils;

@Service
public class newprojectserviceimpl implements newprojectservice {
	@Autowired
	newprojectrepo repo;

	@Override
	public ResponseEntity<?> newdatapost(newprojectpayload reqBody) {
		try {
			String errorMsg = CommonQueryAPIUtils.validationService(
					Arrays.asList(reqBody.getEmail(), reqBody.getPassword(), reqBody.getName(), reqBody.getRole()),
					Arrays.asList("email", "password", "name","role" ));
			if (errorMsg.length() > 0) {
				return CommonQueryAPIUtils.fStaticResponse(errorMsg);
			} else {
				Integer count = repo.getEmailCount(reqBody.getEmail());
				if (count > 0) {
					return CommonQueryAPIUtils.manualResponse("02","Already registered");
				} else {
					Integer rl = reqBody.getRole();
					String name = reqBody.getName();
					String id = reqBody.getEmail();
					String pass = reqBody.getPassword();

					newprojectentity entity = new newprojectentity();
					entity.setRole(rl);
					entity.setName(name);
					entity.setEmail(id);
					entity.setPassword(pass);
					repo.save(entity);
					return CommonQueryAPIUtils.sResponse("Successfully Registered");
				}

			}
		} catch (Exception e) {
			return CommonQueryAPIUtils.fStaticResponse("Internal Server Issue");
		}

	}

//	@Override
//	public ResponseEntity<?> loginUser(UserRequest reqBody) {
//		try {
//			String errorMsg = CommonQueryAPIUtils.validationService(
//					Arrays.asList(reqBody.getEmail(), reqBody.getPassword()), Arrays.asList("email", "password"));
//			if (errorMsg.length() > 0) {
//				return CommonQueryAPIUtils.fStaticResponse(errorMsg);
//			} else {
//				String email = reqBody.getEmail();
//				String password = reqBody.getPassword();
//
//				List<Map<String, Object>> getUserDetails = userRepo.getUserDetails(email, password);
//				if (getUserDetails.size() > 0) {
//					return CommonQueryAPIUtils.sResponse(" Login successful");
//
//				} else {
//					System.err.println("user fail");
//					return CommonQueryAPIUtils.manualResponse("02", "Invalid credentials!");
//				}
//
//			}
//		} catch (Exception e) {
//
//			return CommonQueryAPIUtils.manualResponse("02", "Invalid credentials!");
//		}
//	}

}
