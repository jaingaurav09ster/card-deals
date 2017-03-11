package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.UserRole;

/**
 * This interface provides methods for getting User roles from the database
 * 
 * @author Gaurav Jain
 *
 */
public interface UserRoleDao {

	/**
	 * This method will fetch all the User roles from the database
	 * 
	 * @return user roles list
	 */
	List<UserRole> findAll();

	/**
	 * This method will fetch the User role
	 * 
	 * @param type
	 *            role value
	 * @return User role entity
	 */
	UserRole findByType(String type);

	/**
	 * This method will fetch the user role object from the database based on id
	 * passed
	 * 
	 * @param id
	 *            User role id
	 * @return User role object
	 */
	UserRole findById(int id);
}
