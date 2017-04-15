package com.portal.deals.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portal.deals.model.Bank;
import com.portal.deals.model.Card;
import com.portal.deals.model.CardCategory;
import com.portal.deals.model.Category;
import com.portal.deals.service.BankService;
import com.portal.deals.service.CardCategoryService;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;

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

	@Autowired
	private BankService bankService;

	@Autowired
	private CardCategoryService cardCategoryService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * This method will search for cards in the database based on Query string
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchCardsAjax", method = RequestMethod.GET)
	public @ResponseBody List<Card> search(@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "order", required = false) String order) throws IOException {
		LOG.info("Searching for Cards in the database");
		Map<String, String> criterias = getCriterias(query);
		String queryString = createQueryString(criterias, orderBy, order);
		if (StringUtils.isEmpty(pageIndex)) {
			pageIndex = 0;
		}
		if (StringUtils.isEmpty(limit)) {
			limit = 10;
		}
		int begin = ((pageIndex) * limit);
		List<Card> cards = new ArrayList<Card>();
		List<Object[]> resultList = cardServiceManager.searchCard(queryString.toString(), begin, limit);
		for (Object[] result : resultList) {
			Card card = (Card) result[0];
			cards.add(card);
		}
		return cards;
	}

	private Map<String, String> getCriterias(String query) {
		Map<String, String> criterias = new HashMap<String, String>();
		if (!StringUtils.isEmpty(query)) {
			String[] params = query.split("::");
			for (String param : params) {
				if (!StringUtils.isEmpty(param)) {
					String[] filter = param.split(":");
					if (filter != null && filter.length > 1) {
						String criteria = filter[0];
						String values = filter[1];
						criterias.put(criteria, values);
					}
				}
			}
		}
		return criterias;
	}

	private String createQueryString(Map<String, String> criterias, String orderBy, String order) {
		StringBuffer queryString = new StringBuffer("select c, cat from Card c join c.categories cat ");
		for (Map.Entry<String, String> criteria : criterias.entrySet()) {
			if (criteria.getKey() != null && "category".equalsIgnoreCase(criteria.getKey())) {
				if (!queryString.toString().contains("where")) {
					queryString.append("where cat.id in (" + criteria.getValue() + ")");
				} else {
					queryString.append(" and cat.id in (" + criteria.getValue() + ")");
				}
			} else if (criteria.getKey() != null && "bank".equalsIgnoreCase(criteria.getKey())) {
				if (!queryString.toString().contains("where")) {
					queryString.append("where c.bank.id in (" + criteria.getValue() + ")");
				} else {
					queryString.append(" and c.bank.id in (" + criteria.getValue() + ")");
				}
			} else if (criteria.getKey() != null && "title".equalsIgnoreCase(criteria.getKey())) {
				if (!queryString.toString().contains("where")) {
					queryString.append("where c.title like '%" + criteria.getValue() + "%'");
				} else {
					queryString.append(" and c.title like '%" + criteria.getValue() + "%'");
				}
			} else if (criteria.getKey() != null && "cardCategory".equalsIgnoreCase(criteria.getKey())) {
				if (!queryString.toString().contains("where")) {
					queryString.append("where c.cardCategory.id in (" + criteria.getValue() + ")");
				} else {
					queryString.append(" and c.cardCategory.id in (" + criteria.getValue() + ")");
				}
			}
		}
		if (!StringUtils.isEmpty(orderBy) && !StringUtils.isEmpty(order)) {
			if (order.equalsIgnoreCase("desc")) {
				queryString.append(" order by c." + orderBy + " desc");
			} else {
				queryString.append(" order by c." + orderBy + " asc");
			}
		} else {
			queryString.append(" order by c.lastModifiedDate desc");
		}
		return queryString.toString();
	}

	/**
	 * This method will search for cards in the database based on Query string
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
		Map<String, String> criterias = getCriterias(query);

		final JsonNodeFactory factory = JsonNodeFactory.instance;

		model.addAttribute("banks", getBankListJSON(criterias, factory));
		model.addAttribute("cardCategories", getCardCategoryJSON(criterias, factory));
		model.addAttribute("categories", getCategoryJSON(criterias, factory));
		model.addAttribute("title", getTitleJSON(criterias, factory));
		return "searchPage";
	}

	private String getBankListJSON(Map<String, String> criterias, final JsonNodeFactory factory) {
		ArrayNode childNodes = factory.arrayNode();
		String selectedBanks = criterias.get("bank");
		for (Bank bank : bankService.listAllBanks()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put("id", bank.getId());
			((ObjectNode) element).put("name", "bank");
			((ObjectNode) element).put("displayName", bank.getName());
			if (selectedBanks != null && selectedBanks.contains(bank.getId().toString())) {
				((ObjectNode) element).put("isChecked", true);
			} else {
				((ObjectNode) element).put("isChecked", false);
			}
			childNodes.add(element);
		}
		return childNodes.toString();
	}

	private String getCardCategoryJSON(Map<String, String> criterias, final JsonNodeFactory factory) {
		ArrayNode childNodes = factory.arrayNode();
		String selectedCardCategories = criterias.get("cardCategory");
		for (CardCategory cardCategory : cardCategoryService.listAllCardCategories()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put("id", cardCategory.getId());
			((ObjectNode) element).put("name", "cardCategory");
			((ObjectNode) element).put("displayName", cardCategory.getName());
			if (selectedCardCategories != null && selectedCardCategories.contains(cardCategory.getId().toString())) {
				((ObjectNode) element).put("isChecked", true);
			} else {
				((ObjectNode) element).put("isChecked", false);
			}
			childNodes.add(element);
		}
		return childNodes.toString();
	}

	private String getCategoryJSON(Map<String, String> criterias, final JsonNodeFactory factory) {
		ArrayNode childNodes = factory.arrayNode();
		String selectedCategory = criterias.get("category");
		for (Category category : categoryService.listAllCategories()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put("id", category.getId());
			((ObjectNode) element).put("name", "category");
			((ObjectNode) element).put("displayName", category.getName());
			if (selectedCategory != null && selectedCategory.contains(category.getId().toString())) {
				((ObjectNode) element).put("isChecked", true);
			} else {
				((ObjectNode) element).put("isChecked", false);
			}
			childNodes.add(element);
		}
		return childNodes.toString();
	}

	private String getTitleJSON(Map<String, String> criterias, final JsonNodeFactory factory) {
		ArrayNode childNodes = factory.arrayNode();
		String title = criterias.get("title");
		if (!StringUtils.isEmpty(title)) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put("id", title);
			((ObjectNode) element).put("name", "title");
			((ObjectNode) element).put("displayName", title);
			childNodes.add(element);
		}
		return childNodes.toString();
	}
}
