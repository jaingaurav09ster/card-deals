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
import com.portal.deals.model.Card;
import com.portal.deals.model.Fees;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.FeesService;

/**
 * This is the controller class for Fees CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class FeesController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(FeesController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private FeesService service;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardService;

	/** The JSP name for add new fees page */
	private static final String DEAL_FORM_JSP = "feesForm";

	/** The JSP name for add new fees page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateFeesForm";

	/** The JSP name for fees list page */
	private static final String DEAL_LIST_JSP = "feesList";

	/** The module name */
	private static final String MODULE = "feesManager";

	/**
	 * This method will render the add new fees page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newFees/{id}")
	public String addFees(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new fees page");
		try {
			/**
			 * Adding the blank Fees object as model attribute for Form
			 */
			model.addAttribute("fees", new Fees());
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new fees Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the fees data in the database
	 * 
	 * @param fees
	 *            category The CARD category added on add fees form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newFees/{id}", method = RequestMethod.POST)
	public String addFees(@PathVariable("id") int cardId, @Valid Fees fees, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the Fees to the database");

		try {
			/**
			 * If there is any validation error, then reload the Fees form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, fees.getCardId());
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(fees.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddFees", "Card not found");
			}
			/** Add the fees to Card */
			fees.setCard(card);

			/** Save the Fees in the database */
			service.saveFees(fees);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Fees", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listFees/" + fees.getCardId();
	}

	/**
	 * This method will get the list of fees from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listFees/{id}")
	public String listFees(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the fees  list page");
		try {
			List<Fees> fees = service.getFeesByCardId(cardId);
			/**
			 * Adding the fees list to model, to be used for rendering in JSP
			 */
			model.addAttribute("fees", fees);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the fees listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the fees details
	 * 
	 * @param id
	 *            Id of the fees, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateFees/{cardId}")
	public String updateFees(@PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update fees page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Fees id to be updated for [" + cardId + "]");
			}
			/** Get the Fees entity by id from the database */
			Card card = cardService.getCardById(cardId);
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddFees", "Card not found");
			}
			Fees fees = card.getFees();
			if (fees == null) {
				return "redirect:/admin/newFees/" + card.getId();
			}

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("fees", fees);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the fees", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the fees data in the database
	 * 
	 * @param fees
	 *            The CARD details added on add fees form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateFees/{cardId}", method = RequestMethod.POST)
	public String updateFees(@PathVariable("cardId") int cardId, @Valid Fees fees, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the fees details");
		try {
			/** Reload the update fees page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, fees.getCard());
				return "redirect:/admin/updateFees/" + fees.getId() + "/" + fees.getCardId();
			}

			/** Updating the Fees in the database */
			service.updateFees(fees);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Fees Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listFees/" + fees.getCardId();
	}

	/**
	 * This method will delete the fees from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the fees that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteFees/{id}/{cardId}")
	public String deleteFees(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the fees from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Fees id to be deleted [" + id + "]");
			}
			/** Call to database to delete the fees */
			service.deleteFeesById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the fees", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listFees/" + cardId;
	}
}
