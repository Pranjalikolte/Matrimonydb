package com.matrimony.codewithnitin.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.codewithnitin.entity.User;
import com.matrimony.codewithnitin.exception.ResourceNotFoundException;
import com.matrimony.codewithnitin.payload.RegisterRequest;
import com.matrimony.codewithnitin.repository.UserRepository;
import com.matrimony.codewithnitin.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RegisterRequest updateUser(RegisterRequest registerRequest, Integer userId) {
		User users = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		users.setUserId(registerRequest.getUserId());
		users.setFirstName(registerRequest.getFirstName());
		users.setMiddleName(registerRequest.getMiddleName());
		users.setLastName(registerRequest.getLastName());
		users.setEmailId(registerRequest.getEmailId());
		users.setPassword(registerRequest.getPassword());
		users.setDisplayName(registerRequest.getDisplayName());

		User updateUser = this.userRepository.save(users);
		return this.modelMapper.map(updateUser, RegisterRequest.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User users = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		this.userRepository.delete(users);
	}

	@Override
	public RegisterRequest getSingleUser(Integer userId) {
		User users = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		return this.modelMapper.map(users, RegisterRequest.class);
	}

	@Override
	public List<RegisterRequest> getAllUsers() {
		List<User> allusers = this.userRepository.findAll();
		List<RegisterRequest> registerRequests = allusers.stream()
				.map((users) -> this.modelMapper.map(users, RegisterRequest.class)).collect(Collectors.toList());
		return registerRequests;
	}

}
