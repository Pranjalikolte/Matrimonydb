package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.State;


public interface StateRepository extends JpaRepository<State, Long> {

	

//	@Query("SELECT s, c FROM State s JOIN s.cities c WHERE s.country.countryid = :countryId")
//	List<Object[]> findStatesAndCitiesByCountryId(@Param("countryId") Long countryId);
//
//	 @Query("SELECT c FROM City c WHERE c.state.id = :stateId")
//	 List<City> findCitiesByStateId(@Param("stateId") Long stateId);
//
//
//	 @Query("SELECT s.stateid, s.stateName, c.cityid, c.cityName FROM State s JOIN s.cities c WHERE s.country.countryid = :countryId")
//	 List<StateWithCitiesDTO> findByCountryId(@Param("countryId") Long countryId);

	 
//	 @Query("SELECT s.stateid, s.stateName, c.cityid, c.cityName " +
//	            "FROM State s " +
//	            "JOIN s.cities c " +
//	            "JOIN s.country co " +
//	            "WHERE co.countryid = :countryId")
//	    List<Object[]> findStateAndCityByCountryId(@Param("countryId") Long countryId);

	 
	    @Query("SELECT s.stateid, s.stateName, c.cityid, c.cityName " +
	            "FROM State s " +
	            "LEFT JOIN s.cities c " +
	            "WHERE s.country.countryid = :countryid " +
	            "ORDER BY s.stateid, c.cityid")
	    List<Object[]> findStateAndCityByCountryId(@Param("countryid") Long countryid);

		List<State> findByCountryCountryid(Long countryid);


}
