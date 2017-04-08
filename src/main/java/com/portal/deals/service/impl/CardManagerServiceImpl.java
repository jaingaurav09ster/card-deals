package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Card;
import com.portal.deals.model.dao.CardDetailsDAO;
import com.portal.deals.service.CardManagerService;

@Service("cardServiceManager")
@Transactional
public class CardManagerServiceImpl implements CardManagerService {

	@Autowired
	private CardDetailsDAO cardDao;

	@Override
	public List<Card> searchCard(String query) {
		return cardDao.searchCard(query);
	}

	@Override
	public List<Card> listAllCards() {
		return cardDao.listAllCards();
	}

	@Override
	public Card getCardById(Integer id) {
		return cardDao.getCardById(id);
	}

	@Override
	public void deleteCardById(Integer id) {
		cardDao.deleteCardById(id);
	}

	@Override
	public void saveCard(Card card) {
		cardDao.saveCard(card);
	}

	@Override
	public void updateCard(Card card) {
		Card entity = cardDao.getCardById(card.getId());
		if (entity != null) {
			entity.setTitle(card.getTitle());
			entity.setDescription(card.getDescription());
			entity.setLaunchDate(card.getLaunchDate());
			entity.setImage(card.getImage());
			entity.setImagePath(card.getImagePath());
			entity.setRank(card.getRank());
			entity.setBank(card.getBank());
			entity.setCardCategory(card.getCardCategory());
			entity.setCardType(card.getCardType());
			entity.setCategories(card.getCategories());
		}
	}

	@Override
	public void deleteCard(Card card) {
		cardDao.deleteCard(card);
	}

	@Override
	public String getCardName(Integer id) {
		return cardDao.getCardName(id);
	}
}