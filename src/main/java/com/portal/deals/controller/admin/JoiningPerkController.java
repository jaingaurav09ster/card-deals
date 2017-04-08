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
import com.portal.deals.model.JoiningPerk;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.service.JoiningPerkService;

/**
 * This is the controller class for JoiningPerk CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class JoiningPerkController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(JoiningPerkController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private JoiningPerkService service;
	
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

	/** The JSP name for add new joiningPerk page */
	private static final String DEAL_FORM_JSP = "joiningPerkForm";

	/** The JSP name for add new joiningPerk page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateJoiningPerkForm";

	/** The JSP name for joiningPerk list page */
	private static final String DEAL_LIST_JSP = "joiningPerkList";

	/** The module name */
	private static final String MODULE = "joiningPerkManager";

	/**
	 * This method will render the add new joiningPerk page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newJoiningPerk/{id}")
	public String addJoiningPerk(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new joiningPerk page");
		try {
			/**
			 * Adding the blank JoiningPerk object as model attribute for Form
			 */
			model.addAttribute("joiningPerk", new JoiningPerk());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new joiningPerk Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the joiningPerk data in the database
	 * 
	 * @param joiningPerk
	 *            category The CARD category added on add joiningPerk form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newJoiningPerk/{id}", method = RequestMethod.POST)
	public String addJoiningPerk(@PathVariable("id") int cardId, @Valid JoiningPerk joiningPerk, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the JoiningPerk to the database");

		try {
			/**
			 * If there is any validation error, then reload the JoiningPerk form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, joiningPerk.getCardId());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(joiningPerk.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddJoiningPerk", "Card not found");
			}
			/** Add the joiningPerk to Card */
			joiningPerk.setCard(card);

			/** Save the JoiningPerk in the database */
			service.saveJoiningPerk(joiningPerk);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the JoiningPerk", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listJoiningPerks/" + joiningPerk.getCardId();
	}

	/**
	 * This method will get the list of joiningPerk from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listJoiningPerks/{id}")
	public String listAllJoiningPerks(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the joiningPerk  list page");
		try {
			List<JoiningPerk> joiningPerks = service.getJoiningPerksByCardId(cardId);
			/**
			 * Adding the joiningPerk list to model, to be used for rendering in JSP
			 */
			model.addAttribute("joiningPerks", joiningPerks);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the joiningPerk listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the joiningPerk details
	 * 
	 * @param id
	 *            Id of the joiningPerk, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateJoiningPerk/{id}/{cardId}")
	public String updateJoiningPerk(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update joiningPerk page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The JoiningPerk id to be updated [" + id + "]");
			}
			/** Get the JoiningPerk entity by id from the database */
			JoiningPerk joiningPerk = service.getJoiningPerkById(id);
			if (joiningPerk == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("joiningPerk", joiningPerk);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the joiningPerk", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the joiningPerk data in the database
	 * 
	 * @param joiningPerk
	 *            The CARD details added on add joiningPerk form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateJoiningPerk/{cardId}", method = RequestMethod.POST)
	public String updateJoiningPerk(@PathVariable("cardId") int cardId, @Valid JoiningPerk joiningPerk, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the joiningPerk details");
		try {
			/** Reload the update joiningPerk page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, joiningPerk.getCard());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return "redirect:/admin/updateJoiningPerk/" + joiningPerk.getId() + "/" + joiningPerk.getCardId();
			}

			/** Updating the JoiningPerk in the database */
			service.updateJoiningPerk(joiningPerk);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading JoiningPerk Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listJoiningPerks/" + joiningPerk.getCardId();
	}

	/**
	 * This method will delete the joiningPerk from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the joiningPerk that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteJoiningPerk/{id}/{cardId}")
	public String deleteCard(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the joiningPerk from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The JoiningPerk id to be deleted [" + id + "]");
			}
			/** Call to database to delete the joiningPerk */
			service.deleteJoiningPerkById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the joiningPerk", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listJoiningPerks/" + cardId;
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("categories")
	public List<Category> initializeCategories() {
		return categoryService.listAllRootCategories();
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
