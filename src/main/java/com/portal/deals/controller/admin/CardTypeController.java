package com.portal.deals.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.CardType;
import com.portal.deals.service.CardTypeService;

/**
 * This is the controller class for card type CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class CardTypeController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CardTypeController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardTypeService service;

	/** The JSP name for add new card type page */
	private static final String CARD_TYPE_FORM_JSP = "cardTypeForm";

	/** The update card type page */
	private static final String UPDATE_CARD_TYPE_FORM_JSP = "updateCardTypeForm";

	/** The JSP name for card type list page */
	private static final String CARD_TYPE_LIST_JSP = "cardTypeList";

	/** The module name */
	private static final String MODULE = "cardTypeManager";

	/**
	 * This method will render the add new card type page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCardType")
	public String addCardType(ModelMap model) {
		LOG.info("Rendering the add new card type page");
		try {
			/**
			 * Adding the blank Card Type object as model attribute for Form
			 */
			model.addAttribute("cardType", new CardType());
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_TYPE_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new card type Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_TYPE_FORM_JSP;
	}

	/**
	 * This method will persist the card data in the database
	 * 
	 * @param card
	 *            type The CARD type added on add card type form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCardType", method = RequestMethod.POST)
	public String addCardType(@Valid CardType cardType, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the CardType to the database");

		try {
			/**
			 * If there is any validation error, then reload the CardType form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CARD_TYPE_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return CARD_TYPE_FORM_JSP;
			}

			/** Save the CardType in the database */
			service.saveCardType(cardType);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the CardType", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardTypes";
	}

	/**
	 * This method will get the list of card categories from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listCardTypes")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the card categories list page");
		try {
			/** Get the list of Card categories from the database */
			List<CardType> cardTypes = service.listAllCardTypes();

			/**
			 * Adding the card categories list to model, to be used for
			 * rendering in JSP
			 */
			model.addAttribute("cardTypes", cardTypes);
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_TYPE_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the card type listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_TYPE_LIST_JSP;
	}

	/**
	 * This method will update the card Category details
	 * 
	 * @param id
	 *            Id of the card Category, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCardType/{id}")
	public String updateCardTypeForm(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update card Category page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card Type id to be updated [" + id + "]");
			}
			/** Get the Card Type entity by id from the database */
			CardType cardType = service.getCardTypeById(id);
			if (cardType == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("cardType", cardType);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_TYPE_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the card Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_TYPE_FORM_JSP;
	}

	/**
	 * This method will update the card Category data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card Category form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCardType", method = RequestMethod.POST)
	public String updateCard(@Valid CardType cardType, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the card Category details");
		try {
			/** Reload the update card Category page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_TYPE_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateCardType/" + cardType.getId();
			}

			/** Updating the Card Type in the database */
			service.updateCardType(cardType);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Card Type Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardTypes";
	}

	/**
	 * This method will delete the card Category from the database, based on the
	 * id passed
	 * 
	 * @param id
	 *            The id of the card Category that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteCardType/{id}")
	public String deleteCard(@PathVariable("id") int id) {
		LOG.info("Deleting the card Category from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card Type id to be deleted [" + id + "]");
			}
			/** Call to database to delete the card Category */
			service.deleteCardTypeById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the card Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardTypes";
	}

}
