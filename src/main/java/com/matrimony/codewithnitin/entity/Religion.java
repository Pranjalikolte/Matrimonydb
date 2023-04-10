package com.matrimony.codewithnitin.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "religion")
public class Religion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long religionid;

	private String religionName;
	
	private String active;

	private String description;

	// getters and setters

}
