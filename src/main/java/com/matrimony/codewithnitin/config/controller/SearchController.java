package com.matrimony.codewithnitin.config.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.matrimony.codewithnitin.entity.City;
import com.matrimony.codewithnitin.entity.Country;
import com.matrimony.codewithnitin.entity.PartnerPreferance;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.ProfileDetails;
import com.matrimony.codewithnitin.entity.State;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.payload.UserProfileRequest;
import com.matrimony.codewithnitin.repository.CityRepository;
import com.matrimony.codewithnitin.repository.CountryRepository;
import com.matrimony.codewithnitin.repository.PartnerPreferenceRepository;
import com.matrimony.codewithnitin.repository.ProfileRepository;
import com.matrimony.codewithnitin.repository.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SearchController {

	@Autowired
	EntityManager entityManager;

	@Autowired
	ProfileRepository profileRepository;
	
	@PostMapping("/search")
	public List<Map<String, Object>> searchadvanceProfiles(@RequestBody UserProfileRequest request) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<User> root = query.from(User.class);
		Join<User, Profile> profile = root.join("profile");
		Join<Profile, ProfileDetails> profileDetails = profile.join("profileDetails");
		// Select only specific columns from the three tables
		query.select(
				cb.array(root.get("firstName"), root.get("middleName"), root.get("lastName"),profile.get("profileId"), profile.get("religion"),
						profile.get("cast"), profile.get("subCast"), profile.get("gender"), profile.get("bloodGroup"),
						profile.get("state"), profileDetails.get("height"), profileDetails.get("marriageStatus"),
						profileDetails.get("motherTongue"), profileDetails.get("nativePlace"), profile.get("townCity"),
						profile.get("age"), profileDetails.get("zodiac"), profileDetails.get("eatingHabits"),
						profileDetails.get("occupation"), profileDetails.get("mangalik"), profileDetails.get("company"),
						profileDetails.get("smoking"), profileDetails.get("drinking"), profileDetails.get("gothram"),
						profileDetails.get("profession"), profileDetails.get("qualification"),
						profileDetails.get("course"), profileDetails.get("stream"), profileDetails.get("wokingWith")));
		List<Predicate> predicates = new ArrayList<>();
		if (request.getBasicSearch().getReligion() != null && !request.getBasicSearch().getReligion().equals("")) {
			predicates.add(
					cb.equal(cb.lower(profile.get("religion")), request.getBasicSearch().getReligion().toLowerCase()));
		}
		if (request.getBasicSearch().getBirthPlace() != null && !request.getBasicSearch().getBirthPlace().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("nativePlace")),
					request.getBasicSearch().getBirthPlace().toLowerCase()));
		}
		if (request.getBasicSearch().getMarriageStatus() != null
				&& !request.getBasicSearch().getMarriageStatus().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("marriageStatus")),
					request.getBasicSearch().getMarriageStatus().toLowerCase()));
		}
		if (request.getBasicSearch().getMotherTongue() != null
				&& !request.getBasicSearch().getMotherTongue().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("motherTongue")),
					request.getBasicSearch().getMotherTongue().toLowerCase()));
		}
		if (request.getBasicSearch().getState() != null && !request.getBasicSearch().getState().equals("")) {
			predicates.add(cb.equal(cb.lower(profile.get("state")), request.getBasicSearch().getState().toLowerCase()));
		}
		if (request.getBasicSearch().getSubCast() != null && !request.getBasicSearch().getSubCast().equals("")) {
			predicates.add(
					cb.equal(cb.lower(profile.get("subCast")), request.getBasicSearch().getSubCast().toLowerCase()));
		}
		if (request.getBasicSearch().getCast() != null && !request.getBasicSearch().getCast().equals("")) {
			predicates.add(cb.equal(cb.lower(profile.get("cast")), request.getBasicSearch().getCast().toLowerCase()));
		}
		if (request.getBasicSearch().getCountry() != null && !request.getBasicSearch().getCountry().equals("")) {
			predicates.add(
					cb.equal(cb.lower(profile.get("country")), request.getBasicSearch().getCountry().toLowerCase()));
		}
		if (request.getBasicSearch().getGender() != null && !request.getBasicSearch().getGender().equals("")) {
			predicates
					.add(cb.equal(cb.lower(profile.get("gender")), request.getBasicSearch().getGender().toLowerCase()));
		}
		if (request.getBasicSearch().getTownCity() != null && !request.getBasicSearch().getTownCity().equals("")) {
			predicates.add(
					cb.equal(cb.lower(profile.get("townCity")), request.getBasicSearch().getTownCity().toLowerCase()));
		}
		if (request.getBasicSearch().getMinHeights() != null && !request.getBasicSearch().getMinHeights().equals("")) {
			predicates.add(
					cb.greaterThanOrEqualTo(profileDetails.get("height"), request.getBasicSearch().getMinHeights()));
		}
		if (request.getBasicSearch().getMaxHeights() != null && !request.getBasicSearch().getMaxHeights().equals("")) {
			predicates
					.add(cb.lessThanOrEqualTo(profileDetails.get("height"), request.getBasicSearch().getMaxHeights()));
		}
		if (request.getBasicSearch().getMinAge() != null && !request.getBasicSearch().getMinAge().equals("")) {
			predicates.add(cb.greaterThanOrEqualTo(profile.get("age"), request.getBasicSearch().getMinAge()));
		}
		if (request.getBasicSearch().getMaxAge() != null && !request.getBasicSearch().getMaxAge().equals("")) {
			predicates.add(cb.lessThanOrEqualTo(profile.get("age"), request.getBasicSearch().getMaxAge()));
		}
		if (request.getAdvanceSearch().getQualification() != null
			    && !request.getAdvanceSearch().getQualification().equals("")) {
			        predicates.add(cb.equal(cb.lower(profileDetails.get("qualification")),
			                request.getAdvanceSearch().getQualification().toLowerCase()));
			}
		 
		if (request.getAdvanceSearch().getStream() != null && !request.getAdvanceSearch().getStream().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("stream")),
					request.getAdvanceSearch().getStream().toLowerCase()));
		}
		if (request.getAdvanceSearch().getCourse() != null && !request.getAdvanceSearch().getCourse().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("course")),
					request.getAdvanceSearch().getCourse().toLowerCase()));
		}
		if (request.getAdvanceSearch().getWorkingWith() != null
				&& !request.getAdvanceSearch().getWorkingWith().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("workingWith")),
					request.getAdvanceSearch().getWorkingWith().toLowerCase()));
		}

		if (request.getAdvanceSearch().getEatingHabits() != null
				&& !request.getAdvanceSearch().getEatingHabits().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("eatingHabits")),
					request.getAdvanceSearch().getEatingHabits().toLowerCase()));
		}
		if (request.getAdvanceSearch().getSmoking() != null && !request.getAdvanceSearch().getSmoking().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("smoking")),
					request.getAdvanceSearch().getSmoking().toLowerCase()));
		}
		if (request.getAdvanceSearch().getDrinking() != null && !request.getAdvanceSearch().getDrinking().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("drinking")),
					request.getAdvanceSearch().getDrinking().toLowerCase()));
		}
		if (request.getAdvanceSearch().getGothram() != null && !request.getAdvanceSearch().getGothram().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("gothram")),
					request.getAdvanceSearch().getGothram().toLowerCase()));
		}
		if (request.getAdvanceSearch().getZodiac() != null && !request.getAdvanceSearch().getZodiac().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("zodiac")),
					request.getAdvanceSearch().getZodiac().toLowerCase()));
		}
		if (request.getAdvanceSearch().getMangalik() != null && !request.getAdvanceSearch().getMangalik().equals("")) {
			predicates.add(cb.equal(cb.lower(profileDetails.get("mangalik")),
					request.getAdvanceSearch().getMangalik().toLowerCase()));
		}
		// If no parameters are provided, return all profiles
		if (predicates.isEmpty()) {
			CriteriaQuery<Object[]> selectAll = query.select(cb.array(root.get("firstName"), root.get("middleName"),
					root.get("lastName"), profile.get("profileId"), profile.get("religion"), profile.get("cast"),
					profile.get("subCast"), profile.get("gender"), profile.get("bloodGroup"), profile.get("state"),
					profileDetails.get("height"), profileDetails.get("marriageStatus"),
					profileDetails.get("motherTongue"), profileDetails.get("nativePlace"), profile.get("townCity"),
					profile.get("age"), profileDetails.get("zodiac"), profileDetails.get("eatingHabits"),
					profileDetails.get("occupation"), profileDetails.get("mangalik"), profileDetails.get("company"),
					profileDetails.get("smoking"), profileDetails.get("drinking"), profileDetails.get("gothram"),
					profileDetails.get("profession"), profileDetails.get("qualification"), profileDetails.get("course"),
					profileDetails.get("stream"), profileDetails.get("wokingWith")));

			List<Object[]> results = entityManager.createQuery(selectAll).getResultList();
			List<Map<String, Object>> response = new ArrayList<>();
			String[] columnNames = { "firstName", "middleName", "lastName", "profileId", "religion", "cast", "subCast",
					"gender", "bloodGroup", "state", "height", "marriageStatus", "motherTongue", "nativePlace",
					"townCity", "age", "zodiac", "eatingHabits", "occupation", "mangalik", "company", "smoking",
					"drinking", "gothram", "profession", "qualification", "course", "stream", "wokingWith" };
			for (Object[] result : results) {
				Map<String, Object> map = new LinkedHashMap<>();
				for (int i = 0; i < columnNames.length; i++) {
					map.put(columnNames[i], result[i]);
				}
				response.add(map);
			}
			return response;
		}
		query.where(predicates.toArray(new Predicate[] {}));
		List<Object[]> results = entityManager.createQuery(query).getResultList();
		List<Map<String, Object>> response = new ArrayList<>();
		String[] columnNames = { "firstName", "middleName", "lastName", "profileId", "religion", "cast", "subCast",
				"gender", "bloodGroup", "state", "height", "marriageStatus", "motherTongue", "nativePlace", "townCity",
				"age", "zodiac", "eatingHabits", "occupation", "mangalik", "company", "smoking", "drinking", "gothram",
				"profession", "qualification", "course", "stream", "wokingWith" };
		for (Object[] result : results) {
			Map<String, Object> map = new LinkedHashMap<>();
			for (int i = 0; i < columnNames.length; i++) {
				map.put(columnNames[i], result[i]);
			}
			response.add(map);
		}
		return response;
	}    
	 
	
	//    @GetMapping("/search")
	    public List<Profile> searchProfiles() {
	    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    	CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
	    	Root<Profile> profile = cq.from(Profile.class);
	    	Join<Profile, PartnerPreferance> partnerPreferance = profile.join("partnerPreferance", JoinType.INNER);

	    	cq.select(profile).distinct(true);

	    	Predicate predicate = cb.conjunction();

	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("preCast"), profile.get("cast")));
//	    	predicate = cb.and(predicate, cb.greaterThanOrEqualTo(partnerPreferance.get("preAgeRangeFrom"), profile.get("age")));
//	    	predicate = cb.and(predicate, cb.lessThanOrEqualTo(partnerPreferance.get("preAgeRangeTo"), profile.get("age")));
	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("preReligion"), profile.get("religion")));
	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("preSubCast"), profile.get("subCast")));
	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("prestate"), profile.get("state")));
	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("city"), profile.get("townCity")));
	    	predicate = cb.and(predicate, cb.equal(partnerPreferance.get("preCountry"), profile.get("country")));

	    	cq.where(predicate);

	    	TypedQuery<Profile> query = entityManager.createQuery(cq);
	    	List<Profile> profiles = query.getResultList();


	        return profiles;
	    
	}
}




	    
	    
	   
	      

	    
	   


	


