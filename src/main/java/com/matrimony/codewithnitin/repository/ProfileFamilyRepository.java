package com.matrimony.codewithnitin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.ProfileFamily;
import com.matrimony.codewithnitin.entity.User;

public interface ProfileFamilyRepository extends JpaRepository<ProfileFamily, Integer> {

	Optional<User> findByProfileProfileId(Integer profileId);

}
