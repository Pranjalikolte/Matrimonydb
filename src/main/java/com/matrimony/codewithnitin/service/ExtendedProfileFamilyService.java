package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.ExtendedProfileFamily;
import com.matrimony.codewithnitin.repository.ExtendedProfileFamilyRepository;

@Service
public class ExtendedProfileFamilyService {
	@Autowired
	ExtendedProfileFamilyRepository extendedProfileFamilyRepository;

	public void saveExtendedProfileFamily(ExtendedProfileFamily extendedProfileFamily) {
		extendedProfileFamilyRepository.save(extendedProfileFamily);

	}

	public List<ExtendedProfileFamily> getData() {
		return extendedProfileFamilyRepository.findAll();
	}
}
