package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);		
		
	}
	@Override
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username).orElse(null);
	}
	
	@Override
	public List<String> listOfUserById(List<Long> idList) {
		return userRepository.findByIdList(idList);
	}
	

}
