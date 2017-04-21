package com.portal.deals.model.dao;

import java.util.List;
import java.util.Map;

import com.portal.deals.model.Card;

/**
 * This interface will provide the methods to manipulate CARD entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface CardDetailsDAO {

	/**
	 * This method will get the CARD entity list from the database, based on the
	 * query search parameter.
	 * 
	 * @param query
	 *            Query String
	 * @return List of Card Entity
	 */
	List<Object> searchCard(String query, int begin, int limit);

	/**
	 * This method will return list of all the Card entity from the database
	 * 
	 * @return List of Card entity
	 */
	List<Card> listAllCards();

	/**
	 * This method will return the Card based on the card id passed.
	 * 
	 * @param id
	 *            card id
	 * @return Card entity
	 */
	Card getCardById(Integer id);

	/**
	 * This method will return the Card name based on the card id passed.
	 * 
	 * @param id
	 *            card id
	 * @return Card entity
	 */
	String getCardName(Integer id);

	/**
	 * This method will save the Card entity to the database
	 * 
	 * @param card
	 */
	void saveCard(Card card);

	/**
	 * This method will update the card entity in the database
	 * 
	 * @param card
	 */
	void updateCard(Card card);

	/**
	 * This method will delete the card passed as parameter
	 * 
	 * @param card
	 *            card entity object to be deleted
	 */
	void deleteCard(Card card);

	/**
	 * This method will delete the card w.r.t the card id passed as parameter
	 * 
	 * @param id
	 *            card id
	 */
	void deleteCardById(Integer id);

	/**
	 * THis will get the details of card as well
	 * 
	 * @param id
	 * @return
	 */
	Card getCardWithDetailsById(Integer id);

	/**
	 * This will get the list of card for a BANK
	 * 
	 * @param bankId
	 * @return
	 */
	List<Card> listAllCardsByBank(Integer bankId);

	Map<Integer, Long> getCountByCategory();

	Map<Integer, Long> getCountByCardCategory();

	Map<Integer, Long> getCountByBank();

	int getTotalCount(String namedQuery);
}