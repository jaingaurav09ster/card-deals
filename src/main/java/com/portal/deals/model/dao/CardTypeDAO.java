package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.CardType;

/**
 * This interface will provide the methods to manipulate CARD type entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface CardTypeDAO {

	/**
	 * This method will return list of all the CARD type entity from the
	 * database
	 * 
	 * @return List of CARD type entity
	 */
	List<CardType> listAllTypes();

	/**
	 * This method will return the Card type based on the card type id passed.
	 * 
	 * @param id
	 *            card id
	 * @return CARD type entity
	 */
	CardType getTypeById(Integer id);

	/**
	 * This method will save the CARD type entity to the database
	 * 
	 * @param card
	 *            type
	 */
	void saveType(CardType type);

	/**
	 * This method will update the CARD type entity in the database
	 * 
	 * @param card
	 *            type
	 */
	void updateType(CardType type);

	/**
	 * This method will delete the card type passed as parameter
	 * 
	 * @param card
	 *            CARD type entity object to be deleted
	 */
	void deleteType(CardType type);

	/**
	 * This method will delete the card type w.r.t the card type id passed as
	 * parameter
	 * 
	 * @param id
	 *            card type id
	 */
	void deleteTypeById(Integer id);
}