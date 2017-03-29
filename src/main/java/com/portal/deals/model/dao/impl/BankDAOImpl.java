package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Bank;
import com.portal.deals.model.dao.BankDAO;

/**
 * This class provides implementation to <code>BankDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on BANK table,
 * this class will be accessible through BankService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("BankDAO")
public class BankDAOImpl extends AbstractDao<Integer, Bank> implements BankDAO {

	@Override
	public void saveBank(Bank Bank) {
		this.persist(Bank);
	}

	@Override
	public void updateBank(Bank Bank) {
		this.update(Bank);
	}

	@Override
	public void deleteBank(Bank Bank) {
		this.delete(Bank);
	}

	@Override
	public List<Bank> listAllBanks() {
		List<Bank> list = this.loadAll();
		return (List<Bank>) list;
	}

	@Override
	public Bank getBankById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteBankById(Integer id) {
		this.deleteById(id);
	}
}