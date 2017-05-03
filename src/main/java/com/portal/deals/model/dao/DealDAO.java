package com.portal.deals.model.dao;

import java.util.List;
import java.util.Map;

import com.portal.deals.model.Deal;

/**
 * This interface will provide the methods to manipulate DEAL entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface DealDAO {

	/**
	 * This method will return list of all the DEAL entity from the database
	 * 
	 * @return List of DEAL entity
	 */
	List<Deal> listAllDeals();

	/**
	 * This method will return list of all the DEAL entity for a CARD from the
	 * database
	 * 
	 * @return List of DEAL entity
	 */
	List<Deal> getDealsByCardId(Integer cardId);

	/**
	 * This method will return the Deal based on the Deal id passed.
	 * 
	 * @param id
	 *            deal id
	 * @return DEAL entity
	 */
	Deal getDealById(Integer id);

	/**
	 * This method will save the DEAL entity to the database
	 * 
	 * @param deal
	 */
	void saveDeal(Deal deal);

	/**
	 * This method will update the DEAL entity in the database
	 * 
	 * @param deal
	 */
	void updateDeal(Deal deal);

	/**
	 * This method will delete the deal passed as parameter
	 * 
	 * @param deal
	 *            DEAL entity object to be deleted
	 */
	void deleteDeal(Deal deal);

	/**
	 * This method will delete the deal w.r.t the deal id passed as parameter
	 * 
	 * @param id
	 *            deal id
	 */
	void deleteDealById(Integer id);

	int getTotalCount(String namedQuery);

	/**
	 * This method will get the DEAL entity list from the database, based on the
	 * query search parameter.
	 * 
	 * @param query
	 *            Query String
	 * @return List of Card Entity
	 */
	List<Object> searchDeal(String query, int begin, int limit);
	
	Map<Integer, Long> getCountByCategory();

	Map<Integer, Long> getCountByCardCategory();

	Map<Integer, Long> getCountByBank();
	
	Map<Integer, Long> getCountByMerchant();
	
	Map<Integer, Long> getCountByCards();

}