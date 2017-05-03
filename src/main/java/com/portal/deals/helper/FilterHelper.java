package com.portal.deals.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portal.deals.model.Bank;
import com.portal.deals.model.Card;
import com.portal.deals.model.CardCategory;
import com.portal.deals.model.Category;
import com.portal.deals.model.Merchant;
import com.portal.deals.service.BankService;
import com.portal.deals.service.CardCategoryService;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.service.MerchantService;

/**
 * This is the controller class for doing CARD search
 * 
 * @author Gaurav Jain
 *
 */
@Component
public class FilterHelper {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(FilterHelper.class);

	/**
	 * Service class for communicating with DAO layer for Bank specific DB
	 * operations
	 */
	@Autowired
	private BankService bankService;

	/**
	 * Service class for communicating with DAO layer for Merchant specific DB
	 * operations
	 */
	@Autowired
	private MerchantService merchantService;

	/**
	 * Service class for communicating with DAO layer for Card specific DB
	 * operations
	 */
	@Autowired
	private CardManagerService cardService;

	/**
	 * Service class for communicating with DAO layer for Card Category specific
	 * DB operations
	 */
	@Autowired
	private CardCategoryService cardCategoryService;

	/**
	 * Service class for communicating with DAO layer for Category specific DB
	 * operations
	 */
	@Autowired
	private CategoryService categoryService;

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String COUNT = "count";
	private static final String DISPLAY_NAME = "displayName";
	private static final String BANK = "bank";
	private static final String CARD = "card";
	private static final String MERCHANT = "merchant";
	private static final String CARD_CATEGORY = "cardCategory";
	private static final String CATEGORY = "category";
	private static final String TITLE = "title";
	private static final String IS_CHECKED = "isChecked";

