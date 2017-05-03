package com.portal.deals.model.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public List<Object> searchCard(String namedQuery, int begin, int limit) {
		List<Object> list = getSession().createQuery(namedQuery).setFirstResult(begin).setMaxResults(limit).list();
		return (List<Object>) list;
	}

	@Override
	public int getTotalCount(String namedQuery) {
		Long count = (Long) getSession().createQuery(namedQuery).uniqueResult();
		return count.intValue();
	}

	@Override
	public Map<Integer, Long> getCountByBank() {
		List<Object> list = getSession()
				.createQuery("SELECT c.bank.id, COUNT(c.bank.id) FROM Card c group by c.bank.id").list();
		Map<Integer, Long> statusMap = new HashMap<Integer, Long>();
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			statusMap.put((Integer) obj[0], (Long) obj[1]);
		}
		return statusMap;
	}

	@Override
	public Map<Integer, Long> getCountByCategory() {
		List<Object> list = getSession()
				.createQuery("SELECT cat.id, COUNT(cat.id) FROM Card c join c.categories cat group by cat.id").list();
		Map<Integer, Long> statusMap = new HashMap<Integer, Long>();
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			statusMap.put((Integer) obj[0], (Long) obj[1]);
		}
		return statusMap;
	}

	@Override
	public Map<Integer, Long> getCountByCardCategory() {
		List<Object> list = getSession()
				.createQuery(
						"SELECT c.cardCategory.id, COUNT(c.cardCategory.id) FROM Card c group by c.cardCategory.id")
				.list();
		Map<Integer, Long> statusMap = new HashMap<Integer, Long>();
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			statusMap.put((Integer) obj[0], (Long) obj[1]);
		}
		return statusMap;
	}

	@Override
	public List<Card> listAllCards() {
		List<Card> list = this.loadAll();
		return (List<Card>) list;
	}

	@Override
	public List<Card> listAllCardsByBank(Integer bankId) {
		List<Card> list = this.findAllByChildProperty("bank", "id", bankId);
		return list;
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

	@Override
	public List<Card> listAllCardsByTitle(String title) {
		List<Card> list = getSession().createQuery("select c from Card c where c.title like '%" + title + "%'")
				.list();
		return list;
	}
}