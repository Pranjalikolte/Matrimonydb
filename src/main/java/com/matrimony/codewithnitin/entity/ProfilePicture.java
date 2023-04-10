package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ProfilePicture {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer picid;

	@Column(name = "imageFileName")
	private String imageFileName;

	@Column(name = "type")
	private String type;

	// image bytes can have large lengths so we specify a value
	// which is more than the default length for picByte column

	private String imageFilePath;

	private String thumbNail;

	private String active;

	private String createdBy;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	// mapping for profile- profiledetails
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "profile_id", referencedColumnName = "profileId")
	private Profile profile;

}
