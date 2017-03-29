package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.CardType;
import com.portal.deals.model.dao.CardTypeDAO;
import com.portal.deals.service.CardTypeService;

@Service("cardTypeService")
@Transactional
public class CardTypeServiceImpl implements CardTypeService {

	@Autowired
	private CardTypeDAO cardTypeDAO;

	@Override
	public List<CardType> listAllCardTypes() {
		return cardTypeDAO.listAllTypes();
	}

	@Override
	public CardType getCardTypeById(Integer id) {
		return cardTypeDAO.getTypeById(id);
	}

	@Override
	public void deleteCardTypeById(Integer id) {
		cardTypeDAO.deleteTypeById(id);
	}

	@Override
	public void saveCardType(CardType cardType) {
		cardTypeDAO.saveType(cardType);
	}

	@Override
	public void updateCardType(CardType cardType) {
		CardType entity = cardTypeDAO.getTypeById(cardType.getId());
		if (entity != null) {
			entity.setDescription(cardType.getDescription());
			entity.setName(cardType.getName());
		}
	}

	@Override
	public void deleteCardType(CardType cardType) {
		cardTypeDAO.deleteType(cardType);
	}
}