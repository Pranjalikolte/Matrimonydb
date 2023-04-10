package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.City;
import com.matrimony.codewithnitin.entity.State;

public interface CityRepository extends JpaRepository<City, Long>{

	List<City> findByStateStateid(Long stateid);

	
}
