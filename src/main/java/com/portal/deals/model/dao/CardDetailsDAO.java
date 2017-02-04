package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Card;

public interface CardDetailsDAO {

	List<Card> searchCard(String query);

	List<Card> listAllCards();

	Card getCardById(Integer id);

	void deleteCardById(Integer id);

	void saveCard(Card card);

	void updateCard(Card card);

	void deleteCard(Card card);
}