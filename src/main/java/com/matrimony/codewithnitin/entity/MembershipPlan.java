package com.matrimony.codewithnitin.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matrimony.codewithnitin.repository.MembershipRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MembershipPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MemberPlanId;
	private String PlanType;
	private String Description;
	private Long Duration;
	private String DurationType;
	private Long Amount;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	// mapping of user-membershipPlan
	@JsonIgnoreProperties("membershipPlan")
	@OneToOne(mappedBy = "membershipPlan", cascade = CascadeType.MERGE)
	private User user;

	// mapping of MembershipPlan - feature management
	@JsonIgnoreProperties("membershipPlan")
	@OneToOne(mappedBy = "membershipPlan", cascade = CascadeType.MERGE)
	private FeatureManagement featureManagement;

	// mapping of usermember-membershipPlan
	@JsonIgnoreProperties("membershipPlan")
	@OneToOne(mappedBy = "membershipPlan", cascade = CascadeType.MERGE)
	private UserMember userMember;
	
	private Boolean active;
	
	private Long nor ;
	
	public boolean isActive() {
        LocalDate startDate = LocalDate.of(dateCreated.getYear(), dateCreated.getMonth(), dateCreated.getDayOfMonth());
        LocalDate expirationDate;

        if (DurationType.equals("Days")) {
            expirationDate = startDate.plusDays(Duration);
        } else  {
            throw new IllegalArgumentException("Invalid duration type: " + DurationType);
        }

        return expirationDate.isAfter(LocalDate.now());
    }
	
	
	 
}
