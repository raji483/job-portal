package com.example.practice.newrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.practice.newentity.login;



@Repository
public interface loginrepo extends JpaRepository<login, String> {
	
	@Query(nativeQuery = true, value = "select password from users where email=:email;")
	String getpassword(String email);
	
	@Query(nativeQuery = true, value = "select count(*) login_count from users where email=:email and password=:password")
	Integer getpassword(String email,String password);
	
	
	
	
	
}
