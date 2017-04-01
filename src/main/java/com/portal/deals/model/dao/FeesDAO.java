package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Fees;

/**
 * This interface will provide the methods to manipulate FEES entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface FeesDAO {

	/**
	 * This method will return list of all the FEES entity from the database
	 * 
	 * @return List of FEES entity
	 */
	List<Fees> listAllFees();

	/**
	 * This method will return list of all the FEES entity for a CARD from the
	 * database
	 * 
	 * @return List of FEES entity
	 */
	List<Fees> getFeesByCardId(Integer cardId);

	/**
	 * This method will return the Fees based on the Fees id passed.
	 * 
	 * @param id
	 *            fees id
	 * @return FEES entity
	 */
	Fees getFeesById(Integer id);

	/**
	 * This method will save the FEES entity to the database
	 * 
	 * @param fees
	 */
	void saveFees(Fees fees);

	/**
	 * This method will update the FEES entity in the database
	 * 
	 * @param fees
	 */
	void updateFees(Fees fees);

	/**
	 * This method will delete the fees passed as parameter
	 * 
	 * @param fees
	 *            FEES entity object to be deleted
	 */
	void deleteFees(Fees fees);

	/**
	 * This method will delete the fees w.r.t the fees id passed as parameter
	 * 
	 * @param id
	 *            fees id
	 */
	void deleteFeesById(Integer id);
}