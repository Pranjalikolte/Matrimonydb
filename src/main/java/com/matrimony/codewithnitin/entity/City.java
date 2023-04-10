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
@Table(name = "city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityid;

	@Column(nullable = false)
	private String cityName;

	private String active = "True";

	private String description;

	@ManyToOne()
	@JoinColumn(name = "state_stateid", referencedColumnName = "stateid", nullable = false)
	private State state;

}
