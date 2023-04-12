package com.matrimony.codewithnitin.config.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.codewithnitin.entity.MembershipPlan;
import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.entity.UserMember;
import com.matrimony.codewithnitin.payload.MembershipPlanResponse;
import com.matrimony.codewithnitin.repository.MembershipRepository;
import com.matrimony.codewithnitin.repository.UserMemberRepository;
import com.matrimony.codewithnitin.repository.UserRepository;
import com.matrimony.codewithnitin.service.MembershipService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MembershipController {
	
	 @Autowired
	    private MembershipRepository membershipRepository;
	 
	 @Autowired
	 private UserMemberRepository userMemberRepository;
	 
	 @Autowired
	 private UserRepository userRepository;

	    @Autowired
	    private MembershipService membershipService;
	    
	    //list of  plans available
	    @GetMapping("/plandetails")
	    public List<Map<String,Object>> getMembershipPlanDetails() {
	        return membershipRepository.getMembershipPlanDetails();
	    }

	    
//	    @PostMapping("/user-member")
//	    public ResponseEntity<UserMember> createUserMember(@RequestBody UserMember userMember) {
//	    	Integer userId = userMember.getUser().getUserId();
//	        UserMember newUserMember = userMemberRepository.save(userMember);
//	        return ResponseEntity.created(URI.create("/user-member/" + newUserMember.getMemId())).body(newUserMember);
//	    }

	    
//	    @PostMapping("/user-member")
//	    public ResponseEntity<UserMember> createUserMember(@RequestBody UserMember userMember) {
//	        Integer userId = userMember.getUser().getUserId();
//	        Integer membershipPlanId = userMember.getMembershipPlan().getMemberPlanId();
//
//	        User user = userRepository.findById(userId)
//	                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));
//	        MembershipPlan membershipPlan = membershipRepository.findById(membershipPlanId)
//	                .orElseThrow(() -> new IllegalArgumentException("Membership plan not found with id " + membershipPlanId));
//
//	        userMember.setUser(user);
//	        userMember.setMembershipPlan(membershipPlan);
//	        userMember.setActive(true);
//
//	        userMemberRepository.save(userMember);
//
//	        return new ResponseEntity<>(userMember, HttpStatus.CREATED);
//	    }
	    
	    
	    // api to take membership plan
	    @PostMapping("/addplan")
	    public ResponseEntity<MembershipPlanResponse> createUserMember(@RequestBody UserMember userMember) {
	        Integer userId = userMember.getUser().getUserId();
	        Integer membershipPlanId = userMember.getMembershipPlan().getMemberPlanId();

	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));
	        MembershipPlan membershipPlan = membershipRepository.findById(membershipPlanId)
	                .orElseThrow(() -> new IllegalArgumentException("Membership plan not found with id " + membershipPlanId));

	        userMember.setUser(user);
	        userMember.setMembershipPlan(membershipPlan);
	        userMember.setActive(true);

	        userMemberRepository.save(userMember);

	        String message = "Membership plan accessed with id " + membershipPlanId;
	        MembershipPlanResponse response = new MembershipPlanResponse(membershipPlanId, message);

	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }

//	    @GetMapping("/expired-user-members")
//	    public List<Map<String, String>> getExpiredUserMembers() {
//	        List<UserMember> allUserMembers = userMemberRepository.findAll();
//	        return allUserMembers.stream()
//	                .filter(userMember -> !userMember.isactive())
//	                .map(userMember -> {
//	                    Map<String, String> map = new HashMap<>();
//	                    map.put("memId", userMember.getMemId().toString());
//	                    map.put("message", "Plan expired");
//	                    return map;
//	                })
//	                .collect(Collectors.toList());
//	    }
//}

	    
	    // to check plan is expired or is active
	    @GetMapping("/expiredmembers")
	    public List<Map<String, String>> getExpiredUserMembers() {
	        List<UserMember> allUserMembers = userMemberRepository.findAll();
	        LocalDateTime now = LocalDateTime.now();
	        return allUserMembers.stream()
	                .map(userMember -> {
	                    Map<String, String> map = new HashMap<>();
	                    map.put("memId", userMember.getMemId().toString());
	                    if (userMember.isactive()) {
	                        Duration timeLeft = Duration.between(now, userMember.getDateCreated().plusDays(userMember.getMembershipPlan().getDuration()));
	                        if (timeLeft.toDays() < 0) {
	                            map.put("message", "Plan is expired");
	                        } else {
	                            map.put("message", "Plan active");
	                            map.put("daysLeft", Long.toString(timeLeft.toDays()));
	                        }
	                    } else {
	                        map.put("message", "Plan is expired");
	                    }
	                    return map;
	                })
	                .collect(Collectors.toList());
	    }

}


	    
