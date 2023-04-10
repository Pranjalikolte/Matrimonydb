package com.matrimony.codewithnitin.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.UserRepository;
  
@Service
public interface RegisterService {

	
	
	RegisterRequest updateUser(RegisterRequest registerRequest, Integer userId);

	public void deleteUser(Integer userId);

	RegisterRequest getSingleUser(Integer userId);

	List<RegisterRequest> getAllUsers();
	

//
//	Page<RegisterRequest> getUserPagination(Integer pageNumber, Integer pageSize){
//		Pageable pageable = PageRequest.of(pageNumber,pageSize);
//		return userRepository.findAll(pageable);
//	}

}
