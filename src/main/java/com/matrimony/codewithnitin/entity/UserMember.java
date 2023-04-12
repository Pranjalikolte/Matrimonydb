package com.matrimony.codewithnitin.entity;

import java.time.Duration;
import java.time.LocalDate;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long memId;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	// mapping of user-membership
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;

	// mapping of user-membership
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "MembershipPlan_Id", referencedColumnName = "MemberPlanId")
	// @JsonIgnore
	private MembershipPlan membershipPlan;

	// private Boolean active;

	public boolean isactive() {
		LocalDateTime now = LocalDateTime.now();
		Duration planDuration = Duration.ofDays(this.membershipPlan.getDuration());
		LocalDateTime expirationDate = this.dateCreated.plus(planDuration);

		boolean isExpired = now.isAfter(expirationDate);
		this.active = !isExpired;

		return !isExpired;
	}

	private Boolean active;
	private Long nor;
	private LocalDate expirationDate;

	@PostPersist
	@PostUpdate
	public void calculateExpirationDate() {
		LocalDate startDate = LocalDate.of(dateCreated.getYear(), dateCreated.getMonth(), dateCreated.getDayOfMonth());

		if (membershipPlan.getDurationType().equals("days")) {
			expirationDate = startDate.plusDays(membershipPlan.getDuration());
		}
		else {
			throw new IllegalArgumentException("Invalid duration type: " + membershipPlan.getDurationType());
		}

		active = expirationDate.isAfter(LocalDate.now());
	}

	public boolean isActive() {
		return active;
	}

}
