package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.OfferUnit;
import com.portal.deals.model.dao.OfferUnitDAO;

/**
 * This class provides implementation to <code>OfferUnitDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on BANK table,
 * this class will be accessible through OfferUnitService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("OfferUnitDAO")
public class OfferUnitDAOImpl extends AbstractDao<Integer, OfferUnit> implements OfferUnitDAO {

	@Override
	public void saveOfferUnit(OfferUnit OfferUnit) {
		this.persist(OfferUnit);
	}

	@Override
	public void updateOfferUnit(OfferUnit OfferUnit) {
		this.update(OfferUnit);
	}

	@Override
	public void deleteOfferUnit(OfferUnit OfferUnit) {
		this.delete(OfferUnit);
	}

	@Override
	public List<OfferUnit> listAllOfferUnits() {
		List<OfferUnit> list = this.loadAll();
		return (List<OfferUnit>) list;
	}

	@Override
	public OfferUnit getOfferUnitById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteOfferUnitById(Integer id) {
		this.deleteById(id);
	}
}