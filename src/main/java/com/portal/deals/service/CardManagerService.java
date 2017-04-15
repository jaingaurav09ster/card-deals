package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Card;

public interface CardManagerService {

	List<Object[]> searchCard(String query, int begin, int end);

	List<Card> listAllCards();

	Card getCardById(Integer id);
	
	String getCardName(Integer id);

	void deleteCardById(Integer id);

	void saveCard(Card card);

	void updateCard(Card card, boolean bankUser);

	void deleteCard(Card card);

	Card getCardWithDetailsById(Integer id);

	List<Card> listAllCardsByBank(Integer bankId);
	
	List<Object> getCardCountForBanks();
}