package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Bank;

/**
 * This interface will provide the methods to manipulate BANK entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface BankDAO {

	/**
	 * This method will return list of all the BANK entity from the database
	 * 
	 * @return List of BANK entity
	 */
	List<Bank> listAllBanks();

	/**
	 * This method will return the Bank based on the Bank id passed.
	 * 
	 * @param id
	 *            bank id
	 * @return BANK Category entity
	 */
	Bank getBankById(Integer id);

	/**
	 * This method will save the BANK entity to the database
	 * 
	 * @param bank
	 */
	void saveBank(Bank bank);

	/**
	 * This method will update the BANK entity in the database
	 * 
	 * @param bank
	 */
	void updateBank(Bank bank);

	/**
	 * This method will delete the bank passed as parameter
	 * 
	 * @param bank
	 *            BANK entity object to be deleted
	 */
	void deleteBank(Bank bank);

	/**
	 * This method will delete the bank w.r.t the bank id passed as parameter
	 * 
	 * @param id
	 *            bank id
	 */
	void deleteBankById(Integer id);
}