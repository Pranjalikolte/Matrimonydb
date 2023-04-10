package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
	
	List<Qualification> findByStreamStreamid(Long streamid);


}
