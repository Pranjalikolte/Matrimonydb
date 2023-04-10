package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.ProfileFamily;
import com.matrimony.codewithnitin.exception.ResourceNotFoundException;
import com.matrimony.codewithnitin.repository.ProfileFamilyRepository;

@Service
public class ProfileFamilyService {

	@Autowired
	ProfileFamilyRepository profileFamilyRepository;

	public void saveProfileDetails(ProfileFamily ProfileFamily) {
		profileFamilyRepository.save(ProfileFamily);

	}

	public List<ProfileFamily> getData() {
		return profileFamilyRepository.findAll();
	}

	public ProfileFamily getSingleProfileFamily(Integer profileFamilyId) {
		ProfileFamily profileFamily = this.profileFamilyRepository.findById(profileFamilyId)
				.orElseThrow(() -> new ResourceNotFoundException("profileFamily", "profileFamily id", profileFamilyId));
		return profileFamily;
	}

	public void deleteProfileFamily(Integer profileFamilyId) {
		ProfileFamily profileFamily = this.profileFamilyRepository.findById(profileFamilyId)
				.orElseThrow(() -> new ResourceNotFoundException("ProfileFamily", "profileFamily id", profileFamilyId));

		this.profileFamilyRepository.delete(profileFamily);
	}
}
