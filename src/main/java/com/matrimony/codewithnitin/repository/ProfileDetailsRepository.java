package com.matrimony.codewithnitin.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matrimony.codewithnitin.entity.ProfileDetails;
import com.matrimony.codewithnitin.entity.User;

public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, Integer> {

	Optional<ProfileDetails> findById(Integer profileDetailsId);

	@Query("SELECT COUNT(pd) FROM ProfileDetails pd JOIN pd.profile p WHERE p.id = :profileId")
	Optional<Object[]> countByProfileId(@Param("profileId") Integer profileId);

	@Query(value = "select u.first_name as firstName," + "u.middle_name as middleName, " + "u.last_name as lastName, "
			+ "u.display_name as displayName, "
			// Profile table data
			+ "p.profile_name as profileName, " + "p.dob as dob, " + "p.gender as gender, " + "p.religion as religion, "
			+ "p.cast as cast, " + "p.sub_cast as subCast, " + "p.address1 as address1, " + "p.address2 as address2, "
			+ "p.country as country, " + "p.state as state, " + "p.town_city as townCity," + " p.pin_code as pinCode,"
			+ "p.age as age," + "p.blood_group as bloodGroup ," + "p.dob as dob, "
			// ProfileDetails table data
			+ " pd.skin_colour as skinColour, " + " pd.height as height, " + " pd.weight as weight, "
			+ " pd.body_type as bodyType, " + " pd.qualification as qualification, " + " pd.stream as stream, "
			+ " pd.woking_with as wokingWith, " + " pd.occupation as occupation, " + "pd.course as course, "
			+ " pd.occupation as occupation, " + " pd.profession as profession, "
			+ " pd.annual_income as annualIncome, " + " pd.native_place as nativePlace, "
			+ " pd.birth_time as birthTime, " + " pd.marriage_status as marriageStatus, "
			+ " pd.physical_status as physicalStatus, " + " pd.mother_tongue as motherTongue, "
			+ " pd.zodiac as zodiac, " + " pd.eating_habits as eatingHabits, " + " pd.drinking as drinking, "
			+ " pd.eating_habits as eatingHabits, " + " pd.smoking as smoking, " + " pd.hobbies as hobbies, "
			+ " pd.gothram as gothram, " + " pd.mangalik as mangalik, " + " pd.property_detail as propertyDetail, "
			+ "pd.company as company," + " pd.star_nakshtra as starNakshtra,"
			// profilefamily table data
			+ " pf.father_name as fatherName, " + " pf.mother_name as motherName, "
			+ " pf.father_occupation as fatherOccupation, " + " pf.mother_occupation as motherOccupation, "
			+ " pf.mother_nativeplace as motherNativeplace, " + " pf.mother_nativeplace as mother_nativeplace, "
			+ " pf.mother_education as motherEducation, "
			// profileextendedfamily table data
			+ " pef.member_name as memberName, " + " pef.member_occupation as memberOccupation, "
			+ " pef.member_marriage_status as memberMarriageStatus, " + " pef.spouse_name as spouseName, "
			+ " pef.spouse_native_place as spouseNativePlace, " + " pef.relation as Relation, "
			// partnerpreferance table data
			+ " pp.pre_education as preEducation, " + " pp.pre_age_range_from as preAgeRangeFrom, "
			+ " pp.pre_age_range_to as preAgeRangeTo, " + " pp.pre_height as preHeight, "
			+ " pp.pre_skin_colour as preSkinColour, " + " pp.pre_occupation as preOccupation, "
			+ " pp.pre_profession as preProfession, " + " pp.pre_religion as preReligion, "
			+ " pp.pre_cast as preCast, " + " pp.pre_sub_cast as preSubCast, "
			+ " pp.pre_smoking_habit as preSmokingHabit, " + " pp.pre_drinking_habit as preDrinkingHabit, "
			+ " pp.pre_eating_habit as preEatingHabit, " + " pp.pre_gothram as preGothram, "
			+ " pp.pre_rashi as preRashi, " + " pp.pre_mangalik as preMangalik, "
			+ " pp.pre_star_nakshtra as preStar_Nakshtra, " + " pp.pre_marriage_status as preMarriageStatus, "
			+ " pp.city as preCity," + " pp.pre_state as preState, " + "pp.stream as preStream ,"
			+ "pp.qualification as preQualification," + "pp.course as preCourse ,"
			+ " pp.pre_mother_tongue as preMotherTongue, " + " pp.pre_country as preCountry " + "from profile p "
			+ "left join _user u on p.user_id = u.user_id "
			+ "left join profile_details pd on p.profile_id = pd.profile_id "
			+ "left join profile_family pf on p.profile_id = pf.profile_id "
			+ "left join partner_preferance pp on p.profile_id = pp.profile_id "
			+ "left join extended_profile_family pef on p.profile_id = pef.profile_id "
			+ "where p.profile_id = :profileid", nativeQuery = true)
	Map<String, Object> getAllUser(@Param("profileid") long profileid);

	Optional<User> findByProfileProfileId(Integer profileId);
}