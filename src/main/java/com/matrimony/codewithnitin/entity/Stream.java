package com.matrimony.codewithnitin.entity;

import jakarta.persistence.Column;
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
@Table(name = "stream")
public class Stream {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long streamid;

	@Column(nullable = false)
	private String streamName;

	private String active;

	private String description;

}
