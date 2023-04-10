package com.matrimony.codewithnitin.config.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.UserRepository;
import com.matrimony.codewithnitin.service.ProfileService;
import com.matrimony.codewithnitin.service.RegisterService;

@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@RestController
public class ViewListController {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	ProfileService profileService ;

	@Autowired 
	UserRepository userRepository ;
	
	@RequestMapping(value = "/page/{pageNumber}/{pageSize}",method = RequestMethod.GET)
	public Page<User> userPagination(@PathVariable Integer pageNumber , @PathVariable Integer pageSize ){
		return profileService.getUserPagination(pageNumber,pageSize);
	}
//	
//	@GetMapping("/registeredusers")
//	public List<Map<String, Object>> getAllUsers() {
//	   return userRepository.findAllUsers();
//	}
	
	
	@GetMapping("/registeredusers")
	public List<Map<String, Object>> getAllUsers(Authentication authentication) {
	    boolean isAdmin = authentication.getAuthorities().stream()
	                      .anyMatch(a -> a.getAuthority().equals("ROLE_admin"));
	    if (!isAdmin) {
	        throw new AccessDeniedException("Access is denied");
	    }
	    return userRepository.findAllUsers();
	}


	}
