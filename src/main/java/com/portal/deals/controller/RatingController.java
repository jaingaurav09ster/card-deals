package com.portal.deals.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import com.portal.deals.model.Card;
import com.portal.deals.model.Rating;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.RatingService;

/**
 * This is the controller class for Rating CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@Secured({ "ROLE_BANK", "ROLE_ADMIN" })
public class RatingController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RatingController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private RatingService service;
	
	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardService;

	/** The JSP name for add new rating page */
	private static final String DEAL_FORM_JSP = "ratingForm";

	/** The JSP name for add new rating page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateRatingForm";

	/** The JSP name for rating list page */
	private static final String DEAL_LIST_JSP = "ratingList";

	/** The module name */
	private static final String MODULE = "ratingManager";

	/**
	 * This method will render the add new rating page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newRating/{id}")
	public String addRating(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new rating page");
		try {
			/**
			 * Adding the blank Rating object as model attribute for Form
			 */
			model.addAttribute("rating", new Rating());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new rating Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the rating data in the database
	 * 
	 * @param rating
	 *            category The CARD category added on add rating form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newRating/{id}", method = RequestMethod.POST)
	public String addRating(@PathVariable("id") int cardId, @Valid Rating rating, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Rating to the database");

		try {
			/**
			 * If there is any validation error, then reload the Rating form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, rating.getCardId());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(rating.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddRating", "Card not found");
			}
			/** Add the rating to Card */
			rating.setCard(card);

			/** Save the Rating in the database */
			service.saveRating(rating);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Rating", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listRatings/" + rating.getCardId();
	}

	/**
	 * This method will get the list of rating from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listRatings/{id}")
	public String listAllRatings(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the rating  list page");
		try {
			List<Rating> ratings = service.getRatingsByCardId(cardId);
			/**
			 * Adding the rating list to model, to be used for rendering in JSP
			 */
			model.addAttribute("ratings", ratings);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the rating listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the rating details
	 * 
	 * @param id
	 *            Id of the rating, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateRating/{id}/{cardId}")
	public String updateRating(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update rating page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Rating id to be updated [" + id + "]");
			}
			/** Get the Rating entity by id from the database */
			Rating rating = service.getRatingById(id);
			if (rating == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("rating", rating);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the rating", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the rating data in the database
	 * 
	 * @param rating
	 *            The CARD details added on add rating form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateRating/{cardId}", method = RequestMethod.POST)
	public String updateRating(@PathVariable("cardId") int cardId, @Valid Rating rating, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the rating details");
		try {
			/** Reload the update rating page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, rating.getCard());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return "redirect:/updateRating/" + rating.getId() + "/" + rating.getCardId();
			}

			/** Updating the Rating in the database */
			service.updateRating(rating);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Rating Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listRatings/" + rating.getCardId();
	}

	/**
	 * This method will delete the rating from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the rating that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteRating/{id}/{cardId}")
	public String deleteRating(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the rating from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Rating id to be deleted [" + id + "]");
			}
			/** Call to database to delete the rating */
			service.deleteRatingById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the rating", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listRatings/" + cardId;
	}
}
