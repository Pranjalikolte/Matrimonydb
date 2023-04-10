package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.Country;
import com.matrimony.codewithnitin.entity.State;

public interface CountryRepository extends JpaRepository<Country, Long> {

//	 @Query("SELECT s FROM State s WHERE s.country.id = :countryId")
//	   List<State> findStatesByCountryId(@Param("countryId") Long countryId);

	@Query("SELECT s FROM State s JOIN FETCH s.cities WHERE s.country.id = :countryId")
	  List<State> findStatesByCountryId(@Param("countryId") Long countryId);
	
	 @Query("SELECT c FROM Country c")
	   List<Country> findAllCountries();
	
	
}
