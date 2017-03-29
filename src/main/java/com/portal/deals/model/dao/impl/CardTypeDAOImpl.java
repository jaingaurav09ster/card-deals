package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.CardType;
import com.portal.deals.model.dao.CardTypeDAO;

/**
 * This class provides implementation to <code>CardTypeDAO</code> interface. It
 * will access the Database and it will perform CRUD operations on CARD_Type
 * table, this class will be accessible through CardTypeService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("CardTypeDAO")
public class CardTypeDAOImpl extends AbstractDao<Integer, CardType> implements CardTypeDAO {

	@Override
	public void saveType(CardType CardType) {
		this.persist(CardType);
	}

	@Override
	public void updateType(CardType CardType) {
		this.update(CardType);
	}

	@Override
	public void deleteType(CardType CardType) {
		this.delete(CardType);
	}

	@Override
	public List<CardType> listAllTypes() {
		List<CardType> list = this.loadAll();
		return (List<CardType>) list;
	}

	@Override
	public CardType getTypeById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteTypeById(Integer id) {
		this.deleteById(id);
	}
}