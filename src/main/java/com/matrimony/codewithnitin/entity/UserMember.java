package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMember {
	
	@Id
	private Long Id;
	
	
	@CreationTimestamp
	private LocalDateTime dateCreated;

}
