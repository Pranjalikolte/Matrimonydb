package com.matrimony.codewithnitin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matrimony.codewithnitin.entity.MembershipPlan;
import com.matrimony.codewithnitin.repository.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Transactional
    public void insertAllPlans() {
        MembershipRepository.insertAllPlans();
    }
	
    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipRepository.findAll();
    }
}
