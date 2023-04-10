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

import com.matrimony.codewithnitin.entity.City;
import com.matrimony.codewithnitin.entity.Country;
import com.matrimony.codewithnitin.entity.State;
import com.matrimony.codewithnitin.repository.CityRepository;
import com.matrimony.codewithnitin.repository.CountryRepository;
import com.matrimony.codewithnitin.repository.StateRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CountryDropDownController {
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	
	//to show all countries 
	@GetMapping("/countries")
	public List<Country> getAllCountries() {
	    return countryRepository.findAll();
	}
	

	 
	 @GetMapping("/states/{countryid}")
	 public ResponseEntity<Map<String, List<Map<String, Object>>>> getStatesByCountryId(@PathVariable Long countryid) {
	     List<State> states = stateRepository.findByCountryCountryid(countryid);
	     List<Map<String, Object>> stateDataList = new ArrayList<>();
	     for (State state : states) {
	         Map<String, Object> stateData = new HashMap<>();
	         stateData.put("stateid", state.getStateid());
	         stateData.put("stateName", state.getStateName());
	         stateDataList.add(stateData);
	     }
	     Map<String, List<Map<String, Object>>> response = new HashMap<>();
	     response.put("states", stateDataList);
	     return ResponseEntity.ok(response);
	 }
	 
	 
	 @GetMapping("/cities/{stateid}")
	 public ResponseEntity<Map<String, List<Map<String, Object>>>> getCitiesByStateId(@PathVariable Long stateid){
	     List<City> cities = cityRepository.findByStateStateid(stateid);
	     List<Map<String, Object>> cityList = new ArrayList<>();
	     for (City city : cities) {
	         Map<String, Object> cityMap = new HashMap<>();
	         cityMap.put("cityid", city.getCityid());
	         cityMap.put("cityName", city.getCityName());
	         cityList.add(cityMap);
	     }
	     Map<String, List<Map<String, Object>>> response = new HashMap<>();
	     response.put("cities", cityList);
	     return ResponseEntity.ok(response);
	 }
	 
	 



}
