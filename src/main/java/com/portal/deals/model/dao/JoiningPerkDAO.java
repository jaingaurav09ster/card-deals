package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.JoiningPerk;

/**
 * This interface will provide the methods to manipulate JOININGPERK entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface JoiningPerkDAO {

	/**
	 * This method will return list of all the JOININGPERK entity from the
	 * database
	 * 
	 * @return List of JOININGPERK entity
	 */
	List<JoiningPerk> listAllJoiningPerks();

	/**
	 * This method will return list of all the JOININGPERK entity for a CARD
	 * from the database
	 * 
	 * @return List of JOININGPERK entity
	 */
	List<JoiningPerk> getJoiningPerksByCardId(Integer cardId);

	/**
	 * This method will return the JoiningPerk based on the JoiningPerk id
	 * passed.
	 * 
	 * @param id
	 *            joiningPerk id
	 * @return JOININGPERK entity
	 */
	JoiningPerk getJoiningPerkById(Integer id);

	/**
	 * This method will save the JOININGPERK entity to the database
	 * 
	 * @param joiningPerk
	 */
	void saveJoiningPerk(JoiningPerk joiningPerk);

	/**
	 * This method will update the JOININGPERK entity in the database
	 * 
	 * @param joiningPerk
	 */
	void updateJoiningPerk(JoiningPerk joiningPerk);

	/**
	 * This method will delete the joiningPerk passed as parameter
	 * 
	 * @param joiningPerk
	 *            JOININGPERK entity object to be deleted
	 */
	void deleteJoiningPerk(JoiningPerk joiningPerk);

	/**
	 * This method will delete the joiningPerk w.r.t the joiningPerk id passed
	 * as parameter
	 * 
	 * @param id
	 *            joiningPerk id
	 */
	void deleteJoiningPerkById(Integer id);
}