package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.OfferType;
import com.portal.deals.model.dao.OfferTypeDAO;

/**
 * This class provides implementation to <code>OfferTypeDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on BANK table,
 * this class will be accessible through OfferTypeService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("OfferTypeDAO")
public class OfferTypeDAOImpl extends AbstractDao<Integer, OfferType> implements OfferTypeDAO {

	@Override
	public void saveOfferType(OfferType OfferType) {
		this.persist(OfferType);
	}

	@Override
	public void updateOfferType(OfferType OfferType) {
		this.update(OfferType);
	}

	@Override
	public void deleteOfferType(OfferType OfferType) {
		this.delete(OfferType);
	}

	@Override
	public List<OfferType> listAllOfferTypes() {
		List<OfferType> list = this.loadAll();
		return (List<OfferType>) list;
	}

	@Override
	public OfferType getOfferTypeById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteOfferTypeById(Integer id) {
		this.deleteById(id);
	}
}