package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByEmail(String email);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByEmail(String email);

	List<User> findAllUsers(); 
	
	boolean isUserUnique(Integer id, String email);

}