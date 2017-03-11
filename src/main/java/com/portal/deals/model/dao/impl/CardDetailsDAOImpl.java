package com.portal.deals.model.dao.impl;

import java.util.List;

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
		String namedQuery = "from Card c where c.title like '%" + query + "%' OR c.description like '%" + query
				+ "%' OR c.bank.name like '%" + query + "%'";
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
	public void deleteCardById(Integer id) {
		this.deleteById(id);
	}
}