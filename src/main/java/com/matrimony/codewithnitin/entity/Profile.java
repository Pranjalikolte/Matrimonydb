package com.matrimony.codewithnitin.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "profile", uniqueConstraints = { @UniqueConstraint(columnNames = "user_id") })

public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Integer profileId;

	@NotNull
	private String profileName;

	@NotNull
	private Date dob;

	@NotNull
	private String gender;

	@NotNull
	private String religion;

	@NotNull
	private String cast;

	@NotNull
	private String subCast;

	@NotNull
	private String address1;

	@NotNull
	private String address2;

	@NotNull
	private String townCity;

	@NotNull
	private String state;

	@NotNull
	private String country;

	@NotNull
	private String pinCode;

	private Integer age;

	private String profstatus = "pending";

	@NotNull
	private String bloodGroup;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	private static Integer calculateAgeFromDob(Date dob) {
		LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
		this.age = calculateAgeFromDob(dob);
	}

	// mapping of user-profile
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	// @JsonIgnore
	private User user;

	// mapping for profile - profiledetails
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private ProfileDetails profileDetails;

	// mapping for profile-ProfileFamily
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private ProfileFamily profileFamily;

	// mapping for profile-ExtendedProfileFamily
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private ExtendedProfileFamily extendedprofileFamily;

	// mapping for profile-PartnerPreferance
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private PartnerPreferance partnerPreferance;
	
	// mapping for profile - profiledetails
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private ProfilePicture profilePicture;

}