package com.portal.deals.model.dao;

import com.portal.deals.model.PasswordResetToken;

/**
 * This interface provide methods to perform database operations on
 * PasswordResetToken entity
 * 
 * @author Gaurav Jain
 *
 */
public interface PasswordResetTokenDao {

	/**
	 * This method will retrieve the token entity from the database based on the
	 * token value passed
	 * 
	 * @param token
	 *            The token value
	 * @return the token entity object
	 */
	PasswordResetToken getToken(String token);

	/**
	 * This method will save the token entity to the database
	 * 
	 * @param token
	 *            the token object
	 */
	void save(PasswordResetToken token);

}
