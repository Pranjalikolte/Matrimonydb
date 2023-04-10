package com.matrimony.codewithnitin.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.service.ProfileService;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminPanelController {

	@Autowired
	ProfileService profileservice;
	
	// find list of profile
		@GetMapping("/profile")
		public List<Profile> getData() {

			return profileservice.getData();
		}
		
		@GetMapping("/user")
		public ResponseEntity<String>normaluser(){
			return ResponseEntity.ok("i am user");
		}
		
		@GetMapping("/normal")
				public ResponseEntity<String>user(){
			return ResponseEntity.ok("i am not user");
		}
		
}
