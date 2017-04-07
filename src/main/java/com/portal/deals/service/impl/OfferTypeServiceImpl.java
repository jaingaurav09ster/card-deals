package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.OfferType;
import com.portal.deals.model.dao.OfferTypeDAO;
import com.portal.deals.service.OfferTypeService;

@Service("OfferTypeService")
@Transactional
public class OfferTypeServiceImpl implements OfferTypeService {

	@Autowired
	private OfferTypeDAO offerTypeDAO;

	@Override
	public List<OfferType> listAllOfferTypes() {
		return offerTypeDAO.listAllOfferTypes();
	}

	@Override
	public OfferType getOfferTypeById(Integer id) {
		return offerTypeDAO.getOfferTypeById(id);
	}

	@Override
	public void deleteOfferTypeById(Integer id) {
		offerTypeDAO.deleteOfferTypeById(id);
	}

	@Override
	public void saveOfferType(OfferType offerType) {
		offerTypeDAO.saveOfferType(offerType);
	}

	@Override
	public void updateOfferType(OfferType offerType) {
		OfferType entity = offerTypeDAO.getOfferTypeById(offerType.getId());
		if (entity != null) {
			entity.setDescription(offerType.getDescription());
			entity.setTitle(offerType.getTitle());
		}
	}

	@Override
	public void deleteOfferType(OfferType offerType) {
		offerTypeDAO.deleteOfferType(offerType);
	}
}