package com.portal.deals.service;

import java.util.List;
import java.util.Map;

import com.portal.deals.model.Deal;
import com.portal.deals.model.SearchDealResponse;

public interface DealService {

	List<Deal> listAllDeals();

	Deal getDealById(Integer id);

	void deleteDealById(Integer id);

	void saveDeal(Deal deal);

	void updateDeal(Deal deal);

	void deleteDeal(Deal deal);

	List<Deal> getDealsByCardId(Integer cardId);

	SearchDealResponse searchDeal(Map<String, String> criterias);
	
	Map<Integer, Long> getCountByCategory();
	
	Map<Integer, Long> getCountByCards();
	
	Map<Integer, Long> getCountByMerchants();

	Map<Integer, Long> getCountByCardCategory();

	Map<Integer, Long> getCountByBank();
}