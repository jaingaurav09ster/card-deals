package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.User;

/**
 * This interface will provide the methods to manipulate USER entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface UserDao {

	/**
	 * This method will get the User entity object from the database, based on
	 * the user id passed.
	 * 
	 * @param id
	 *            User's id
	 * @return user entity object
	 */
	User findById(int id);

	/**
	 * This method will get the User entity object from the database, based on
	 * the user email passed.
	 * 
	 * @param email
	 *            User's email
	 * @return User entity object
	 */
	User findByEmail(String email);

	/**
	 * This method will persist the User entity to the database.
	 * 
	 * @param user
	 *            The user entity
	 */
	void save(User user);

	/**
	 * This method will delete the user from the database, based on the user
	 * email passed
	 * 
	 * @param email
	 *            The User's email
	 */
	void deleteByEmail(String email);

	/**
	 * This method will get the User entity list from the database
	 * 
	 * @return List of Users
	 */
	List<User> findAllUsers();

}
