package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.OfferType;

public interface OfferTypeService {

	List<OfferType> listAllOfferTypes();

	OfferType getOfferTypeById(Integer id);

	void deleteOfferTypeById(Integer id);

	void saveOfferType(OfferType offerType);

	void updateOfferType(OfferType offerType);

	void deleteOfferType(OfferType offerType);
}