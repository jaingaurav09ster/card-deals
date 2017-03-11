package com.portal.deals.model.dao;

import com.portal.deals.model.VerificationToken;

/**
 * This interface provide methods to perform database operations on
 * VerificationToken entity
 * 
 * @author Gaurav Jain
 *
 */
public interface VerificationTokenDao {

	/**
	 * This method will retrieve the token entity from the database based on the
	 * token value passed
	 * 
	 * @param token
	 * @return
	 */
	VerificationToken getToken(String token);

	/**
	 * This method will save the token entity to the database
	 * 
	 * @param token
	 */
	void save(VerificationToken token);

}
