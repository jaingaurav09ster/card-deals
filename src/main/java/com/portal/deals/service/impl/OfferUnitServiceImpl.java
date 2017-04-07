package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.OfferUnit;
import com.portal.deals.model.dao.OfferUnitDAO;
import com.portal.deals.service.OfferUnitService;

@Service("OfferUnitService")
@Transactional
public class OfferUnitServiceImpl implements OfferUnitService {

	@Autowired
	private OfferUnitDAO offerUnitDAO;

	@Override
	public List<OfferUnit> listAllOfferUnits() {
		return offerUnitDAO.listAllOfferUnits();
	}

	@Override
	public OfferUnit getOfferUnitById(Integer id) {
		return offerUnitDAO.getOfferUnitById(id);
	}

	@Override
	public void deleteOfferUnitById(Integer id) {
		offerUnitDAO.deleteOfferUnitById(id);
	}

	@Override
	public void saveOfferUnit(OfferUnit offerUnit) {
		offerUnitDAO.saveOfferUnit(offerUnit);
	}

	@Override
	public void updateOfferUnit(OfferUnit offerUnit) {
		OfferUnit entity = offerUnitDAO.getOfferUnitById(offerUnit.getId());
		if (entity != null) {
			entity.setDescription(offerUnit.getDescription());
			entity.setTitle(offerUnit.getTitle());
		}
	}

	@Override
	public void deleteOfferUnit(OfferUnit offerUnit) {
		offerUnitDAO.deleteOfferUnit(offerUnit);
	}
}