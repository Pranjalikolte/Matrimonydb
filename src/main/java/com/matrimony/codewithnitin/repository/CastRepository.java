package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.Cast;

public interface CastRepository extends JpaRepository<Cast, Long> { 
	
	List<Cast> findByReligionReligionid(Long religionid);



}
