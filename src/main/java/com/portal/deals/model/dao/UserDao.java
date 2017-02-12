package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByEmail(String email);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	List<User> findAllUsers();

}

