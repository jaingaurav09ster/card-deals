package com.portal.deals.model;

import java.util.List;

public class SearchCardResponse {

	private int count;
	private List<Card> cards;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards
	 *            the cards to set
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}