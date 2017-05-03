package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Merchant;
import com.portal.deals.model.dao.MerchantDAO;

/**
 * This class provides implementation to <code>MerchantDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on MERCHANT table,
 * this class will be accessible through MerchantService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("MerchantDAO")
public class MerchantDAOImpl extends AbstractDao<Integer, Merchant> implements MerchantDAO {

	@Override
	public void saveMerchant(Merchant Merchant) {
		this.persist(Merchant);
	}

	@Override
	public void updateMerchant(Merchant Merchant) {
		this.update(Merchant);
	}

	@Override
	public void deleteMerchant(Merchant Merchant) {
		this.delete(Merchant);
	}

	@Override
	public List<Merchant> listAllMerchants() {
		List<Merchant> list = this.loadAll();
		return (List<Merchant>) list;
	}

	@Override
	public Merchant getMerchantById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteMerchantById(Integer id) {
		this.deleteById(id);
	}
}