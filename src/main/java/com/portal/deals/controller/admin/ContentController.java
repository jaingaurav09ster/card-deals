package com.portal.deals.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Content;
import com.portal.deals.service.ContentService;

/**
 * This is the controller class for Content CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class ContentController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private ContentService service;

	/** The JSP name for add new content page */
	private static final String CONTENT_FORM_JSP = "contentForm";

	/** The JSP name for add new content page */
	private static final String UPDATE_CONTENT_FORM_JSP = "updateContentForm";

	/** The JSP name for content list page */
	private static final String CONTENT_LIST_JSP = "contentList";

	/** The module name */
	private static final String MODULE = "contentManager";

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	MessageSource messageSource;

	/**
	 * This method will render the add new content page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newContent")
	public String addContent(ModelMap model) {
		LOG.info("Rendering the add new content page");
		try {
			/**
			 * Adding the blank Content object as model attribute for Form
			 */
			model.addAttribute("content", new Content());
			model.addAttribute(CommonConstants.PAGE_NAME, CONTENT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new content Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CONTENT_FORM_JSP;
	}

	/**
	 * This method will persist the content data in the database
	 * 
	 * @param content
	 *            category The CARD category added on add content form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newContent", method = RequestMethod.POST)
	public String addContent(@Valid Content content, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Content to the database");

		try {
			/**
			 * If there is any validation error, then reload the Content form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CONTENT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return CONTENT_FORM_JSP;
			}

			/** Checking if the email id entered is unique */
			if (service.getContentByUrlMapping(content.getUrlMapping()) != null) {
				FieldError error = new FieldError("content", "urlMapping",
						messageSource.getMessage("non.unique.url.mapping", null, Locale.getDefault()));
				result.addError(error);
				model.addAttribute(CommonConstants.PAGE_NAME, CONTENT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return CONTENT_FORM_JSP;
			}

			/** Save the Content in the database */
			service.saveContent(content);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Content", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listContents";
	}

	/**
	 * This method will get the list of content from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listContents")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the content  list page");
		try {
			/** Get the list of Card from the database */
			List<Content> contents = service.listAllContents();

			/**
			 * Adding the content list to model, to be used for rendering in JSP
			 */
			model.addAttribute("contents", contents);
			model.addAttribute(CommonConstants.PAGE_NAME, CONTENT_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the content listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CONTENT_LIST_JSP;
	}

	/**
	 * This method will update the content details
	 * 
	 * @param id
	 *            Id of the content, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateContent/{id}")
	public String updateContent(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update content page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Content id to be updated [" + id + "]");
			}
			/** Get the Content entity by id from the database */
			Content content = service.getContentById(id);
			if (content == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute("content", content);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CONTENT_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the content", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CONTENT_FORM_JSP;
	}

	/**
	 * This method will update the content data in the database
	 * 
	 * @param content
	 *            The CARD details added on add content form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateContent", method = RequestMethod.POST)
	public String updateContent(@Valid Content content, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Updating the content details");
		try {
			/** Reload the update content page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CONTENT_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return "redirect:/admin/updateContent/" + content.getId();
			}

			/** Updating the Content in the database */
			service.updateContent(content);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Content Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listContents";
	}

	/**
	 * This method will delete the content from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the content that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteContent/{id}")
	public String deleteContent(@PathVariable("id") int id) {
		LOG.info("Deleting the content from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Content id to be deleted [" + id + "]");
			}
			/** Call to database to delete the content */
			service.deleteContentById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the content", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listContents";
	}

}
