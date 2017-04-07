package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.OfferUnit;

/**
 * This interface will provide the methods to manipulate OFFER UNIT entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface OfferUnitDAO {

	/**
	 * This method will return list of all the OFFER UNIT entity from the database
	 * 
	 * @return List of OFFER UNIT entity
	 */
	List<OfferUnit> listAllOfferUnits();

	/**
	 * This method will return the OfferUnit based on the OfferUnit id passed.
	 * 
	 * @param id
	 *            offerUnit id
	 * @return OFFER UNIT Category entity
	 */
	OfferUnit getOfferUnitById(Integer id);

	/**
	 * This method will save the OFFER UNIT entity to the database
	 * 
	 * @param offerUnit
	 */
	void saveOfferUnit(OfferUnit offerUnit);

	/**
	 * This method will update the OFFER UNIT entity in the database
	 * 
	 * @param offerUnit
	 */
	void updateOfferUnit(OfferUnit offerUnit);

	/**
	 * This method will delete the offerUnit passed as parameter
	 * 
	 * @param offerUnit
	 *            OFFER UNIT entity object to be deleted
	 */
	void deleteOfferUnit(OfferUnit offerUnit);

	/**
	 * This method will delete the offerUnit w.r.t the offerUnit id passed as parameter
	 * 
	 * @param id
	 *            offerUnit id
	 */
	void deleteOfferUnitById(Integer id);
}