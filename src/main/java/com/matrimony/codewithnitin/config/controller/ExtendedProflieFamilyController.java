package com.matrimony.codewithnitin.config.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.ExtendedProfileFamily;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ExtendedProfileFamilyResponce;
import com.matrimony.codewithnitin.repository.ExtendedProfileFamilyRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ExtendedProflieFamilyController {

	@Autowired
	ExtendedProfileFamilyRepository extendedProfileFamilyRepository;

	@Autowired
	ProfileRepository profileRepository;

	// create users extendedfamilyprofile
	@PostMapping("/createextendedfamily")
	public ResponseEntity<ExtendedProfileFamilyResponce> createProfile(
			@RequestBody ExtendedProfileFamily extendedProfileFamily) {
		try {
			Integer profileId = extendedProfileFamily.getProfile().getProfileId();
			Profile profile = profileRepository.findById(profileId)
					.orElseThrow(() -> new IllegalArgumentException(" profile  not found for the given profileId"));
			extendedProfileFamily.setProfile(profile);
			ExtendedProfileFamily savedProfile = extendedProfileFamilyRepository.save(extendedProfileFamily);
			ExtendedProfileFamilyResponce extendedProfileFamilyResponce = new ExtendedProfileFamilyResponce(
					savedProfile.getExtendedId(), "Extended Profile Family created successfully");
			return ResponseEntity.ok(extendedProfileFamilyResponce);

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

	// update users extended family profile
	@PutMapping("/updateextendedfamily/{profileId}")
	public ResponseEntity<Map<String, Object>> updateExtendedFamily(@PathVariable Integer profileId,
			@RequestBody ExtendedProfileFamily updateExtendedFamily) {
		Optional<Profile> optionalProfile = profileRepository.findById(profileId);
		if (optionalProfile.isPresent()) {
			Profile profile = optionalProfile.get();
			ExtendedProfileFamily extendedProfileFamily = profile.getExtendedprofileFamily();
			if (extendedProfileFamily == null) {
				return ResponseEntity.notFound().build();
			}
			// use reflection to update the fields of the profile entity
			Field[] fields = extendedProfileFamily.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(updateExtendedFamily);
					if (value != null) {
						field.set(extendedProfileFamily, value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			ExtendedProfileFamily savedextendedProfileFamily = extendedProfileFamilyRepository
					.save(extendedProfileFamily);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Profile Family updated successfully!");
			response.put("extendedId", savedextendedProfileFamily.getExtendedId());
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
