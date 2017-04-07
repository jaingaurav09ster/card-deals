package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.OfferType;

/**
 * This interface will provide the methods to manipulate OFFER TYPE entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface OfferTypeDAO {

	/**
	 * This method will return list of all the OFFER TYPE entity from the database
	 * 
	 * @return List of OFFER TYPE entity
	 */
	List<OfferType> listAllOfferTypes();

	/**
	 * This method will return the OfferType based on the OfferType id passed.
	 * 
	 * @param id
	 *            offerType id
	 * @return OFFER TYPE Category entity
	 */
	OfferType getOfferTypeById(Integer id);

	/**
	 * This method will save the OFFER TYPE entity to the database
	 * 
	 * @param offerType
	 */
	void saveOfferType(OfferType offerType);

	/**
	 * This method will update the OFFER TYPE entity in the database
	 * 
	 * @param offerType
	 */
	void updateOfferType(OfferType offerType);

	/**
	 * This method will delete the offerType passed as parameter
	 * 
	 * @param offerType
	 *            OFFER TYPE entity object to be deleted
	 */
	void deleteOfferType(OfferType offerType);

	/**
	 * This method will delete the offerType w.r.t the offerType id passed as parameter
	 * 
	 * @param id
	 *            offerType id
	 */
	void deleteOfferTypeById(Integer id);
}