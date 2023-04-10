package com.matrimony.codewithnitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.ExtendedProfileFamily;
import com.matrimony.codewithnitin.entity.User;

public interface ExtendedProfileFamilyRepository extends JpaRepository<ExtendedProfileFamily, Integer> {

	Optional<User> findByProfileProfileId(Integer profileId);

}
