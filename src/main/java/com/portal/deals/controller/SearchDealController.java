package com.portal.deals.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.portal.deals.helper.FilterHelper;
import com.portal.deals.model.Card;
import com.portal.deals.model.SearchDealResponse;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.DealService;

/**
 * This is the controller class for doing DEAL search
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class SearchDealController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(SearchDealController.class);

	/**
	 * Service class for communicating with DAO layer for Card specific DB
	 * operations
	 */
	@Autowired
	private DealService dealService;

	/**
	 * Service class for communicating with DAO layer for Card specific DB
	 * operations
	 */
	@Autowired
	private CardManagerService cardService;

	/**
	 * Helper class for filter related methods
	 */
	@Autowired
	private FilterHelper filterHelper;

	private static final String SEARCH_JSP = "searchDeal";

	private static final String BANK_LIST = "banks";
	private static final String CARD_CATEGORY_LIST = "cardCategories";
	private static final String CATEGORY_LIST = "categories";
	private static final String CARD_LIST = "cards";
	private static final String MERCHANT_LIST = "merchants";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String TITLE = "title";

	/**
	 * This method will render the search page for Deals
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchDeals", method = RequestMethod.GET)
	public String searchCards(@RequestParam(value = "query", required = false) String query, ModelMap model)
			throws IOException {
		LOG.info("Searching for Deals in the database");

		/** Get the search criteria from the query parameter */
		Map<String, String> criterias = filterHelper.getCriterias(query);
		String title = criterias.get(TITLE);
		List<String> cardValues = new ArrayList<>();
		if (!StringUtils.isEmpty(title)) {
			List<Card> cards = cardService.listAllCardsByTitle(title);
			if (cards != null && cards.size() > 0) {
				for (Card card : cards) {
					cardValues.add(card.getId().toString());
				}
				query = "card:" + String.join(",", cardValues);
				criterias = filterHelper.getCriterias(query);
			}
		}
		if (query == null) {
			query = "";
		}
		/** Get the counts for each filter */
		Map<Integer, Long> bankCount = dealService.getCountByBank();
		Map<Integer, Long> cardCategoryCount = dealService.getCountByCardCategory();
		Map<Integer, Long> categoryCount = dealService.getCountByCategory();
		Map<Integer, Long> cardCount = dealService.getCountByCards();
		Map<Integer, Long> merchantCount = dealService.getCountByMerchants();

		/** Get the filters and add to model to be painted on view */
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		model.addAttribute(BANK_LIST, filterHelper.getBankListJSON(criterias, factory, bankCount));
		model.addAttribute(CARD_CATEGORY_LIST, filterHelper.getCardCategoryJSON(criterias, factory, cardCategoryCount));
		model.addAttribute(CATEGORY_LIST, filterHelper.getCategoryJSON(criterias, factory, categoryCount));
		model.addAttribute(CARD_LIST, filterHelper.getCardListJSON(criterias, factory, cardCount));
		model.addAttribute(MERCHANT_LIST, filterHelper.getMerchantListJSON(criterias, factory, merchantCount));
		model.addAttribute("query", query);

		String pageIndex = "0";
		if (!StringUtils.isEmpty(criterias.get(PAGE_INDEX))) {
			pageIndex = criterias.get(PAGE_INDEX);
		}
		model.addAttribute(PAGE_INDEX, pageIndex);
		return SEARCH_JSP;
	}

	/**
	 * This method will search for deals in the database based on Query string
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchDealsAjax", method = RequestMethod.GET)
	public @ResponseBody SearchDealResponse search(@RequestParam(value = "query", required = false) String query)
			throws IOException {
		LOG.info("Searching for Cards in the database");
		Map<String, String> criterias = filterHelper.getCriterias(query);
		return dealService.searchDeal(criterias);
	}

}
