package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Deal;
import com.portal.deals.model.dao.DealDAO;

/**
 * This class provides implementation to <code>DealDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on DEAL table, this
 * class will be accessible through DealService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("DealDAO")
public class DealDAOImpl extends AbstractDao<Integer, Deal> implements DealDAO {

	@Override
	public void saveDeal(Deal Deal) {
		this.persist(Deal);
	}

	@Override
	public void updateDeal(Deal Deal) {
		this.update(Deal);
	}

	@Override
	public void deleteDeal(Deal Deal) {
		this.delete(Deal);
	}

	@Override
	public List<Deal> listAllDeals() {
		List<Deal> list = this.loadAll();
		return (List<Deal>) list;
	}

	@Override
	public Deal getDealById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteDealById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Deal> getDealsByCardId(Integer cardId) {
		List<Deal> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Deal>) list;

	}
}