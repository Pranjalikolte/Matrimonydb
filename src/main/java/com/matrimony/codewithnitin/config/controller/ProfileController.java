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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ProfileResponce;
import com.matrimony.codewithnitin.repository.ExtendedProfileFamilyRepository;
import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;
import com.matrimony.codewithnitin.repository.ProfileDetailsRepository;
import com.matrimony.codewithnitin.repository.ProfileFamilyRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.repository.UserRepository;
import com.matrimony.codewithnitin.service.ProfileService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProfileController {

	@Autowired
	ProfileService profileservice;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileDetailsRepository profileDetailsRepository;
	
	@Autowired
	ProfileFamilyRepository profileFamilyRepository;
	
	@Autowired
	ExtendedProfileFamilyRepository extendedProfileFamilyRepository;
	
	@Autowired
	PartnerPreferenceRepository partnerPreferenceRepository;

	//create user profile
	@PostMapping("/createprofile")
	public ResponseEntity<ProfileResponce> createProfile(@RequestBody Profile profile) {
		try {
			Integer userId = profile.getUser().getUserId();
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new IllegalArgumentException("User not found for the given userId"));
			profile.setUser(user);
			Profile savedProfile = profileRepository.save(profile);
			ProfileResponce profileResponce = new ProfileResponce(savedProfile.getProfileId(),
					"Profile created successfully");
			return ResponseEntity.ok(profileResponce);

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

	// list of all profiles with service
//	@GetMapping("/allprofile")
//	public ResponseEntity<List<Profile>> getAllProfiles() {
// 
//		return ResponseEntity.ok(profiles);
//	}

	// find list of profile
	

	// get single profile by profileId
	@GetMapping("/profile/{profileId}")
	public ResponseEntity<Profile> getSingleProfile(@PathVariable Integer profileId) {
		Profile profiles = this.profileservice.getSingleProfile(profileId);
		return new ResponseEntity<Profile>(profiles, HttpStatus.OK);
	}

//	@GetMapping("/profile/{profileId}")
//	public ResponseEntity<ProfileResponce> getProfileById(@PathVariable("profileId") Integer profileId) {
//	    try {
//	        Profile profile = profileRepository.findById(profileId)
//	                .orElseThrow(() -> new IllegalArgumentException("Profile not found for the given profileId"));
//	        ProfileResponce profileResponce = new ProfileResponce(profile.getProfileId(), "Profile retrieved successfully");
//	        return ResponseEntity.ok(profileResponce);
//	    } catch (Exception ex) {
//	        String message = "An error occurred while processing your request.";
//	        ApiResponce response = new ApiResponce(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value());
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	    }
//	}

	//get list of profiles
	 @GetMapping("/profiles")
	    public List<Profile> getAllProfiles() {
	        return profileRepository.findAll();
	    }

	// @GetMapping("/profile/{profileId}")
	public ResponseEntity<ProfileResponce> getProfileById(@PathVariable("profileId") Integer profileId) {
		try {
			Profile profile = profileRepository.findById(profileId)
					.orElseThrow(() -> new IllegalArgumentException("Profile not found for the given profileId"));
			ProfileResponce profileResponce = new ProfileResponce();
			return ResponseEntity.ok(profileResponce);
		} catch (Exception ex) {
			String message = "An error occurred while processing your request.";
			ApiResponce response = new ApiResponce(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// find byId
//	@GetMapping("/profile/{profileId}")
//	public ResponseEntity<Profile> getUserById(@PathVariable Integer profileId) {
//		Profile profile = profileRepository.findById(profileId)
//				.orElseThrow(() -> new RuntimeException("Profile not found"));
//		return ResponseEntity.ok(profile);
//	}
//

	
	// delete userprofile
	@DeleteMapping("/deleteprofile/{profileId}")
	public String delete(@PathVariable Integer profileId) {
		Profile profile = profileRepository.findById(profileId)
				.orElseThrow(() -> new RuntimeException("Profile notfound"));
		profileRepository.delete(profile);
		return "User succesfully Deleted...";
	}

//	@DeleteMapping("/profile/{profileId}")
//	public ResponseEntity<ApiResponce> deleteProfile(@PathVariable("profileId") Integer profileId) {
//		this.profileservice.deleteProfile(profileId);
//		return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true, 100), HttpStatus.OK);
//	}

	
	//update users profile with profile id
	@PutMapping("/updateprofile/{profileId}")
	public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable Integer profileId,
			@RequestBody Profile updatedProfile) {
		Optional<Profile> optionalProfile = profileRepository.findById(profileId);
		if (optionalProfile.isPresent()) {
			Profile profile = optionalProfile.get();
			// use reflection to update the fields of the profile entity
			Field[] fields = updatedProfile.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(updatedProfile);
					if (value != null) {
						field.set(profile, value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			Profile savedProfile = profileRepository.save(profile);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Profile updated successfully!");
			response.put("profileId", savedProfile.getProfileId());
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	
	//without if condition
	
//	@GetMapping("/check-profiles/{userId}")
//	public Map<String, Boolean> checkProfiles(@PathVariable Long userId) {
//	List<Object[]> result = profileRepository.findprofiles(userId);
//	Map<String, Boolean> response = new HashMap<>();
//	response.put("profile", false);
//	response.put("profileDetails", false);
//	response.put("profileFamily", false);
//	response.put("extendedProfileFamily", false);
//	response.put("partnerPreference", false);
//	for (Object[] row : result) {
//	    response.put("profile", (Boolean) row[1]);
//	    response.put("profileDetails", (Boolean) row[2]);
//	    response.put("profileFamily", (Boolean) row[3]);
//	    response.put("extendedProfileFamily", (Boolean) row[4]);
//	    response.put("partnerPreference", (Boolean) row[5]);
//	}
//
//	return response;
//	}

	
	//with if condition check if user has completely created profile
	@GetMapping("/profilestatus/{userId}")
	public Map<String, Boolean> checkProfiles(@PathVariable Long userId) {
	List<Object[]> result = profileRepository.findprofiles(userId);
	Map<String, Boolean> response = new HashMap<>();
	response.put("profile", false);
	response.put("profileDetails", false);
	response.put("profileFamily", false);
	response.put("extendedProfileFamily", false);
	response.put("partnerPreference", false);
	for (Object[] row : result) {
	    response.put("profile", (Boolean) row[1]);

	    if ((Boolean) row[1]) {
	        response.put("profileDetails", (Boolean) row[2]);
	        response.put("profileFamily", (Boolean) row[3]);
	        response.put("extendedProfileFamily", (Boolean) row[4]);
	        response.put("partnerPreference", (Boolean) row[5]);
	    }
	    
	    break;  
	}
	return response;
	}
	
	
	
	@GetMapping("/registrations/count")
	    public Integer countTotalRegistrations() {
	        return userRepository.countTotalRegistrations();
	    }
}
