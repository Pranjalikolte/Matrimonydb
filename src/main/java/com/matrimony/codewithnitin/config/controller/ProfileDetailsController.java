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
import com.matrimony.codewithnitin.entity.ProfileDetails;
import com.matrimony.codewithnitin.payload.ApiResponce;
import com.matrimony.codewithnitin.payload.ProfileDetailsResponce;
import com.matrimony.codewithnitin.repository.ProfileDetailsRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.service.ProfileDetailsService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProfileDetailsController {

	@Autowired
	ProfileDetailsRepository profileDetailsRepository;

	@Autowired
	public ProfileDetailsService profileDetailsService;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	AuthenticationController authenticationController;

	//get list of users profiledetails
	@GetMapping("/profiledetails")
	public List<ProfileDetails> getData() {
		return profileDetailsService.getData();
	}

	
	//delete profile details by id
	@DeleteMapping("/profiledetails/{profileDetailsId}")
	public ResponseEntity<ApiResponce> deleteProfileDetails(
			@PathVariable("profileDetailsId") Integer profileDetailsId) {
		this.profileDetailsService.deleteProfileDetails(profileDetailsId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully", true, 100), HttpStatus.OK);
	}
	
	//get prodiledetails by id
	@GetMapping("/profiledetails/{profileDetailsId}")
	public ResponseEntity<ProfileDetails> getSingleProfile(@PathVariable Integer profileDetailsId) {
		ProfileDetails profileDetails = this.profileDetailsService.getSingleProfile(profileDetailsId);
		return new ResponseEntity<ProfileDetails>(profileDetails, HttpStatus.OK);
	}

	// create users profiledetails
	@PostMapping("/createprofiledetails")
	public ResponseEntity<ProfileDetailsResponce> createProfile(@RequestBody ProfileDetails profileDetails) {
		try {
			Integer profileId = profileDetails.getProfile().getProfileId();
			Profile profile = profileRepository.findById(profileId)
					.orElseThrow(() -> new IllegalArgumentException("profile  not found for the given profileId"));
			profileDetails.setProfile(profile);
			ProfileDetails savedProfile = profileDetailsRepository.save(profileDetails);
			ProfileDetailsResponce profileDetailsResponce = new ProfileDetailsResponce(
					savedProfile.getProfileDetailsId(), "Profile Details created successfully");
			return ResponseEntity.ok(profileDetailsResponce);

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

	//to show users all data from all tables 
	@GetMapping("/viewdetails/{profileId}")
	public Map<String, Object> getCustomProfileById(@PathVariable("profileId") long id) {
		Map<String, Object> resultSet = profileDetailsRepository.getAllUser(id);
		return resultSet;// .orElseThrow(() -> new RuntimeException("Profile not found"));
	}
	
	
	
	
	//update api
//	@PutMapping("/updateprofiledetails/{profileDetailsId}")
//	public ResponseEntity<Map<String, Object>> updateProfileDetails(@PathVariable Integer profileDetailsId, @RequestBody ProfileDetails updateProfileDetails) {
//	Optional<ProfileDetails> optionalProfile = profileDetailsRepository.findById(profileDetailsId);
//	if (optionalProfile.isPresent()) {
//	ProfileDetails profileDetails = optionalProfile.get();
//	// use reflection to update the fields of the profile entity
//	Field[] fields = updateProfileDetails.getClass().getDeclaredFields();
//	for (Field field : fields) {
//	field.setAccessible(true);
//	try {
//	Object value = field.get(updateProfileDetails);
//	if (value != null) {
//	field.set(profileDetails, value);
//	}
//	} catch (IllegalAccessException e) {
//	e.printStackTrace();
//	}
//	}
//	ProfileDetails savedProfile = profileDetailsRepository.save(profileDetails);
//	Map<String, Object> response = new HashMap<>();
//	response.put("message", "Profile Details updated successfully!");
//	response.put("profileDetailsId", savedProfile.getProfileDetailsId());
//	return ResponseEntity.ok().body(response);
//	} else {
//	return ResponseEntity.notFound().build();
//	}
//	}
	
	//update profiledetails by profile id 
	@PutMapping("/updateprofilesdetails/{profileId}")
	public ResponseEntity<Map<String, Object>> updateProfileDetails(@PathVariable Integer profileId, @RequestBody ProfileDetails updateProfileDetails) {
	    Optional<Profile> optionalProfile = profileRepository.findById(profileId);
	    if (optionalProfile.isPresent()) {
	        Profile profile = optionalProfile.get();
	        ProfileDetails profileDetails = profile.getProfileDetails();
	        if (profileDetails == null) {
	            return ResponseEntity.notFound().build();
	        }
	        // use reflection to update the fields of the profile details entity
	        Field[] fields = updateProfileDetails.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            field.setAccessible(true);
	            try {
	                Object value = field.get(updateProfileDetails);
	                if (value != null) {
	                    field.set(profileDetails, value);
	                }
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	        ProfileDetails savedProfileDetails = profileDetailsRepository.save(profileDetails);
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Profile Details updated successfully!");
	        response.put("profileDetailsId", savedProfileDetails.getProfileDetailsId());
	        return ResponseEntity.ok().body(response);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
