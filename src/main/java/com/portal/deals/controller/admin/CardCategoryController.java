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
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.CardCategory;
import com.portal.deals.service.CardCategoryService;

/**
 * This is the controller class for card category CRUD operation. Only ADMIN
 * will have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class CardCategoryController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CardCategoryController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardCategoryService service;

	/** The JSP name for add new card category page */
	private static final String CARD_CATEGORY_FORM_JSP = "cardCategoryForm";

	/** The update card category page */
	private static final String UPDATE_CARD_CATEGORY_FORM_JSP = "updateCardCategoryForm";

	/** The JSP name for card category list page */
	private static final String CARD_CATEGORY_LIST_JSP = "cardCategoryList";

	/** The module name */
	private static final String MODULE = "cardCategoryManager";

	/**
	 * This method will render the add new card category page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCardCategory")
	public String addCardCategory(ModelMap model) {
		LOG.info("Rendering the add new card category page");
		try {
			/**
			 * Adding the blank Card Category object as model attribute for Form
			 */
			model.addAttribute("cardCategory", new CardCategory());
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new card category Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_CATEGORY_FORM_JSP;
	}

	/**
	 * This method will persist the card data in the database
	 * 
	 * @param card
	 *            category The CARD category added on add card category form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCardCategory", method = RequestMethod.POST)
	public String addCardCategory(@Valid CardCategory cardCategory, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the CardCategory to the database");

		try {
			/**
			 * If there is any validation error, then reload the CardCategory
			 * form page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CARD_CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return CARD_CATEGORY_FORM_JSP;
			}

			/** Save the CardCategory in the database */
			service.saveCardCategory(cardCategory);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the CardCategory", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardCategories";
	}

	/**
	 * This method will get the list of card categories from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listCardCategories")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the card categories list page");
		try {
			/** Get the list of Card categories from the database */
			List<CardCategory> cardCategories = service.listAllCardCategories();

			/**
			 * Adding the card categories list to model, to be used for
			 * rendering in JSP
			 */
			model.addAttribute("cardCategories", cardCategories);
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_CATEGORY_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the card category listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_CATEGORY_LIST_JSP;
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
	@RequestMapping(value = "/updateCardCategory/{id}")
	public String updateCardCategoryForm(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update card Category page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card Category id to be updated [" + id + "]");
			}
			/** Get the Card Category entity by id from the database */
			CardCategory cardCategory = service.getCardCategoryById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("cardCategory", cardCategory);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_CATEGORY_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the card Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_CATEGORY_FORM_JSP;
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
	@RequestMapping(value = "/updateCardCategory", method = RequestMethod.POST)
	public String updateCard(@Valid CardCategory cardCategory, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the card Category details");
		try {
			/** Reload the update card Category page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_CATEGORY_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateCardCategory/" + cardCategory.getId();
			}

			/** Updating the Card Category in the database */
			service.updateCardCategory(cardCategory);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Card Category Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardCategories";
	}

	/**
	 * This method will delete the card Category from the database, based on the
	 * id passed
	 * 
	 * @param id
	 *            The id of the card Category that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteCardCategory/{id}")
	public String deleteCard(@PathVariable("id") int id) {
		LOG.info("Deleting the card Category from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card Category id to be deleted [" + id + "]");
			}
			/** Call to database to delete the card Category */
			service.deleteCardCategoryById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the card Category", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listCardCategories";
	}

}
