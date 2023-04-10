package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileFamilyId;
	private String fatherName;
	private String motherName;
	private String fatherOccupation;
	private String motherOccupation;
	private String motherNativeplace;
	private String fatherEducation;
	private String motherEducation;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	// mapping profile-profilefamily
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;
}
