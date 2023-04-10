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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeatureManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FeatureMgmtId;
	
	private String Visibility;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;
	
	
	// mapping of membership-features
	 @OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "MembershipPlan_Id", referencedColumnName = "MemberPlanId")
	 // @JsonIgnore
	private MembershipPlan membershipPlan;	
	 
	// mapping of membership-features
		 @OneToOne(cascade = CascadeType.MERGE)
		@JoinColumn(name = "Features_Id", referencedColumnName = "FeatureId")
		 // @JsonIgnore
		private Features features;
}