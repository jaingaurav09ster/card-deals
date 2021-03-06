package com.portal.deals.controller;

import java.io.IOException;
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
import com.portal.deals.model.SearchCardResponse;
import com.portal.deals.model.User;
import com.portal.deals.model.UserDetails;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.UserService;
import com.portal.deals.util.Utils;

/**
 * This is the controller class for doing CARD search
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class SearchCardController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(SearchCardController.class);

	/**
	 * Service class for communicating with DAO layer for Card specific DB
	 * operations
	 */
	@Autowired
	private CardManagerService cardServiceManager;

	/**
	 * Helper class for filter related methods
	 */
	@Autowired
	private FilterHelper filterHelper;

	/**
	 * Service class for communicating with DAO layer for USER specific DB
	 * operations
	 */
	@Autowired
	UserService userService;

	private static final String SEARCH_JSP = "searchCard";

	private static final String BANK_LIST = "banks";
	private static final String CARD_TYPE_LIST = "cardTypes";
	private static final String CARD_CATEGORY_LIST = "cardCategories";
	private static final String CATEGORY_LIST = "categories";
	private static final String TITLE = "title";
	private static final String PAGE_INDEX = "pageIndex";

	/**
	 * This method will render the search page for Cards
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchCards", method = RequestMethod.GET)
	public String searchCards(@RequestParam(value = "query", required = false) String query, ModelMap model)
			throws IOException {
		LOG.info("Searching for Cards in the database");

		/** Get the search criteria from the query parameter */
		Map<String, String> criterias = filterHelper.getCriterias(query);

		/** Get the counts for each filter */
		Map<Integer, Long> bankCount = cardServiceManager.getCountByBank();
		Map<Integer, Long> cardTypeCount = cardServiceManager.getCountByCardType();
		Map<Integer, Long> cardCategoryCount = cardServiceManager.getCountByCardCategory();
		Map<Integer, Long> categoryCount = cardServiceManager.getCountByCategory();

		/** Get the filters and add to model to be painted on view */
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		model.addAttribute(BANK_LIST, filterHelper.getBankListJSON(criterias, factory, bankCount));
		model.addAttribute(CARD_TYPE_LIST, filterHelper.getCardTypeListJSON(criterias, factory, cardTypeCount));
		model.addAttribute(CARD_CATEGORY_LIST, filterHelper.getCardCategoryJSON(criterias, factory, cardCategoryCount));
		model.addAttribute(CATEGORY_LIST, filterHelper.getCategoryJSON(criterias, factory, categoryCount));
		model.addAttribute(TITLE, filterHelper.getTitleJSON(criterias, factory));

		String pageIndex = "0";
		if (!StringUtils.isEmpty(criterias.get(PAGE_INDEX))) {
			pageIndex = criterias.get(PAGE_INDEX);
		}
		model.addAttribute(PAGE_INDEX, pageIndex);
		return SEARCH_JSP;
	}

	/**
	 * This method will search for cards in the database based on Query string
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchCardsAjax", method = RequestMethod.GET)
	public @ResponseBody SearchCardResponse search(@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "filter", required = false) String filter) throws IOException {
		LOG.info("Searching for Cards in the database");
		Map<String, String> criterias = filterHelper.getCriterias(query);
		SearchCardResponse response = cardServiceManager.searchCard(criterias);
		List<Card> cards = response.getCards();
		if (cards != null && cards.size() > 0) {
			if (!StringUtils.isEmpty(filter)) {
				UserDetails userDetails = Utils.getLoggedInUser();
				if (LOG.isDebugEnabled()) {
					LOG.debug("Logged In User's Email [" + userDetails.getUsername() + "]");
				}
				User user = userService.findByEmail(userDetails.getUsername());
				if (user != null) {
					if (user.getCards() != null && user.getCards().size() > 0) {
						for (Card card : user.getCards()) {
							cards.remove(card);
						}
					}
				}
			}
		}
		return response;
	}
}
