package com.portal.deals.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Card;
import com.portal.deals.model.SearchCardResponse;
import com.portal.deals.model.dao.CardDetailsDAO;
import com.portal.deals.service.CardManagerService;

@Service("cardServiceManager")
@Transactional
public class CardManagerServiceImpl implements CardManagerService {

	@Autowired
	private CardDetailsDAO cardDao;

	@SuppressWarnings("unchecked")
	@Override
	public SearchCardResponse searchCard(Map<String, String> criterias) {

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
		SearchCardResponse searchResponse = new SearchCardResponse();
		List<Object> resultList = cardDao.searchCard(query, begin, limit);
		if (resultList != null && resultList.size() > 0) {
			if (resultList.get(0) instanceof Card) {
				searchResponse.setCards((List<Card>) (List<?>) resultList);
			} else {
				List<Card> cards = new ArrayList<>();
				for (Object result : resultList) {
					Object[] arr = (Object[]) result;
					Card card = (Card) arr[0];
					cards.add(card);
				}
				searchResponse.setCards(cards);
			}
		}
		String queryForCount = null;
		if (query.contains("distinct c")) {
			queryForCount = query.replace("distinct c", "count(distinct c)");
		} else if (query.indexOf(CommonConstants.WHERE) == -1) {
			queryForCount = "select count(*) " + query;
		} else {
			queryForCount = "select count(*) from Card c " + query.substring(query.indexOf(CommonConstants.WHERE));
		}
		/** Get the cards total count */
		int count = cardDao.getTotalCount(queryForCount);
		searchResponse.setCount(count);

		return searchResponse;
	}

	@Override
	public List<Card> listAllCards() {
		return cardDao.listAllCards();
	}

	@Override
	public Card getCardById(Integer id) {
		return cardDao.getCardById(id);
	}

	@Override
	public void deleteCardById(Integer id) {
		cardDao.deleteCardById(id);
	}

	@Override
	public void saveCard(Card card) {
		cardDao.saveCard(card);
	}

	@Override
	public void updateCard(Card card, boolean bankUser) {
		Card entity = cardDao.getCardById(card.getId());
		if (entity != null) {
			entity.setTitle(card.getTitle());
			entity.setDescription(card.getDescription());
			entity.setLaunchDate(card.getLaunchDate());
			entity.setImage(card.getImage());
			entity.setImagePath(card.getImagePath());
			entity.setRank(card.getRank());
			if (!bankUser) {
				entity.setBank(card.getBank());
			}
			entity.setCardCategory(card.getCardCategory());
			entity.setCardType(card.getCardType());
			entity.setCategories(card.getCategories());
		}
	}

	@Override
	public void deleteCard(Card card) {
		cardDao.deleteCard(card);
	}

	@Override
	public String getCardName(Integer id) {
		return cardDao.getCardName(id);
	}

	@Override
	public Card getCardWithDetailsById(Integer id) {
		return cardDao.getCardWithDetailsById(id);
	}

	@Override
	public List<Card> listAllCardsByBank(Integer bankId) {
		return cardDao.listAllCardsByBank(bankId);
	}

	@Override
	public Map<Integer, Long> getCountByCategory() {
		return cardDao.getCountByCategory();
	}

	@Override
	public Map<Integer, Long> getCountByCardCategory() {
		return cardDao.getCountByCardCategory();
	}

	@Override
	public Map<Integer, Long> getCountByBank() {
		return cardDao.getCountByBank();
	}
	
	@Override
	public Map<Integer, Long> getCountByCardType() {
		return cardDao.getCountByCardType();
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
		String cardTypeValue = criterias.get(CommonConstants.CARD_TYPE);
		String categoryValue = criterias.get(CommonConstants.CATEGORY);
		String cardCategoryValue = criterias.get(CommonConstants.CARD_CATEGORY);
		String titleValue = criterias.get(CommonConstants.TITLE);

		if (!StringUtils.isEmpty(categoryValue)) {
			queryString = new StringBuffer("select distinct c from Card c join c.categories cat ");
			if (!queryString.toString().contains("where")) {
				queryString.append("where cat.id in (" + categoryValue + ")");
			} else {
				queryString.append(" and cat.id in (" + categoryValue + ")");
			}
		} else {
			queryString = new StringBuffer("from Card c ");
		}

		if (!StringUtils.isEmpty(bankValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where c.bank.id in (" + bankValue + ")");
			} else {
				queryString.append(" and c.bank.id in (" + bankValue + ")");
			}
		}
		if (!StringUtils.isEmpty(cardTypeValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where c.cardType.id in (" + cardTypeValue + ")");
			} else {
				queryString.append(" and c.cardType.id in (" + cardTypeValue + ")");
			}
		}
		if (!StringUtils.isEmpty(titleValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where c.title like '%" + titleValue + "%'");
			} else {
				queryString.append(" and c.title like '%" + titleValue + "%'");
			}
		}
		if (!StringUtils.isEmpty(cardCategoryValue)) {
			if (!queryString.toString().contains("where")) {
				queryString.append("where c.cardCategory.id in (" + cardCategoryValue + ")");
			} else {
				queryString.append(" and c.cardCategory.id in (" + cardCategoryValue + ")");
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
			queryString.append(" order by c.lastModifiedDate desc");
		}
		return queryString.toString();
	}

	@Override
	public List<Card> listAllCardsByTitle(String title) {
		return cardDao.listAllCardsByTitle(title);
	}

	@Override
	public Card getCardByTitle(String title) {
		return cardDao.getCardByTitle(title);
	}

}