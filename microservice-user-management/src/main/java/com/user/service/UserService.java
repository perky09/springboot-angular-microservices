package com.user.service;

import java.util.List;

import com.user.model.User;

public interface UserService {	

	User saveUser(User user);

	User getUserByUsername(String username);

	List<String> listOfUserById(List<Long> idList);

}
