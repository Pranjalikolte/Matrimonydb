package com.matrimony.codewithnitin.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.PartnerPreferance;
import com.matrimony.codewithnitin.entity.Profile;
import com.matrimony.codewithnitin.entity.User;

public interface PartnerPreferenceRepository extends JpaRepository<PartnerPreferance, Integer> {

//	PartnerPreferance findByProfileId(Long profileId);

	
	//find matching profiles based on preferance  
	@Query("SELECT u.firstName as firstName, u.middleName as middleName, u.lastName as lastName ,"
			+ "p.profileId as profileId, p.religion as religion, p.cast as cast, p.subCast as subCast, "
			+ "p.gender as gender,p.townCity as townCity,p.age as age, p.bloodGroup as bloodGroup, p.state as state,"
			+ "pd.height as height, pd.marriageStatus as marriageStatus, pd.motherTongue as motherTongue, "
			+ "pd.nativePlace as nativePlace,pd.zodiac as zodiac,pd.eatingHabits as eatingHabits,pd.occupation as occupation,"
			+ "pd.mangalik as mangalik, pd.company as company, pd.smoking as smoking, pd.drinking as drinking, pd.gothram as gothram, "
			+ "pd.profession as profession,pd.qualification as qualification, pd.course as course, pd.stream as stream, "
			+ "pd.wokingWith as wokingWith " +
		       "FROM Profile p " +
		       "JOIN p.user u " +
		       "JOIN p.partnerPreferance pp " +
		       "JOIN p.profileDetails pd " +
		       "WHERE p.religion = ( " +
		       "    SELECT pp.preReligion " +
		       "    FROM PartnerPreferance pp " +
		       "    WHERE pp.profile.profileId = :profileId " +
		       ") " +
				"AND p.gender <> (" +
				"    SELECT p.gender " +
				"    FROM Profile p " +
				"    WHERE p.profileId = :profileId" +
				") " +
		       "AND p.state = ( " +
		       "    SELECT pp.preState " +
		       "    FROM PartnerPreferance pp " +
		       "    WHERE pp.profile.profileId = :profileId " +
		       ") " +
		       "AND p.cast = ( " +
		       "    SELECT pp.preCast " +
		       "    FROM PartnerPreferance pp " +
		       "    WHERE pp.profile.profileId = :profileId " +
		       ") " +
		       "AND pd.marriageStatus = ( " +
		       " SELECT pp.preMarriageStatus " +
		       "    FROM PartnerPreferance pp " +
		       "    WHERE pp.profile.profileId = :profileId " +
		       ") " +
		       "AND p.age BETWEEN ( " +
		       " SELECT pp.preAgeRangeFrom " +
		       " FROM PartnerPreferance pp " +
		       " WHERE pp.profile.profileId = :profileId " +
		       ") " +
		       "AND( " +
		       " SELECT pp.preAgeRangeTo " +
		       " FROM PartnerPreferance pp " +
		       " WHERE pp.profile.profileId = :profileId " +
		       ") " +
		       "AND p.profileId <> :profileId")
	List<Map<String, Object>> findByPartnerPreferanceAndProfileId(@Param("profileId") Long profileId);

	Optional<User> findByProfileProfileId(Integer profileId);	
}