	/**
	 * This method will parse the query string into criteria object list
	 * 
	 * @param query
	 * @return
	 */
	public Map<String, String> getCriterias(String query) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Search Query" + query);
		}
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

	/**
	 * This method will create the JSON for Bank list
	 * 
	 * @param criterias
	 * @param factory
	 * @param bankCount
	 * @return
	 */
	public String getBankListJSON(Map<String, String> criterias, final JsonNodeFactory factory,
			Map<Integer, Long> bankCount) {
		ArrayNode childNodes = factory.arrayNode();
		List<JsonNode> expected = new ArrayList<JsonNode>();
		String selectedBankIds = criterias.get(BANK);
		List<Integer> selectedBanks = new ArrayList<>();
		if (!StringUtils.isEmpty(selectedBankIds)) {
			String[] bankIds = selectedBankIds.split(",");
			for (String id : bankIds) {
				selectedBanks.add(Integer.valueOf(id));
			}
		}
		for (Bank bank : bankService.listAllBanks()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, bank.getId());
			((ObjectNode) element).put(NAME, BANK);
			((ObjectNode) element).put(COUNT, bankCount.get(bank.getId()));
			((ObjectNode) element).put(DISPLAY_NAME, bank.getName());
			if (selectedBanks != null && selectedBanks.contains(bank.getId())) {
				((ObjectNode) element).put(IS_CHECKED, true);
			} else {
				((ObjectNode) element).put(IS_CHECKED, false);
			}
			expected.add(element);
		}
		sortCollection(childNodes, expected);
		return childNodes.toString();
	}

	/**
	 * This method will create the JSON for Bank list
	 * 
	 * @param criterias
	 * @param factory
	 * @param bankCount
	 * @return
	 */
	public String getCardListJSON(Map<String, String> criterias, final JsonNodeFactory factory,
			Map<Integer, Long> cardCount) {
		ArrayNode childNodes = factory.arrayNode();
		List<JsonNode> expected = new ArrayList<JsonNode>();

		List<Integer> selectedCards = new ArrayList<>();
		String selectedCardIds = criterias.get(CARD);
		if (!StringUtils.isEmpty(selectedCardIds)) {
			String[] cardIds = selectedCardIds.split(",");
			for (String id : cardIds) {
				selectedCards.add(Integer.valueOf(id));
			}
		}
		for (Card card : cardService.listAllCards()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, card.getId());
			((ObjectNode) element).put(NAME, CARD);
			((ObjectNode) element).put(COUNT, cardCount.get(card.getId()));
			((ObjectNode) element).put(DISPLAY_NAME, card.getTitle());
			if (selectedCards != null && selectedCards.contains(card.getId())) {
				((ObjectNode) element).put(IS_CHECKED, true);
			} else {
				((ObjectNode) element).put(IS_CHECKED, false);
			}
			expected.add(element);
		}
		sortCollection(childNodes, expected);
		return childNodes.toString();
	}

	/**
	 * This method will create the JSON for Bank list
	 * 
	 * @param criterias
	 * @param factory
	 * @param bankCount
	 * @return
	 */
	public String getMerchantListJSON(Map<String, String> criterias, final JsonNodeFactory factory,
			Map<Integer, Long> merchantCount) {
		ArrayNode childNodes = factory.arrayNode();
		List<JsonNode> expected = new ArrayList<JsonNode>();
		String selectedMerchantIds = criterias.get(MERCHANT);
		List<Integer> selectedMerchants = new ArrayList<>();
		if (!StringUtils.isEmpty(selectedMerchantIds)) {
			String[] merchantIds = selectedMerchantIds.split(",");
			for (String id : merchantIds) {
				selectedMerchants.add(Integer.valueOf(id));
			}
		}

		for (Merchant merchant : merchantService.listAllMerchants()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, merchant.getId());
			((ObjectNode) element).put(NAME, MERCHANT);
			((ObjectNode) element).put(COUNT, merchantCount.get(merchant.getId()));
			((ObjectNode) element).put(DISPLAY_NAME, merchant.getName());
			if (selectedMerchants != null && selectedMerchants.contains(merchant.getId())) {
				((ObjectNode) element).put(IS_CHECKED, true);
			} else {
				((ObjectNode) element).put(IS_CHECKED, false);
			}
			expected.add(element);
		}
		sortCollection(childNodes, expected);
		return childNodes.toString();
	}

	/**
	 * Comparator for sorting the filters
	 * 
	 * @param childNodes
	 * @param expected
	 */
	private void sortCollection(ArrayNode childNodes, List<JsonNode> expected) {
		Comparator<? super JsonNode> comparator = new Comparator<JsonNode>() {
			@Override
			public int compare(JsonNode o1, JsonNode o2) {
				int count1;
				int count2;
				if (o1.get(COUNT) == null) {
					count1 = 0;
				} else {
					count1 = o1.get(COUNT).intValue();
				}
				if (o2.get(COUNT) == null) {
					count2 = 0;
				} else {
					count2 = o2.get(COUNT).intValue();
				}
				return count2 - count1;
			}
		};
		Collections.sort(expected, comparator);

		for (JsonNode node : expected) {
			childNodes.add(node);
		}
	}

	/**
	 * This method will create JSON for card category list to be shown on
	 * filters
	 * 
	 * @param criterias
	 * @param factory
	 * @param cardCategoryCount
	 * @return
	 */
	public String getCardCategoryJSON(Map<String, String> criterias, final JsonNodeFactory factory,
			Map<Integer, Long> cardCategoryCount) {
		ArrayNode childNodes = factory.arrayNode();
		List<JsonNode> expected = new ArrayList<JsonNode>();
		String selectedCardCategoryIds = criterias.get(CARD_CATEGORY);
		List<Integer> selectedCardCategories = new ArrayList<>();
		if (!StringUtils.isEmpty(selectedCardCategoryIds)) {
			String[] cardCategoryIds = selectedCardCategoryIds.split(",");
			for (String id : cardCategoryIds) {
				selectedCardCategories.add(Integer.valueOf(id));
			}
		}

		for (CardCategory cardCategory : cardCategoryService.listAllCardCategories()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, cardCategory.getId());
			((ObjectNode) element).put(NAME, CARD_CATEGORY);
			((ObjectNode) element).put(COUNT, cardCategoryCount.get(cardCategory.getId()));
			((ObjectNode) element).put(DISPLAY_NAME, cardCategory.getName());
			if (selectedCardCategories != null && selectedCardCategories.contains(cardCategory.getId())) {
				((ObjectNode) element).put(IS_CHECKED, true);
			} else {
				((ObjectNode) element).put(IS_CHECKED, false);
			}
			expected.add(element);
		}
		sortCollection(childNodes, expected);
		return childNodes.toString();
	}

	/**
	 * This method will create JSON for category list to be shown on filters
	 * 
	 * @param criterias
	 * @param factory
	 * @param categoryCount
	 * @return
	 */
	public String getCategoryJSON(Map<String, String> criterias, final JsonNodeFactory factory,
			Map<Integer, Long> categoryCount) {
		ArrayNode childNodes = factory.arrayNode();
		List<JsonNode> expected = new ArrayList<JsonNode>();
		String selectedCategoryIds = criterias.get(CATEGORY);

		List<Integer> selectedCategory = new ArrayList<>();
		if (!StringUtils.isEmpty(selectedCategoryIds)) {
			String[] categoryIds = selectedCategoryIds.split(",");
			for (String id : categoryIds) {
				selectedCategory.add(Integer.valueOf(id));
			}
		}

		for (Category category : categoryService.listAllCategories()) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, category.getId());
			((ObjectNode) element).put(NAME, CATEGORY);
			((ObjectNode) element).put(COUNT, categoryCount.get(category.getId()));
			((ObjectNode) element).put(DISPLAY_NAME, category.getName());
			if (selectedCategory != null && selectedCategory.contains(category.getId())) {
				((ObjectNode) element).put(IS_CHECKED, true);
			} else {
				((ObjectNode) element).put(IS_CHECKED, false);
			}
			expected.add(element);
		}
		sortCollection(childNodes, expected);
		return childNodes.toString();
	}

	/**
	 * This method will create JSON for title
	 * 
	 * @param criterias
	 * @param factory
	 * @return
	 */
	public String getTitleJSON(Map<String, String> criterias, final JsonNodeFactory factory) {
		ArrayNode childNodes = factory.arrayNode();
		String title = criterias.get(TITLE);
		if (!StringUtils.isEmpty(title)) {
			JsonNode element = factory.objectNode();
			((ObjectNode) element).put(ID, title);
			((ObjectNode) element).put(NAME, TITLE);
			((ObjectNode) element).put(DISPLAY_NAME, title);
			childNodes.add(element);
		}
		return childNodes.toString();
	}
}
