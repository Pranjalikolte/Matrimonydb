package com.matrimony.codewithnitin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrimony.codewithnitin.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	Token save(Token userToken);

}