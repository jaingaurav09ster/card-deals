package com.portal.deals.service;

import java.util.List;
import java.util.Map;

import com.portal.deals.model.Card;
import com.portal.deals.model.SearchCardResponse;

public interface CardManagerService {

	SearchCardResponse searchCard(Map<String, String> criterias);

	List<Card> listAllCards();

	Card getCardById(Integer id);

	String getCardName(Integer id);

	void deleteCardById(Integer id);

	void saveCard(Card card);

	void updateCard(Card card, boolean bankUser);

	void deleteCard(Card card);

	Card getCardWithDetailsById(Integer id);

	List<Card> listAllCardsByBank(Integer bankId);
	
	List<Card> listAllCardsByTitle(String title);

	Map<Integer, Long> getCountByCategory();

	Map<Integer, Long> getCountByCardCategory();

	Map<Integer, Long> getCountByBank();

}