package com.matrimony.codewithnitin.config.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.Cast;
import com.matrimony.codewithnitin.entity.Religion;
import com.matrimony.codewithnitin.entity.Subcast;
import com.matrimony.codewithnitin.repository.CastRepository;
import com.matrimony.codewithnitin.repository.ReligionRepository;
import com.matrimony.codewithnitin.repository.SubcastRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ReligionDropDown {

	@Autowired
	ReligionRepository religionRepository;

	@Autowired
	CastRepository castRepository;

	@Autowired
	SubcastRepository subcastRepository;

	// to show all countries
	@GetMapping("/religion")
	public List<Religion> getAllReligions() {
		return religionRepository.findAll();
	}
	
	@GetMapping("/cast/{religionid}")
 public ResponseEntity<Map<String, List<Map<String, Object>>>> getCastByReligionId(@PathVariable Long religionid) {
	     List<Cast> casts = castRepository.findByReligionReligionid(religionid);
	     List<Map<String, Object>> castDataList = new ArrayList<>();
	     for (Cast cast : casts) {
	         Map<String, Object> castData = new HashMap<>();
	         castData.put("castid", cast.getCastid());
	         castData.put("castName", cast.getCastName());
	         castDataList.add(castData);
	     }
	     Map<String, List<Map<String, Object>>> response = new HashMap<>();
	     response.put("casts", castDataList);
	     return ResponseEntity.ok(response);
	 }
	
	@GetMapping("/subcast/{castid}")
	 public ResponseEntity<Map<String, List<Map<String, Object>>>> getSubcastByCastId(@PathVariable Long castid) {
	     List<Subcast> subcasts = subcastRepository.findByCastCastid(castid);
	     List<Map<String, Object>> subcastDataList = new ArrayList<>();
	     for (Subcast subcast : subcasts) {
	         Map<String, Object> subcastData = new HashMap<>();
	         subcastData.put("subcastid", subcast.getSubcastid());
	         subcastData.put("subcastName", subcast.getSubcastName());
	         subcastDataList.add(subcastData);
	     }
	     Map<String, List<Map<String, Object>>> response = new HashMap<>();
	     response.put("subcasts", subcastDataList);
	     return ResponseEntity.ok(response);
	 }

}
