package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
