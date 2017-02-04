package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.deals.model.Card;
import com.portal.deals.model.dao.CardDetailsDAO;
import com.portal.deals.service.CardServiceManager;

@Service("cardServiceManager")
public class CardServiceManagerImpl implements CardServiceManager {

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
		cardDao.updateCard(card);
	}

	@Override
	public void deleteCard(Card card) {
		cardDao.deleteCard(card);
	}
}