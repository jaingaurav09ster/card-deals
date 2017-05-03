package com.portal.deals.model.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Deal;
import com.portal.deals.model.dao.DealDAO;

/**
 * This class provides implementation to <code>DealDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on DEAL table, this
 * class will be accessible through DealService
 * 
 * @author Gaurav Jain
 *
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("DealDAO")
public class DealDAOImpl extends AbstractDao<Integer, Deal> implements DealDAO {

	@Override
	public void saveDeal(Deal Deal) {
		this.persist(Deal);
	}

	@Override
	public void updateDeal(Deal Deal) {
		this.update(Deal);
	}

	@Override
	public void deleteDeal(Deal Deal) {
		this.delete(Deal);
	}

	@Override
	public List<Deal> listAllDeals() {
		List<Deal> list = this.loadAll();
		return (List<Deal>) list;
	}

	@Override
	public Deal getDealById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteDealById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Deal> getDealsByCardId(Integer cardId) {
		List<Deal> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Deal>) list;

	}

	@Override
	public List<Object> searchDeal(String namedQuery, int begin, int limit) {
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
				.createQuery("SELECT d.card.bank.id, COUNT(d.card.bank.id) FROM Deal d group by d.card.bank.id").list();
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
				.createQuery("SELECT cat.id, COUNT(cat.id) FROM Deal d join d.categories cat group by cat.id").list();
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
						"SELECT d.card.cardCategory.id, COUNT(d.card.cardCategory.id) FROM Deal d group by d.card.cardCategory.id")
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
	public Map<Integer, Long> getCountByCards() {
		List<Object> list = getSession()
				.createQuery(
						"SELECT d.card.id, COUNT(d.card.id) FROM Deal d group by d.card.id")
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
	public Map<Integer, Long> getCountByMerchant() {
		List<Object> list = getSession()
				.createQuery(
						"SELECT d.merchant.id, COUNT(d.merchant.id) FROM Deal d group by d.merchant.id")
				.list();
		Map<Integer, Long> statusMap = new HashMap<Integer, Long>();
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			statusMap.put((Integer) obj[0], (Long) obj[1]);
		}
		return statusMap;
	}
}