package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userToken")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;

	private String token;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;
}
