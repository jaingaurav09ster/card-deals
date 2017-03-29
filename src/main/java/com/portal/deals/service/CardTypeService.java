package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.CardType;

public interface CardTypeService {

	List<CardType> listAllCardTypes();

	CardType getCardTypeById(Integer id);

	void deleteCardTypeById(Integer id);

	void saveCardType(CardType cardType);

	void updateCardType(CardType cardType);

	void deleteCardType(CardType cardType);
}