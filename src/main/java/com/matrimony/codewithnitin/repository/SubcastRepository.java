package com.matrimony.codewithnitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.matrimony.codewithnitin.entity.Subcast;

public interface SubcastRepository extends JpaRepository<Subcast, Long> { 

	List<Subcast> findByCastCastid(Long castid);
}
