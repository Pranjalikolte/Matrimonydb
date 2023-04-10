package com.matrimony.codewithnitin.config.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.PartnerPreferance;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.PartnerPreferenceResponce;
import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PartnerPreferenceController {

	@Autowired
	PartnerPreferenceRepository partnerPreferenceRepository;

	@Autowired
	ProfileRepository profileRepository;

//	@PostMapping("/createpartnerPreferance")s
//	public PartnerPreferance create(@RequestBody PartnerPreferance partnerPreferance) {
//		return partnerPreferenceRepository.save(partnerPreferance);
//	}

	//create users partner preferance 
	@PostMapping("/createpartnerpreferance")
	public ResponseEntity<PartnerPreferenceResponce> createProfile(@RequestBody PartnerPreferance partnerPreferance) {
		try {
			Integer profileId = partnerPreferance.getProfile().getProfileId();
			Profile profile = profileRepository.findById(profileId).orElseThrow(
					() -> new IllegalArgumentException("profile details not found for the given profileId"));
			partnerPreferance.setProfile(profile);
			PartnerPreferance savedProfile = partnerPreferenceRepository.save(partnerPreferance);
			PartnerPreferenceResponce partnerPreferenceResponce = new PartnerPreferenceResponce(
					savedProfile.getPreferenceId(), "Profile Preference created successfully");
			return ResponseEntity.ok(partnerPreferenceResponce);

		} catch (DataIntegrityViolationException ex) {
			String message = "The data you're trying to insert violates a unique constraint. Please check your input and try again.";
			ApiResponce response = new ApiResponce(message, false, HttpStatus.CONFLICT.value());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

		} catch (Exception ex) {
			String message = "An error occurred while processing your request.";
			ApiResponce response = new ApiResponce(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	//find matching profiles based on preferance  
	@GetMapping("/matchprofiles/{profileId}")
    public List<Map<String, Object>> getMatches(@PathVariable Long profileId) {
        return partnerPreferenceRepository.findByPartnerPreferanceAndProfileId(profileId);
    }
	
	//update users partner preferance 
	@PutMapping("/updatepartnerPreferance/{profileId}")	
		public ResponseEntity<Map<String, Object>> updatePartnerPreferance(@PathVariable Integer profileId , @RequestBody PartnerPreferance updatePartnerPreferance ){
			Optional<Profile> optionalProfile = profileRepository.findById(profileId);
			if(optionalProfile.isPresent()) {
				Profile profile = optionalProfile.get();
				PartnerPreferance partnerPreferance=profile.getPartnerPreferance();
				if(partnerPreferance == null) {
					return ResponseEntity.notFound().build();
				}
				
				Field[] fields = updatePartnerPreferance.getClass().getDeclaredFields();
				for (Field field: fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(updatePartnerPreferance);
						if(value!= null) {
							field.set(partnerPreferance, value);
						}
					}catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				PartnerPreferance savedPartnerPreferance = partnerPreferenceRepository.save(partnerPreferance);
				Map<String,Object> response = new HashMap<>();
				response.put("message", " PartnerPreferance updated successfully!");
				response.put("preferenceId", savedPartnerPreferance.getPreferenceId());
				return ResponseEntity.ok().body(response);
			}else {
				return ResponseEntity.notFound().build();
			}
		}
	}

	



