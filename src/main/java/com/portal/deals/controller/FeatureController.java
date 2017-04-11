package com.portal.deals.controller;

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
import org.springframework.security.access.annotation.Secured;
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
import com.portal.deals.model.Feature;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.service.FeatureService;

/**
 * This is the controller class for Feature CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@Secured({ "ROLE_BANK", "ROLE_ADMIN" })
public class FeatureController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(FeatureController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private FeatureService service;
	
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

	/** The JSP name for add new feature page */
	private static final String DEAL_FORM_JSP = "featureForm";

	/** The JSP name for add new feature page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateFeatureForm";

	/** The JSP name for feature list page */
	private static final String DEAL_LIST_JSP = "featureList";

	/** The module name */
	private static final String MODULE = "featureManager";

	/**
	 * This method will render the add new feature page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newFeature/{id}")
	public String addFeature(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new feature page");
		try {
			/**
			 * Adding the blank Feature object as model attribute for Form
			 */
			model.addAttribute("feature", new Feature());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new feature Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the feature data in the database
	 * 
	 * @param feature
	 *            category The CARD category added on add feature form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newFeature/{id}", method = RequestMethod.POST)
	public String addFeature(@PathVariable("id") int cardId, @Valid Feature feature, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Feature to the database");

		try {
			/**
			 * If there is any validation error, then reload the Feature form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, feature.getCardId());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(feature.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddFeature", "Card not found");
			}
			/** Add the feature to Card */
			feature.setCard(card);

			/** Save the Feature in the database */
			service.saveFeature(feature);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Feature", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listFeatures/" + feature.getCardId();
	}

	/**
	 * This method will get the list of feature from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listFeatures/{id}")
	public String listAllFeatures(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the feature  list page");
		try {
			List<Feature> features = service.getFeaturesByCardId(cardId);
			/**
			 * Adding the feature list to model, to be used for rendering in JSP
			 */
			model.addAttribute("features", features);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the feature listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the feature details
	 * 
	 * @param id
	 *            Id of the feature, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateFeature/{id}/{cardId}")
	public String updateFeature(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update feature page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Feature id to be updated [" + id + "]");
			}
			/** Get the Feature entity by id from the database */
			Feature feature = service.getFeatureById(id);
			if (feature == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("feature", feature);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the feature", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the feature data in the database
	 * 
	 * @param feature
	 *            The CARD details added on add feature form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateFeature/{cardId}", method = RequestMethod.POST)
	public String updateFeature(@PathVariable("cardId") int cardId, @Valid Feature feature, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the feature details");
		try {
			/** Reload the update feature page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, feature.getCard());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return "redirect:/updateFeature/" + feature.getId() + "/" + feature.getCardId();
			}

			/** Updating the Feature in the database */
			service.updateFeature(feature);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Feature Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listFeatures/" + feature.getCardId();
	}

	/**
	 * This method will delete the feature from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the feature that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteFeature/{id}/{cardId}")
	public String deleteCard(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the feature from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Feature id to be deleted [" + id + "]");
			}
			/** Call to database to delete the feature */
			service.deleteFeatureById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the feature", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listFeatures/" + cardId;
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
