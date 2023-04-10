package com.matrimony.codewithnitin.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.exception.ResourceNotFoundException;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired 
	UserRepository userRepository ;

	public void saveUserProfile(Profile profile) {
		profileRepository.save(profile);
	}

	public List<Profile> getData() {
		return profileRepository.findAll();
	}

	public Profile getSingleProfile(Integer profileId) {
		Profile profiles = this.profileRepository.findById(profileId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile", "profile id", profileId));
		return profiles;
	}

	public void deleteProfile(Integer profileId) {
		Profile profiles = this.profileRepository.findById(profileId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile", "profile id", profileId));
		this.profileRepository.delete(profiles);
	}

	 public List<Profile> getAllProfiles() {
	        return profileRepository.findAll();
	    }

	public Page<User> getUserPagination(Integer pageNumber, Integer pageSize) {
			PageRequest pageable = PageRequest.of(pageNumber,pageSize);
			return userRepository.findAll(pageable);
		

	}

//	public List<Object[]> getUserProfiles(String religion, String birthPlace, String marriageStatus,
//			String motherTongue, String state, String subCast, String cast ,String country ,String townCity,Integer heightFrom,Integer heightTo ) {
//		return userprofileRepository.findUserProfile(religion, birthPlace, marriageStatus, motherTongue, state, subCast,country,townCity,
//				cast,heightFrom,heightTo);
//	}
//
//	public List<Object[]> getSearchProfiles(String education, String company, String eatingHabits, String smoking,
//			String drinking, String gothram, String zodiac, String bloodGroup) {
//		return userprofileRepository.searchProfiles(education, company, eatingHabits, smoking, drinking, gothram, zodiac, bloodGroup);
//	}
//
//	public List<Object[]> getProfilesByFilter(String education, String company, String eatingHabits, String smoking,
//			String drinking, String gothram, String zodiac, String bloodGroup) {
//		
//	    return userprofileRepository.findProfileByFilter(education, company, eatingHabits, smoking, drinking, gothram, zodiac, bloodGroup);
//	
//	}

}

//	public List<UserProfileDTO> getAllUserProfiles(String religion, String birthPlace, String country) {
//        return userprofileRepository.findAllByReligionAndBirthPlaceAndCountry(religion, birthPlace, country);
//	}

//	public List<Object[]> getUserProfiles(String religion,  String country) {
//        return userprofileRepository.findAllByReligionAndBirthPlaceAndCountry(religion,  country);
//
//	}

//	public List<UserProfileDTO> getALLUserProfiles(String religion, String birthPlace, String country) {
//		return userprofileRepository.searchdata(religion, birthPlace, country);
//	}

// List<User> search(String town_City, String religion);
