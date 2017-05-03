package com.portal.deals.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Merchant;
import com.portal.deals.service.MerchantService;

/**
 * This is the controller class for Merchant CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class MerchantController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(MerchantController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private MerchantService service;

	/** The JSP name for add new merchant page */
	private static final String MERCHANT_FORM_JSP = "merchantForm";

	/** The JSP name for add new merchant page */
	private static final String UPDATE_MERCHANT_FORM_JSP = "updateMerchantForm";

	/** The JSP name for merchant list page */
	private static final String MERCHANT_LIST_JSP = "merchantList";

	/** The module name */
	private static final String MODULE = "merchantManager";

	/** Path where image will be uploaded */
	private static String UPLOAD_LOCATION = "/resources/upload/merchant";

	/**
	 * This method will render the add new merchant page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newMerchant")
	public String addMerchant(ModelMap model) {
		LOG.info("Rendering the add new merchant page");
		try {
			/**
			 * Adding the blank Merchant object as model attribute for Form
			 */
			model.addAttribute("merchant", new Merchant());
			model.addAttribute(CommonConstants.PAGE_NAME, MERCHANT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new merchant Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return MERCHANT_FORM_JSP;
	}

	/**
	 * This method will persist the merchant data in the database
	 * 
	 * @param merchant
	 *            category The CARD category added on add merchant form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newMerchant", method = RequestMethod.POST)
	public String addMerchant(@Valid Merchant merchant, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Saving the Merchant to the database");

		try {
			/**
			 * If there is any validation error, then reload the Merchant form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, MERCHANT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return MERCHANT_FORM_JSP;
			}

			/** Upload the File */
			uploadImage(merchant, request);

			/** Save the Merchant in the database */
			service.saveMerchant(merchant);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Merchant", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listMerchants";
	}

	/**
	 * This method will get the list of merchant from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listMerchants")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the merchant  list page");
		try {
			/** Get the list of Card from the database */
			List<Merchant> merchants = service.listAllMerchants();

			/**
			 * Adding the merchant list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("merchants", merchants);
			model.addAttribute(CommonConstants.PAGE_NAME, MERCHANT_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the merchant listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return MERCHANT_LIST_JSP;
	}

	/**
	 * This method will update the merchant details
	 * 
	 * @param id
	 *            Id of the merchant, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateMerchant/{id}")
	public String updateMerchant(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update merchant page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Merchant id to be updated [" + id + "]");
			}
			/** Get the Merchant entity by id from the database */
			Merchant merchant = service.getMerchantById(id);
			if (merchant == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("merchant", merchant);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_MERCHANT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the merchant", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return MERCHANT_FORM_JSP;
	}

	/**
	 * This method will update the merchant data in the database
	 * 
	 * @param merchant
	 *            The CARD details added on add merchant form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateMerchant", method = RequestMethod.POST)
	public String updateMerchant(@Valid Merchant merchant, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the merchant details");
		try {
			/** Reload the update merchant page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_MERCHANT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateMerchant/" + merchant.getId();
			}

			/** Upload the File */
			uploadImage(merchant, request);

			/** Updating the Merchant in the database */
			service.updateMerchant(merchant);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Merchant Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listMerchants";
	}

	/**
	 * This method will delete the merchant from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the merchant that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteMerchant/{id}")
	public String deleteMerchant(@PathVariable("id") int id) {
		LOG.info("Deleting the merchant from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Merchant id to be deleted [" + id + "]");
			}
			/** Call to database to delete the merchant */
			service.deleteMerchantById(id);

		} catch (DataIntegrityViolationException ex) {
			LOG.error("Exception occured while deleting the merchant", ex);
			return "redirect:/admin/listMerchants?err=dberr";
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the merchant", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listMerchants";
	}

	/**
	 * This method will upload the image
	 * 
	 * @param card
	 * @param request
	 * @throws IOException
	 */
	private void uploadImage(Merchant merchant, HttpServletRequest request) throws IOException {
		if (merchant.getImage() != null) {
			MultipartFile multipartFile = merchant.getImage();
			if (!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
				ServletContext context = request.getServletContext();
				String contextPath = context.getRealPath(UPLOAD_LOCATION);

				File directory = new File(contextPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				FileCopyUtils.copy(multipartFile.getBytes(),
						new File(contextPath + File.separator + multipartFile.getOriginalFilename()));
				merchant.setImagePath(multipartFile.getOriginalFilename());
			}
		}
	}

}
