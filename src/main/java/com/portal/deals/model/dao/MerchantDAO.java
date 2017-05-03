package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Merchant;

/**
 * This interface will provide the methods to manipulate MERCHANT entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface MerchantDAO {

	/**
	 * This method will return list of all the MERCHANT entity from the database
	 * 
	 * @return List of MERCHANT entity
	 */
	List<Merchant> listAllMerchants();

	/**
	 * This method will return the Merchant based on the Merchant id passed.
	 * 
	 * @param id
	 *            merchant id
	 * @return MERCHANT Category entity
	 */
	Merchant getMerchantById(Integer id);

	/**
	 * This method will save the MERCHANT entity to the database
	 * 
	 * @param merchant
	 */
	void saveMerchant(Merchant merchant);

	/**
	 * This method will update the MERCHANT entity in the database
	 * 
	 * @param merchant
	 */
	void updateMerchant(Merchant merchant);

	/**
	 * This method will delete the merchant passed as parameter
	 * 
	 * @param merchant
	 *            MERCHANT entity object to be deleted
	 */
	void deleteMerchant(Merchant merchant);

	/**
	 * This method will delete the merchant w.r.t the merchant id passed as parameter
	 * 
	 * @param id
	 *            merchant id
	 */
	void deleteMerchantById(Integer id);
}