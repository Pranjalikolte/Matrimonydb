package com.matrimony.codewithnitin.entity;


import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MembershipPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MemberPlanId;
	private String PlanType;
	private String Description;
	private String Duration;
	private String Amount;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;
	
	// mapping of user-membershipPlan
		@JsonIgnoreProperties("membershipPlan")
		@OneToOne(mappedBy = "membershipPlan", cascade = CascadeType.MERGE)
		private User user;
	
		 //mapping of MembershipPlan - feature management
		@JsonIgnoreProperties("membershipPlan")
		@OneToOne(mappedBy = "membershipPlan", cascade = CascadeType.MERGE)
		private FeatureManagement featureManagement;
	
		
}
