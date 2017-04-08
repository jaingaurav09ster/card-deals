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
import com.portal.deals.model.Document;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.DocumentService;

/**
 * This is the controller class for Document CRUD operation. Only ADMIN will
 * have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class DocumentController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private DocumentService service;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardService;

	/** The JSP name for add new document page */
	private static final String DEAL_FORM_JSP = "documentForm";

	/** The JSP name for add new document page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateDocumentForm";

	/** The JSP name for document list page */
	private static final String DEAL_LIST_JSP = "documentList";

	/** The module name */
	private static final String MODULE = "documentManager";

	/**
	 * This method will render the add new document page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newDocument/{id}")
	public String addDocument(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new document page");
		try {
			/**
			 * Adding the blank Document object as model attribute for Form
			 */
			model.addAttribute("document", new Document());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new document Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the document data in the database
	 * 
	 * @param document
	 *            category The CARD category added on add document form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newDocument/{id}", method = RequestMethod.POST)
	public String addDocument(@PathVariable("id") int cardId, @Valid Document document, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Document to the database");

		try {
			/**
			 * If there is any validation error, then reload the Document form
			 * page with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, document.getCardId());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(document.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddDocument", "Card not found");
			}
			/** Add the document to Card */
			document.setCard(card);

			/** Save the Document in the database */
			service.saveDocument(document);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Document", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDocuments/" + document.getCardId();
	}

	/**
	 * This method will get the list of document from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listDocuments/{id}")
	public String listAllDocuments(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the document  list page");
		try {
			List<Document> documents = service.getDocumentsByCardId(cardId);
			/**
			 * Adding the document list to model, to be used for rendering in
			 * JSP
			 */
			model.addAttribute("documents", documents);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the document listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the document details
	 * 
	 * @param id
	 *            Id of the document, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateDocument/{id}/{cardId}")
	public String updateDocument(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update document page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Document id to be updated [" + id + "]");
			}
			/** Get the Document entity by id from the database */
			Document document = service.getDocumentById(id);
			if (document == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("document", document);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the document", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the document data in the database
	 * 
	 * @param document
	 *            The CARD details added on add document form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateDocument/{cardId}", method = RequestMethod.POST)
	public String updateDocument(@PathVariable("cardId") int cardId, @Valid Document document, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the document details");
		try {
			/** Reload the update document page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, document.getCard());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return "redirect:/admin/updateDocument/" + document.getId() + "/" + document.getCardId();
			}

			/** Updating the Document in the database */
			service.updateDocument(document);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Document Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDocuments/" + document.getCardId();
	}

	/**
	 * This method will delete the document from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the document that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteDocument/{id}/{cardId}")
	public String deleteCard(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the document from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Document id to be deleted [" + id + "]");
			}
			/** Call to database to delete the document */
			service.deleteDocumentById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the document", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listDocuments/" + cardId;
	}
}
