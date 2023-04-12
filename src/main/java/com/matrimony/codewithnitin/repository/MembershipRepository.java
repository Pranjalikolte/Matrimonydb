package com.matrimony.codewithnitin.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matrimony.codewithnitin.entity.MembershipPlan;

public interface MembershipRepository extends JpaRepository<MembershipPlan ,Integer>{
	
	
	
	
	@Query("INSERT INTO MembershipPlan (PlanType, Description, Duration, Amount, active) VALUES "
	        + "('Basic', 'Basic Plan', '30 days', 1000, true), "
	        + "('Premium', 'Premium Plan', '60 days', 5000, true), "
	        + "('Platinum', 'Platinum Plan', '90 days', 10000, true)")
	static void insertAllPlans() {
		
	}

	
	@Query("SELECT m.MemberPlanId as MemberPlanId , m.PlanType as PlanType, m.Description as Description, m.Duration as Duration, m.Amount as Amount FROM MembershipPlan m")
	List<Map<String, Object>>  getMembershipPlanDetails();

	
	
	
}
