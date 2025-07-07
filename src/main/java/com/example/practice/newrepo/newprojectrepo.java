package com.example.practice.newrepo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.practice.newentity.newprojectentity;

@Repository
public interface newprojectrepo extends JpaRepository<newprojectentity, String> {

	@Query(nativeQuery = true, value = "select COUNT(*) from sign_in_data where email=:emailId")
	Integer getEmailCount(String emailId);
	
	
	@Query(nativeQuery = true, value = "select * from Products")
	List<Map<String, Object>> getproducts();
	
	@Query(nativeQuery = true, value = "SELECT * FROM Products WHERE best_seller = 'true' ORDER BY rating DESC;")
	List<Map<String, Object>> getbestsellers();
	
	

}
