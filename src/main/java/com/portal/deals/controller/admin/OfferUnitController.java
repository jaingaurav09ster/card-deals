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
import com.portal.deals.model.OfferUnit;
import com.portal.deals.service.OfferUnitService;

/**
 * This is the controller class for offerUnit CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class OfferUnitController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(OfferUnitController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private OfferUnitService service;

	/** The update new offerUnit page */
	private static final String UPDATE_OFFER_UNIT_FORM_JSP = "updateOfferUnitForm";

	/** The JSP name for add new offerUnit page */
	private static final String OFFER_UNIT_FORM_JSP = "offerUnitForm";

	/** The JSP name for offerUnit list page */
	private static final String OFFER_UNIT_LIST_JSP = "offerUnitList";

	/** The module name */
	private static final String MODULE = "offerUnitManager";

	/**
	 * This method will render the add new offerUnit page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newOfferUnit")
	public String addOfferUnit(ModelMap model) {
		LOG.info("Rendering the add new offerUnit page");
		try {
			/**
			 * Adding the blank OfferUnit object as model attribute for Form
			 */
			model.addAttribute("offerUnit", new OfferUnit());
			model.addAttribute(CommonConstants.PAGE_NAME, OFFER_UNIT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new offerUnit Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_UNIT_FORM_JSP;
	}

	/**
	 * This method will persist the data in the database
	 * 
	 * @param offerUnit
	 *            The OfferUnit added on add offerUnit form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newOfferUnit", method = RequestMethod.POST)
	public String addOfferUnit(@Valid OfferUnit offerUnit, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the OfferUnit to the database");

		try {
			/**
			 * If there is any validation error, then reload the OfferUnit form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, OFFER_UNIT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return OFFER_UNIT_FORM_JSP;
			}

			/** Save the OfferUnit in the database */
			service.saveOfferUnit(offerUnit);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the OfferUnit", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferUnits";
	}

	/**
	 * This method will get the list of offerUnits from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listOfferUnits")
	public String listAllOfferUnits(ModelMap model) {
		LOG.info("Loading the  offerUnits list page");
		try {
			/** Get the list of offerUnits from the database */
			List<OfferUnit> offerUnits = service.listAllOfferUnits();

			/**
			 * Adding the offerUnits list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("offerUnits", offerUnits);
			model.addAttribute(CommonConstants.PAGE_NAME, OFFER_UNIT_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the offerUnit listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_UNIT_LIST_JSP;
	}

	/**
	 * This method will update the OfferUnit details
	 * 
	 * @param id
	 *            Id of the OfferUnit, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateOfferUnit/{id}")
	public String updateOfferUnit(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update  OfferUnit page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The OfferUnit id to be updated [" + id + "]");
			}
			/** Get the OfferUnit entity by id from the database */
			OfferUnit offerUnit = service.getOfferUnitById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("offerUnit", offerUnit);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_OFFER_UNIT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the  OfferUnit", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return OFFER_UNIT_FORM_JSP;
	}

	/**
	 * This method will update the OfferUnit data in the database
	 * 
	 * @param The
	 *            CARD details added on add OfferUnit form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateOfferUnit", method = RequestMethod.POST)
	public String updateOfferUnit(@Valid OfferUnit offerUnit, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the  OfferUnit details");
		try {
			/** Reload the update OfferUnit page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_OFFER_UNIT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateOfferUnit/" + offerUnit.getId();
			}

			/** Updating the OfferUnit in the database */
			service.updateOfferUnit(offerUnit);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading OfferUnit Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferUnits";
	}

	/**
	 * This method will delete the OfferUnit from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the OfferUnit that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteOfferUnit/{id}")
	public String deleteOfferUnit(@PathVariable("id") int id) {
		LOG.info("Deleting the  OfferUnit from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The OfferUnit id to be deleted [" + id + "]");
			}
			/** Call to database to delete the OfferUnit */
			service.deleteOfferUnitById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the  OfferUnit", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listOfferUnits";
	}
}
