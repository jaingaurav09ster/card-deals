package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Deal;
import com.portal.deals.model.dao.DealDAO;
import com.portal.deals.service.DealService;

@Service("DealService")
@Transactional
public class DealServiceImpl implements DealService {

	@Autowired
	private DealDAO dealDAO;

	@Override
	public List<Deal> listAllDeals() {
		return dealDAO.listAllDeals();
	}

	@Override
	public Deal getDealById(Integer id) {
		return dealDAO.getDealById(id);
	}

	@Override
	public void deleteDealById(Integer id) {
		dealDAO.deleteDealById(id);
	}

	@Override
	public void saveDeal(Deal deal) {
		dealDAO.saveDeal(deal);
	}

	@Override
	public void updateDeal(Deal deal) {
		Deal entity = dealDAO.getDealById(deal.getId());
		if (entity != null) {
			entity.setDescription(deal.getDescription());
			entity.setTitle(deal.getTitle());
			entity.setRank(deal.getRank());
			entity.setCategories(deal.getCategories());
			entity.setDealValue(deal.getDealValue());
			entity.setMaxValue(deal.getMaxValue());
			entity.setOfferType(deal.getOfferType());
			entity.setValueUnit(deal.getValueUnit());
			entity.setMaxValueUnit(deal.getMaxValueUnit());
		}
	}

	@Override
	public void deleteDeal(Deal deal) {
		dealDAO.deleteDeal(deal);
	}

	@Override
	public List<Deal> getDealsByCardId(Integer cardId) {
		return dealDAO.getDealsByCardId(cardId);
	}
}