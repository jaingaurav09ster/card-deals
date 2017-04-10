package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Card;

public interface CardManagerService {

	List<Card> searchCard(String query);

	List<Card> listAllCards();

	Card getCardById(Integer id);
	
	String getCardName(Integer id);

	void deleteCardById(Integer id);

	void saveCard(Card card);

	void updateCard(Card card);

	void deleteCard(Card card);

	Card getCardWithDetailsById(Integer id);
}