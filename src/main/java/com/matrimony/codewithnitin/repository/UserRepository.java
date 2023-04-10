package com.matrimony.codewithnitin.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matrimony.codewithnitin.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmailId(String emailId);

	Optional<User> findByMobileNo(String mobileNo);
	

	public User findByUserId(Integer userId);

	// return total number of user registered 

	@Query("SELECT COUNT(u) FROM User u")
	Integer countTotalRegistrations();
	
	// to return list of registered users
	 @Query("SELECT u.userId as userId, " + " u.firstName as firstName," + " u.middleName as middleName,"+" u.lastName as lastName,"+" u.mobileNo as mobileNo"+" FROM User u")
	List<Map<String, Object>> findAllUsers();

	//	 Optional<User> findByEmailIdAndRoles(String username, String string);
}
