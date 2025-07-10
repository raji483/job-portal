package com.example.practice.newrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.newentity.addressentity;

@Repository
public interface addressrepo  extends JpaRepository<addressentity, Integer> {
	
	

}
