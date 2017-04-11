package com.portal.deals.controller.bank;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Bank;
import com.portal.deals.model.Card;
import com.portal.deals.model.CardCategory;
import com.portal.deals.model.CardType;
import com.portal.deals.model.Category;
import com.portal.deals.model.UserDetails;
import com.portal.deals.service.CardCategoryService;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CardTypeService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.util.Utils;

/**
 * This is the controller class for card CRUD operation. Only BANK User will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@Secured("ROLE_BANK")
public class CardController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardServiceManager;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardCategoryService cardCategoryService;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardTypeService cardTypeService;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CategoryService categoryService;

	/** The JSP name for add new card page */
	private static final String CARD_FORM_JSP = "cardForm";

	/** The JSP name for add view card page */
	private static final String CARD_VIEW_JSP = "cardView";

	/** The JSP name for card list page */
	private static final String CARD_LIST_JSP = "cardList";

	/** Path where image will be uploaded */
	private static String UPLOAD_LOCATION = "/resources/upload/card";

	/** The update card page */
	private static final String UPDATE_CARD_FORM_JSP = "updateCardForm";

	/** The module name */
	private static final String MODULE = "cardManager";

	/** The module name */
	private static final String BANK_USER = "bankUser";

	/**
	 * This method will render the add new card page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCard")
	public String addCard(ModelMap model) {
		LOG.info("Rendering the add new card page");
		try {
			/** Adding the blank Card object as model attribute for Form */
			model.addAttribute("card", new Card());
			model.addAttribute(BANK_USER, true);
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new card Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_FORM_JSP;
	}

	/**
	 * This method will persist the card data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newCard", method = RequestMethod.POST)
	public String addCard(@Valid Card card, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the card to the database");

		try {
			/**
			 * If there is any validation error, then reload the card form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, CARD_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(BANK_USER, true);
				return CARD_FORM_JSP;
			}

			/** Upload the File */
			uploadImage(card, request);

			/** Get the logged in user */
			UserDetails user = Utils.getLoggedInUser();
			Bank bank = user.getBank();
			if (bank == null || StringUtils.isEmpty(bank.getId())) {
				throw new EntityNotFoundException("ErrorAddCard", "Bank not associated with this user");
			}
			/** Save the card in the database */
			card.setBank(bank);
			cardServiceManager.saveCard(card);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listCards";
	}

	/**
	 * This method will get the list of cards from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listCards")
	public String listAllCards(ModelMap model) {
		LOG.info("Loading the card list page");
		try {

			/** Get the logged in user */
			UserDetails user = Utils.getLoggedInUser();
			Bank bank = user.getBank();
			if (bank == null || StringUtils.isEmpty(bank.getId())) {
				throw new EntityNotFoundException("ErrorAddCard", "Bank not associated with this user");
			}
			/** Get the list of Cards from the database */
			List<Card> cards = cardServiceManager.listAllCardsByBank(bank.getId());

			/**
			 * Adding the card list to model, to be used for rendering in JSP
			 */
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute("cards", cards);
			model.addAttribute(BANK_USER, true);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the card listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_LIST_JSP;
	}

	/**
	 * This method will update the card details
	 * 
	 * @param id
	 *            Id of the card, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCard/{id}")
	public String updateCard(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading update card page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card id to be updated [" + id + "]");
			}
			/** Get the Card entity by id from the database */
			Card card = cardServiceManager.getCardById(id);
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddCard", "Card not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute("edit", true);
			model.addAttribute("card", card);
			model.addAttribute(BANK_USER, true);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_FORM_JSP;
	}

	/**
	 * This method will view the card details
	 * 
	 * @param id
	 *            Id of the card, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/viewCard/{id}")
	public String viewCard(@PathVariable("id") int id, ModelMap model) {
		LOG.info("Loading view card page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Card id to be updated [" + id + "]");
			}
			/** Get the Card entity by id from the database */
			Card card = cardServiceManager.getCardWithDetailsById(id);
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddCard", "Card not found");
			}
			model.addAttribute(CommonConstants.PAGE_NAME, CARD_VIEW_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute("card", card);
			model.addAttribute(BANK_USER, true);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the card", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return CARD_VIEW_JSP;
	}

	/**
	 * This method will update the card data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	public String updateCard(@Valid Card card, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the card details");
		try {
			/** Reload the update card page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_CARD_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(BANK_USER, true);
				return "redirect:/updateCard/" + card.getId();
			}

			/** Upload the File */
			uploadImage(card, request);

			/** Updating the Card in the database */
			cardServiceManager.updateCard(card, true);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading registration Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/listCards";
	}

	/**
	 * This method will upload the image
	 * 
	 * @param card
	 * @param request
	 * @throws IOException
	 */
	private void uploadImage(Card card, HttpServletRequest request) throws IOException {
		if (card.getImage() != null) {
			MultipartFile multipartFile = card.getImage();
			if (!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
				ServletContext context = request.getServletContext();
				String contextPath = context.getRealPath(UPLOAD_LOCATION);

				File directory = new File(contextPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				FileCopyUtils.copy(multipartFile.getBytes(),
						new File(contextPath + File.separator + multipartFile.getOriginalFilename()));
				card.setImagePath(multipartFile.getOriginalFilename());
			}
		}
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

	/**
	 * This method will provide Card Type list to views
	 */
	@ModelAttribute("cardTypes")
	public List<CardType> initializeCardTypes() {
		return cardTypeService.listAllCardTypes();
	}

	/**
	 * This method will provide Card Category list to views
	 */
	@ModelAttribute("cardCategories")
	public List<CardCategory> initializeCardCategories() {
		return cardCategoryService.listAllCardCategories();
	}

	/**
	 * This method will provide Category list to views
	 */
	@ModelAttribute("categories")
	public List<Category> initializeCategories() {
		return categoryService.listAllRootCategories();
	}

}
