package com.matrimony.codewithnitin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.UserMember;

public interface UserMemberRepository extends JpaRepository<UserMember , Long>{

}
