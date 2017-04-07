package com.portal.deals.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Bank;
import com.portal.deals.model.Sector;
import com.portal.deals.service.BankService;

/**
 * This is the controller class for Bank CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class BankController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(BankController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private BankService service;

	/** The JSP name for add new bank page */
	private static final String BANK_FORM_JSP = "bankForm";

	/** The JSP name for add new bank page */
	private static final String UPDATE_BANK_FORM_JSP = "updateBankForm";

	/** The JSP name for bank list page */
	private static final String BANK_LIST_JSP = "bankList";

	/** The module name */
	private static final String MODULE = "bankManager";

	/**
	 * This method will render the add new bank page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newBank")
	public String addBank(ModelMap model) {
		LOG.info("Rendering the add new bank page");
		try {
			/**
			 * Adding the blank Bank object as model attribute for Form
			 */
			model.addAttribute("bank", new Bank());
			model.addAttribute(CommonConstants.PAGE_NAME, BANK_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new bank Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return BANK_FORM_JSP;
	}

	/**
	 * This method will persist the bank data in the database
	 * 
	 * @param bank
	 *            category The CARD category added on add bank form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newBank", method = RequestMethod.POST)
	public String addBank(@Valid Bank bank, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Bank to the database");

		try {
			/**
			 * If there is any validation error, then reload the Bank form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, BANK_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return BANK_FORM_JSP;
			}

			/** Save the Bank in the database */
			service.saveBank(bank);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Bank", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listBanks";
	}

	/**
	 * This method will get the list of bank from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listBanks")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the bank  list page");
		try {
			/** Get the list of Card from the database */
			List<Bank> banks = service.listAllBanks();

			/**
			 * Adding the bank list to model, to be used for rendering in JSP
			 */
			model.addAttribute("banks", banks);
			model.addAttribute(CommonConstants.PAGE_NAME, BANK_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the bank listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return BANK_LIST_JSP;
	}

	/**
	 * This method will update the bank details
	 * 
	 * @param id
	 *            Id of the bank, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateBank/{id}")
	public String updateBank(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update bank page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Bank id to be updated [" + id + "]");
			}
			/** Get the Bank entity by id from the database */
			Bank bank = service.getBankById(id);

			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("bank", bank);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_BANK_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the bank", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return BANK_FORM_JSP;
	}

	/**
	 * This method will update the bank data in the database
	 * 
	 * @param bank
	 *            The CARD details added on add bank form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateBank", method = RequestMethod.POST)
	public String updateBank(@Valid Bank bank, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the bank details");
		try {
			/** Reload the update bank page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_BANK_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateBank/" + bank.getId();
			}

			/** Updating the Bank in the database */
			service.updateBank(bank);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Bank Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listBanks";
	}

	/**
	 * This method will delete the bank from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the bank that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteBank/{id}")
	public String deleteBank(@PathVariable("id") int id) {
		LOG.info("Deleting the bank from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Bank id to be deleted [" + id + "]");
			}
			/** Call to database to delete the bank */
			service.deleteBankById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the bank", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listBanks";
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("sectors")
	public List<Sector> initializeSectors() {
		List<Sector> sectors = new ArrayList<>();
		sectors.add(new Sector("Public-Sector Bank"));
		sectors.add(new Sector("Private-Sector Bank"));
		sectors.add(new Sector("Foreign Bank"));
		sectors.add(new Sector("Regional Rural Bank"));
		sectors.add(new Sector("Cooperative Bank"));
		sectors.add(new Sector("Urban Cooperative Bank"));
		return sectors;
	}

}
