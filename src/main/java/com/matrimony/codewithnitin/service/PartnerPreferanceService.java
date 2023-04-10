package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.PartnerPreferance;
import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;

@Service
public class PartnerPreferanceService {

	@Autowired
	PartnerPreferenceRepository partnerPreferanceRepository;

	public void savePartnerPreferance(PartnerPreferance partnerPreferance) {
		partnerPreferanceRepository.save(partnerPreferance);

	}

	public List<PartnerPreferance> getData() {
		return partnerPreferanceRepository.findAll();
	}
}