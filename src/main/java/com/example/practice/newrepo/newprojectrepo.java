package com.example.practice.newrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.practice.newentity.newprojectentity;

@Repository
public interface newprojectrepo extends JpaRepository<newprojectentity, String> {

	@Query(nativeQuery = true, value = "select COUNT(*) from sign_in_data where email=:emailId")
	Integer getEmailCount(String emailId);

}
