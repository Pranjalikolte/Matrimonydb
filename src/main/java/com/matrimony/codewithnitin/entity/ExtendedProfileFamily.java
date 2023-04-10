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
public class ExtendedProfileFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer extendedId;
	private String memberName;
	private String memberOccupation;
	private String memberMarriageStatus;
	private String spouseName;
	private String spouseNativePlace;
	private String Relation;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdOn", nullable = false, updatable = false)
	private Date createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upadatedOn", nullable = false)
	private Date upadatedOn;

	// mapping profile-extendedprofilefamily
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;
}
