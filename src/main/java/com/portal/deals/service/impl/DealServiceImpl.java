package com.portal.deals.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Deal;
import com.portal.deals.model.SearchDealResponse;
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
			entity.setEndDate(deal.getEndDate());
			entity.setStartDate(deal.getStartDate());
			entity.setCouponCode(deal.getCouponCode());
			entity.setMerchant(deal.getMerchant());
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

	@SuppressWarnings("unchecked")
	@Override
	public SearchDealResponse searchDeal(Map<String, String> criterias) {

		/** Create HQL query */
		String query = createQueryString(criterias);

		/** Calculate limit and begin index */
		int pageIndex = 0;
		int limit = 12;
		if (!StringUtils.isEmpty(criterias.get(CommonConstants.PAGE_INDEX))) {
			pageIndex = Integer.parseInt(criterias.get(CommonConstants.PAGE_INDEX));
		}
		if (!StringUtils.isEmpty(criterias.get(CommonConstants.LIMIT))) {
			limit = Integer.parseInt(criterias.get(CommonConstants.LIMIT));
		}
		int begin = ((pageIndex) * limit);

		/** Get the Cards from DB */
		SearchDealResponse searchResponse = new SearchDealResponse();
		List<Object> resultList = dealDAO.searchDeal(query, begin, limit);
		if (resultList != null && resultList.size() > 0) {
			if (resultList.get(0) instanceof Deal) {
				searchResponse.setDeals((List<Deal>) (List<?>) resultList);
			} else {
				List<Deal> deals = new ArrayList<>();
				for (Object result : resultList) {
					Object[] arr = (Object[]) result;
					Deal deal = (Deal) arr[0];
					deals.add(deal);
				}
				searchResponse.setDeals(deals);
			}
		}
		String queryForCount = null;
		if (query.contains("distinct d")) {
			queryForCount = query.replace("distinct d", "count(distinct d)");
		} else if (query.indexOf(CommonConstants.WHERE) == -1) {
			queryForCount = "select count(*) " + query;
		} else {
			queryForCount = "select count(*) from Deal d " + query.substring(query.indexOf(CommonConstants.WHERE));
		}
		/** Get the cards total count */
		int count = dealDAO.getTotalCount(queryForCount);
		searchResponse.setCount(count);

		return searchResponse;
	}

	/**
	 * This method will create Query String
	 * 
	 * @param criterias
	 * @param orderBy
	 * @param order
	 * @return
	 */
	private String createQueryString(Map<String, String> criterias) {
		StringBuffer queryString = null;

		String bankValue = criterias.get(CommonConstants.BANK);
		String categoryValue = criterias.get(CommonConstants.CATEGORY);
		String cardCategoryValue = criterias.get(CommonConstants.CARD_CATEGORY);
		String titleValue = criterias.get(CommonConstants.TITLE);
		String cardValue = criterias.get(CommonConstants.CARD);
		String merchantValue = criterias.get(CommonConstants.MERCHANT);

		if (!StringUtils.isEmpty(categoryValue)) {
			queryString = new StringBuffer("select distinct d from Deal d join d.categories cat ");
			if (!queryString.toString().contains("where")) {
				queryString.append("where cat.id in (" + categoryValue + ")");
			} else {
				queryString.append(" and cat.id in (" + categoryValue + ")");
			}
		} else {
			queryString = new StringBuffer("from Deal d ");
		}

		if (!StringUtils.isEmpty(bankValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where d.card.bank.id in (" + bankValue + ")");
			} else {
				queryString.append(" and d.card.bank.id in (" + bankValue + ")");
			}
		}
		if (!StringUtils.isEmpty(titleValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where d.card.title like '%" + titleValue + "%'");
			} else {
				queryString.append(" and d.card.title like '%" + titleValue + "%'");
			}
		}
		if (!StringUtils.isEmpty(cardCategoryValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where d.card.cardCategory.id in (" + cardCategoryValue + ")");
			} else {
				queryString.append(" and d.card.cardCategory.id in (" + cardCategoryValue + ")");
			}
		}
		if (!StringUtils.isEmpty(cardValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where d.card.id in (" + cardValue + ")");
			} else {
				queryString.append(" and d.card.id in (" + cardValue + ")");
			}
		}
		if (!StringUtils.isEmpty(merchantValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where d.merchant.id in (" + merchantValue + ")");
			} else {
				queryString.append(" and d.merchant.id in (" + merchantValue + ")");
			}
		}

		String order = criterias.get(CommonConstants.ORDER);
		String orderBy = criterias.get(CommonConstants.ORDER_BY);
		if (!StringUtils.isEmpty(orderBy) && !StringUtils.isEmpty(order)) {
			if (order.equalsIgnoreCase("desc")) {
				queryString.append(" order by " + orderBy + " desc");
			} else {
				queryString.append(" order by " + orderBy + " asc");
			}
		} else {
			queryString.append(" order by d.lastModifiedDate desc");
		}
		return queryString.toString();
	}

	@Override
	public Map<Integer, Long> getCountByCategory() {
		return dealDAO.getCountByCategory();
	}

	@Override
	public Map<Integer, Long> getCountByCardCategory() {
		return dealDAO.getCountByCardCategory();
	}

	@Override
	public Map<Integer, Long> getCountByBank() {
		return dealDAO.getCountByBank();
	}

	@Override
	public Map<Integer, Long> getCountByCards() {
		return dealDAO.getCountByCards();
	}

	@Override
	public Map<Integer, Long> getCountByMerchants() {
		return dealDAO.getCountByMerchant();
	}
}