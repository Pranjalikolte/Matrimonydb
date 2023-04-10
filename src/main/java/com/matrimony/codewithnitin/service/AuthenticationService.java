package com.matrimony.codewithnitin.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.config.JwtService;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.Role;
import com.matrimony.codewithnitin.entity.Token;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.AuthenticationRequest;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.repository.TokenRepository;
import com.matrimony.codewithnitin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	ProfileRepository profileRepository;

	public User register(RegisterRequest request) {
//	  try {
		var user = User.builder().firstName(request.getFirstName()).middleName(request.getMiddleName())
				.lastName(request.getLastName()).emailId(request.getEmailId()).displayName(request.getDisplayName())
				.password(passwordEncoder.encode(request.getPassword())).mobileNo(request.getMobileNo()).role(Role.USER)
				.build();
		Optional<User> existingUserByEmail = repository.findByEmailId(request.getEmailId());
		if (existingUserByEmail.isPresent()) {
			throw new IllegalArgumentException("Email already exists");
		}
		Optional<User> existingUserByMobileNumber = repository.findByMobileNo(request.getMobileNo());
		if (existingUserByMobileNumber.isPresent()) {
			throw new IllegalArgumentException("Mobile number already exists");
		}

		return repository.save(user);
	}

	public Map<String, String> authenticate(AuthenticationRequest request) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
			var user = repository.findByEmailId(request.getEmailId()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);
			var userToken = user.getToken();
			if (userToken == null) {
				userToken = new Token();
				userToken.setUser(user);
			}
			userToken.setToken(jwtToken);
			tokenRepository.save(userToken);
			user.setToken(userToken);
			repository.save(user);

			Map<String, String> response = new HashMap<>();
			response.put("message", "Welcome user, your login is successful!");
			response.put("token", jwtToken);
			response.put("userId", String.valueOf(user.getUserId()));
			response.put("displayName", user.getDisplayName());
			response.put("role", user.getRole().name());

			// Get the profile ID from the profile object associated with the user
			Profile profile = user.getProfile();
			if (profile != null) {
				response.put("profileId", String.valueOf(profile.getProfileId()));
				response.put("profStatus", String.valueOf(profile.getProfstatus()));

			} else {
				response.put("profileId", "-1");
			}

			return response;
		} catch (AuthenticationException e) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Invalid email and password");
			response.put("status", "101");
			return response;
		}
	}

}

//hardcoded email and password of admin

//public Map<String, String> authenticate(AuthenticationRequest request) {
//	try {
//		if (request.getEmailId().equals("admin@example.com") && request.getPassword().equals("admin")) {
//			// Hardcoded credentials for admin user
//			// Generate token for admin user
//			var adminUser = new User();
//			adminUser.setUserId(-1L);
//			adminUser.setEmailId("admin@example.com");
//			adminUser.setRole(Role.ADMIN);
//
//			var jwtToken = jwtService.generateToken(adminUser);
//			Map<String, String> response = new HashMap<>();
//			response.put("message", "Welcome admin, your login is successful!");
//			response.put("token", jwtToken);
//			response.put("userId", String.valueOf(adminUser.getUserId()));
//			return response;
//		}
//
//		authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
//		var user = repository.findByEmailId(request.getEmailId()).orElseThrow();
//		var jwtToken = jwtService.generateToken(user);
//		var userToken = user.getToken();
//		if (userToken == null) {
//			userToken = new Token();
//			userToken.setUser(user);
//		}
//
//		userToken.setToken(jwtToken);
//		tokenRepository.save(userToken);
//		user.setToken(userToken);
//		repository.save(user);
//
//		Map<String, String> response = new HashMap<>();
//		response.put("message", "Welcome user, your login is successful!");
//		response.put("token", jwtToken);
//		response.put("userId", String.valueOf(user.getUserId()));
//		return response;
//	} catch (AuthenticationException e) {
//		Map<String, String> responce = new HashMap<>();
//		responce.put("message", "Invalid email and password");
//		responce.put("status", "101");
//		return responce;
//	}
//}
