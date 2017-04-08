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
import com.portal.deals.model.OfferType;
import com.portal.deals.service.OfferTypeService;

/**
 * This is the controller class for offerType CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class OfferTypeController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(OfferTypeController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private OfferTypeService service;

	/** The update new offerType page */
	private static final String UPDATE_OFFER_TYPE_FORM_JSP = "updateOfferTypeForm";

	/** The JSP name for add new offerType page */
	private static final String OFFER_TYPE_FORM_JSP = "offerTypeForm";

	/** The JSP name for offerType list page */
	private static final String OFFER_TYPE_LIST_JSP = "offerTypeList";

	/** The module name */
	private static final String MODULE = "offerTypeManager";

	/**
	 * This method will render the add new offerType page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newOfferType")
	public String addOfferType(ModelMap model) {
		LOG.info("Rendering the add new offerType page");
		try {
			/**
			 * Adding the blank OfferType object as model attribute for Form
			 */
			model.addAttribute("offerType", new OfferType());
			model.addAttribute(CommonConstants.PAGE_NAME, OFFER_TYPE_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new offerType Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_TYPE_FORM_JSP;
	}

	/**
	 * This method will persist the data in the database
	 * 
	 * @param offerType
	 *            The OfferType added on add offerType form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newOfferType", method = RequestMethod.POST)
	public String addOfferType(@Valid OfferType offerType, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the OfferType to the database");

		try {
			/**
			 * If there is any validation error, then reload the OfferType form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, OFFER_TYPE_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return OFFER_TYPE_FORM_JSP;
			}

			/** Save the OfferType in the database */
			service.saveOfferType(offerType);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the OfferType", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferTypes";
	}

	/**
	 * This method will get the list of offerTypes from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listOfferTypes")
	public String listAllOfferTypes(ModelMap model) {
		LOG.info("Loading the  offerTypes list page");
		try {
			/** Get the list of offerTypes from the database */
			List<OfferType> offerTypes = service.listAllOfferTypes();

			/**
			 * Adding the offerTypes list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("offerTypes", offerTypes);
			model.addAttribute(CommonConstants.PAGE_NAME, OFFER_TYPE_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the offerType listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_TYPE_LIST_JSP;
	}

	/**
	 * This method will update the OfferType details
	 * 
	 * @param id
	 *            Id of the OfferType, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateOfferType/{id}")
	public String updateOfferType(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update  OfferType page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The OfferType id to be updated [" + id + "]");
			}
			/** Get the OfferType entity by id from the database */
			OfferType offerType = service.getOfferTypeById(id);
			if (offerType == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("offerType", offerType);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_OFFER_TYPE_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the  OfferType", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_TYPE_FORM_JSP;
	}

	/**
	 * This method will update the OfferType data in the database
	 * 
	 * @param The
	 *            CARD details added on add OfferType form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateOfferType", method = RequestMethod.POST)
	public String updateOfferType(@Valid OfferType offerType, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the  OfferType details");
		try {
			/** Reload the update OfferType page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_OFFER_TYPE_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateOfferType/" + offerType.getId();
			}

			/** Updating the OfferType in the database */
			service.updateOfferType(offerType);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading OfferType Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferTypes";
	}

	/**
	 * This method will delete the OfferType from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the OfferType that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteOfferType/{id}")
	public String deleteOfferType(@PathVariable("id") int id) {
		LOG.info("Deleting the  OfferType from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The OfferType id to be deleted [" + id + "]");
			}
			/** Call to database to delete the OfferType */
			service.deleteOfferTypeById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the  OfferType", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferTypes";
	}
}
