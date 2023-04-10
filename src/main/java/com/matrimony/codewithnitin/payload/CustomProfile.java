package com.matrimony.codewithnitin.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomProfile {

	// from user table
	private String firstName;
	private String middleName;
	private String lastName;
	private String displayName;

	// from profile table
	private String profileName;
	private Date dob;
	private String gender;
	private String religion;
	private String cast;
	private String subCast;
	private String address1;
	private String address2;
	private String townCity;
	private String state;
	private String country;
	private String bloodGroup;

	// from profileDetails table
	private String skinColour;
	private String height;
	private String weight;
	private String bodyType;
	private String education;
	private String collegeUniversity;
	private String occupation;
	private String profession;
	private String company;
	private String annualIncome;
	private String nativePlace;
	private String birthPlace;
	private String birthTime;
	private String marriageStatus;
	private String physicalStatus;
	private String motherTongue;
	private String zodiac;
	private String eatingHabits;
	private String drinking;
	private String smoking;
	private String hobbies;
	private String gothram;
	private String mangalik;
	private String propertyDetail;

	// from profilefamily table
	private String fatherName;
	private String motherName;
	private String fatherOccupation;
	private String motherOccupation;
	private String motherNativeplace;
	private String fatherEducation;
	private String motherEducation;

	// from profileextendedfamily table
	private String memberName;
	private String memberOccupation;
	private String memberMarriageStatus;
	private String spouseName;
	private String spouseNativePlace;
	private String Relation;

	// from partnerpreferance table
	private String preEducation;
	private String preAgeRangeFrom;
	private String preAgeRangeTo;
	private String preHeight;
	private String preSkinColour;
	private String preOccupation;
	private String preProfession;
	private String preReligion;
	private String preCast;
	private String preSubCast;
	private String preSmokingHabit;
	private String preDrinkingHabit;
	private String preEatingHabit;
	private String preGothram;
	private String preRashi;
	private String preMangalik;
	private String preStar_Nakshtra;
	private String preMarriageStatus;
	private String preCity;
	private String preMotherTongue;
	private String preCountry;
	private String preState;
}