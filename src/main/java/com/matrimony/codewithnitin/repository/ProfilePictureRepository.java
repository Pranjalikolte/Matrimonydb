package com.matrimony.codewithnitin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.ProfilePicture;


public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Integer> {

	Optional<ProfilePicture> findByimageFileName(String imageFileName);

    Optional<ProfilePicture> findByImageFileName(String imageFileName);
    
    Optional<ProfilePicture> findByProfile_ProfileId(Integer profileId);


    
  // Optional<ProfilePicture> findByProfileId(Integer profileId);


}
