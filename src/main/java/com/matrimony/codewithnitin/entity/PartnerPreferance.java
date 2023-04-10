package com.matrimony.codewithnitin.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PartnerPreferance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer preferenceId;
	private String preEducation;
	private String preAgeRangeFrom;
	private String preAgeRangeTo;
	private String preHeight;
	private String preSkinColour;
	//private String preOccupation;
	private String preProfession;
	private String annualIncomeFrom;
	private String annualIncomeTo;
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
	private String preMotherTongue;
	private String city;
	private String preState;
	private String preCountry;
	private String qualification;
	private String stream;
	private String course;
	

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdOn", nullable = false, updatable = false)
	private Date createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upadatedOn", nullable = false)
	private Date upadatedOn;

	// mapping profile-preferance
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;
}