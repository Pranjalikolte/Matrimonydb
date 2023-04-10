package com.matrimony.codewithnitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData, Long> {

	Optional<ImageData> findByName(String fileName);

//	Optional<ImageData> findByProfileId(Integer profileId);


}
