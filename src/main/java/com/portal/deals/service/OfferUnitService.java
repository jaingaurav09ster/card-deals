package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.OfferUnit;

public interface OfferUnitService {

	List<OfferUnit> listAllOfferUnits();

	OfferUnit getOfferUnitById(Integer id);

	void deleteOfferUnitById(Integer id);

	void saveOfferUnit(OfferUnit offerUnit);

	void updateOfferUnit(OfferUnit offerUnit);

	void deleteOfferUnit(OfferUnit offerUnit);
}