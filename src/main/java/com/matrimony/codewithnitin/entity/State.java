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
@Table(name = "state")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateid;

	@Column(nullable = false)
	private String stateName;

	@ManyToOne()
	@JoinColumn(name = "country_countryid", nullable = false)
	private Country country;

	@OneToMany(mappedBy = "state")
	@JsonIgnore
	private List<City> cities;
	
	private String active;

	private String description;

	
}
