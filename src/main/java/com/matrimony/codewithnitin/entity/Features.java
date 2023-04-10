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
public class Features {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FeatureId;
	private String FeatureName;
	private String Description;
	private String featureType;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;
	
	// mapping of user-profile
		 @JsonIgnoreProperties("features")
		@OneToOne(mappedBy = "features", cascade = CascadeType.MERGE)
		private FeatureManagement featureManagement;
	
}
