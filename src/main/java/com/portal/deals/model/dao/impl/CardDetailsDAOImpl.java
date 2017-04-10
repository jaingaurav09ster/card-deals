package com.portal.deals.model.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Card;
import com.portal.deals.model.dao.CardDetailsDAO;

/**
 * This class provides implementation to <code>CardDetailsDAO</code> interface.
 * It will access the Database and it will perform CRUD operations on
 * CARD_DETAILS table, this class will be accessible through CardServiceManager
 * 
 * @author Gaurav Jain
 *
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("cardDetailsDAO")
public class CardDetailsDAOImpl extends AbstractDao<Integer, Card> implements CardDetailsDAO {

	@Override
	public void saveCard(Card card) {
		this.persist(card);
	}

	@Override
	public void updateCard(Card card) {
		this.update(card);
	}

	@Override
	public void deleteCard(Card card) {
		this.delete(card);
	}

	@Override
	public List<Card> searchCard(String query) {
		String namedQuery = "from Card c where c.title like '%" + query + "%'";
		List<Card> list = getSession().createQuery(namedQuery).list();
		return (List<Card>) list;
	}

	@Override
	public List<Card> listAllCards() {
		List<Card> list = this.loadAll();
		return (List<Card>) list;
	}

	@Override
	public Card getCardById(Integer id) {
		return this.get(id);
	}

	@Override
	public Card getCardWithDetailsById(Integer id) {
		Card card = this.get(id);
		Hibernate.initialize(card.getDeals());
		Hibernate.initialize(card.getRewards());
		Hibernate.initialize(card.getRating());
		Hibernate.initialize(card.getFeatures());
		Hibernate.initialize(card.getJoiningPerks());
		Hibernate.initialize(card.getDocuments());
		Hibernate.initialize(card.getFees());
		return card;
	}

	@Override
	public void deleteCardById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public String getCardName(Integer id) {
		String name = (String) getSession().createQuery("select card.title from Card card where card.id = :id")
				.setInteger("id", id).uniqueResult();
		return name;
	}
}