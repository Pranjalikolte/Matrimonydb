package com.matrimony.codewithnitin.config.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.AuthenticationRequest;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.service.AuthenticationService;
import com.matrimony.codewithnitin.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService service;
	
	@Autowired
	private RegisterService registerService;

	
	//user Registration 
	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	//user authentication
	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, String>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	// update registered user
	@PutMapping("/register/{userId}")
	public ResponseEntity<RegisterRequest> updateUser(@Valid @RequestBody RegisterRequest registerRequest,
			@PathVariable("userId") Integer userId) {
		RegisterRequest updatedUser = this.registerService.updateUser(registerRequest, userId);
		return new ResponseEntity<RegisterRequest>(updatedUser, HttpStatus.OK);
	}

	// DELETE - DELETE PROFILE
	@DeleteMapping("/register/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") Integer userId) {
		this.registerService.deleteUser(userId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true, 100), HttpStatus.OK);
	}

	// get single user by id
	@GetMapping("/register/{userId}")
	public ResponseEntity<RegisterRequest> getSingleUSer(@PathVariable Integer userId) {
		RegisterRequest registerRequest = this.registerService.getSingleUser(userId);
		return new ResponseEntity<RegisterRequest>(registerRequest, HttpStatus.OK);
	}

}
