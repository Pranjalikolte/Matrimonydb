package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;
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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileDetailsId;

	private String skinColour;

	private String height;

	private String weight;

	private String bodyType;

	private String qualification;

	private String stream;

	private String course;

	private String wokingWith;

	private String occupation;

	private String profession;

	private String company;

	private String annualIncome;

	private String nativePlace;


	private String birthTime;

	private String marriageStatus;

	private String physicalStatus;

	private String motherTongue;

	private String zodiac;

	private String eatingHabits;

	private String drinking;

	private String smoking;

	private String starNakshtra;

	private String hobbies;

	private String gothram;

	private String mangalik;

	private String propertyDetail;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	// mapping for profile- profiledetails
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;
}
