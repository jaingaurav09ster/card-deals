package com.portal.deals.controller.admin;

import java.util.List;

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

import com.portal.deals.controller.RegistrationController;
import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.model.Card;
import com.portal.deals.service.CardManagerService;

/**
 * This is the controller class for card CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin/card")
public class AdminCardController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardServiceManager;

	/** The JSP name for add new card page */
	private static final String CARD_FORM_JSP = "cardForm";

	/** The JSP name for card list page */
	private static final String CARD_LIST_JSP = "cardList";

	/**
	 * This method will render the add new card page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCard")
	public String addCard(ModelMap model) {
		LOG.info("Rendering the add new card page");
		try {
			/** Adding the blank Card object as model attribute for Form */
			model.addAttribute("card", new Card());
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new card Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_FORM_JSP;
	}

	/**
	 * This method will persist the card data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCard", method = RequestMethod.POST)
	public String addCard(@Valid Card card, BindingResult result, ModelMap model) {
		LOG.info("Saving the card to the database");

		try {
			/**
			 * If there is any validation error, then reload the card form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				return CARD_FORM_JSP;
			}

			/** Save the card in the database */
			cardServiceManager.saveCard(card);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/card/listCards";
	}

	/**
	 * This method will get the list of cards from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listCards")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the card list page");
		try {
			/** Get the list of Cards from the database */
			List<Card> cards = cardServiceManager.listAllCards();

			/**
			 * Adding the card list to model, to be used for rendering in JSP
			 */
			model.addAttribute("cards", cards);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the card listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_LIST_JSP;
	}

	/**
	 * This method will update the card details
	 * 
	 * @param id
	 *            Id of the card, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCard/{id}")
	public String updateCardForm(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update card page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card id to be updated [" + id + "]");
			}
			/** Get the Card entity by id from the database */
			Card card = cardServiceManager.getCardById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("card", card);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_FORM_JSP;
	}

	/**
	 * This method will update the card data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	public String updateCard(@Valid Card card, BindingResult result, ModelMap model) {
		LOG.info("Updating the card details");
		try {
			/** Reload the update card page in case of any error */
			if (result.hasErrors()) {
				return "redirect:/admin/card/showUpdateForm/" + card.getId();
			}

			/** Updating the Card in the database */
			cardServiceManager.updateCard(card);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading registration Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/card/listCards";
	}

	/**
	 * This method will delete the card from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the card that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteCard/{id}")
	public String deleteCard(@PathVariable("id") int id) {
		LOG.info("Deleting the card from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card id to be deleted [" + id + "]");
			}
			/** Call to database to delete the card */
			cardServiceManager.deleteCardById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/card/listCards";
	}
}
