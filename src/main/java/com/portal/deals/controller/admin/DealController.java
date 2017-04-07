package com.portal.deals.controller.admin;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Card;
import com.portal.deals.model.Category;
import com.portal.deals.model.Deal;
import com.portal.deals.model.OfferType;
import com.portal.deals.model.OfferUnit;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.service.DealService;
import com.portal.deals.service.OfferTypeService;
import com.portal.deals.service.OfferUnitService;

/**
 * This is the controller class for Deal CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class DealController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(DealController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private DealService service;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * Category entity
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardService;

	@Autowired
	private OfferTypeService offerTypeService;

	@Autowired
	private OfferUnitService offerUnitService;

	/** The JSP name for add new deal page */
	private static final String DEAL_FORM_JSP = "dealForm";

	/** The JSP name for add new deal page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateDealForm";

	/** The JSP name for deal list page */
	private static final String DEAL_LIST_JSP = "dealList";

	/** The module name */
	private static final String MODULE = "dealManager";

	/**
	 * This method will render the add new deal page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newDeal/{id}")
	public String addDeal(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new deal page");
		try {
			/**
			 * Adding the blank Deal object as model attribute for Form
			 */
			model.addAttribute("deal", new Deal());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new deal Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the deal data in the database
	 * 
	 * @param deal
	 *            category The CARD category added on add deal form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newDeal/{id}", method = RequestMethod.POST)
	public String addDeal(@PathVariable("id") int cardId, @Valid Deal deal, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the Deal to the database");

		try {
			/**
			 * If there is any validation error, then reload the Deal form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, deal.getCardId());
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(deal.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddDeal", "Card not found");
			}
			/** Add the deal to Card */
			deal.setCard(card);

			/** Save the Deal in the database */
			service.saveDeal(deal);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Deal", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDeals/" + deal.getCardId();
	}

	/**
	 * This method will get the list of deal from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listDeals/{id}")
	public String listAllDeals(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the deal  list page");
		try {
			List<Deal> deals = service.getDealsByCardId(cardId);
			/**
			 * Adding the deal list to model, to be used for rendering in JSP
			 */
			model.addAttribute("deals", deals);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the deal listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the deal details
	 * 
	 * @param id
	 *            Id of the deal, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateDeal/{id}/{cardId}")
	public String updateDeal(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update deal page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Deal id to be updated [" + id + "]");
			}
			/** Get the Deal entity by id from the database */
			Deal deal = service.getDealById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("deal", deal);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the deal", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the deal data in the database
	 * 
	 * @param deal
	 *            The CARD details added on add deal form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateDeal/{cardId}", method = RequestMethod.POST)
	public String updateDeal(@PathVariable("cardId") int cardId, @Valid Deal deal, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the deal details");
		try {
			/** Reload the update deal page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, deal.getCard());
				return "redirect:/admin/updateDeal/" + deal.getId() + "/" + deal.getCardId();
			}

			/** Updating the Deal in the database */
			service.updateDeal(deal);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Deal Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDeals/" + deal.getCardId();
	}

	/**
	 * This method will delete the deal from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the deal that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteDeal/{id}/{cardId}")
	public String deleteCard(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the deal from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Deal id to be deleted [" + id + "]");
			}
			/** Call to database to delete the deal */
			service.deleteDealById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the deal", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDeals/" + cardId;
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("categories")
	public List<Category> initializeCategories() {
		return categoryService.listAllRootCategories();
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("offerTypes")
	public List<OfferType> initializeOfferTypes() {
		return offerTypeService.listAllOfferTypes();
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("offerUnits")
	public List<OfferUnit> initializeOfferUnits() {
		return offerUnitService.listAllOfferUnits();
	}

	/***
	 * Binder for converting the data types
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(Set.class, "categories", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				Integer name = null;
				if (element instanceof String) {
					name = Integer.parseInt((String) element);
				}
				return name != null ? new Category(name) : null;
			}
		});
	}

}
