package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Fees;
import com.portal.deals.model.dao.FeesDAO;

/**
 * This class provides implementation to <code>FeesDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on FEES table, this
 * class will be accessible through FeesService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("FeesDAO")
public class FeesDAOImpl extends AbstractDao<Integer, Fees> implements FeesDAO {

	@Override
	public void saveFees(Fees Fees) {
		this.persist(Fees);
	}

	@Override
	public void updateFees(Fees Fees) {
		this.update(Fees);
	}

	@Override
	public void deleteFees(Fees Fees) {
		this.delete(Fees);
	}

	@Override
	public List<Fees> listAllFees() {
		List<Fees> list = this.loadAll();
		return (List<Fees>) list;
	}

	@Override
	public Fees getFeesById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteFeesById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Fees> getFeesByCardId(Integer cardId) {
		List<Fees> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Fees>) list;

	}
}