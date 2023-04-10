package com.matrimony.codewithnitin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseid;

	@Column(nullable = false)
	private String courseName;

	private String active;

	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "qualification_qualificationid", referencedColumnName = "qualificationid", nullable = false)
	private Qualification qualification;

}
