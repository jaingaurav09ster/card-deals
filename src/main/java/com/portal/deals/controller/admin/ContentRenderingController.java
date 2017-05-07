package com.portal.deals.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.model.Content;
import com.portal.deals.service.ContentService;

/**
 * This is the controller class for rendering content pages
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class ContentRenderingController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(ContentRenderingController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * Content entity
	 */
	@Autowired
	private ContentService service;

	/** The JSP name for add new content page */
	private static final String CONTENT_RENDER_JSP = "contentRender";

	/**
	 * This method will render the content
	 * 
	 * @param id
	 *            Id of the content, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/page/{urlMapping}")
	public String renderContent(@PathVariable("urlMapping") String urlMapping, ModelMap model) {
		LOG.info("Loading update content page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Content id to be rendered [" + urlMapping + "]");
			}
			/** Get the Content entity by id from the database */
			Content content = service.getContentByUrlMapping(urlMapping);
			if (content == null) {
				throw new EntityNotFoundException("Error", "Page not found");
			}
			model.addAttribute("content", content);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the content", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CONTENT_RENDER_JSP;
	}

}
