package com.matrimony.codewithnitin.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "qualification")
public class Qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qualificationid;

	@Column(nullable = false)
	private String qualificationName;

	private String active;

	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "stream_streamid", nullable = false)
	private Stream stream;

	@OneToMany(mappedBy = "qualification")
	@JsonIgnore
	private List<Course> courses;

}
