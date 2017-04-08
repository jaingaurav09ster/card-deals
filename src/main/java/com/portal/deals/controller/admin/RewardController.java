package com.portal.deals.controller.admin;

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
import com.portal.deals.model.Reward;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.CategoryService;
import com.portal.deals.service.RewardService;

/**
 * This is the controller class for Reward CRUD operation. Only ADMIN will have
 * access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class RewardController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RewardController.class);

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * DEAL entity
	 */
	@Autowired
	private RewardService service;
	
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

	/** The JSP name for add new reward page */
	private static final String DEAL_FORM_JSP = "rewardForm";

	/** The JSP name for add new reward page */
	private static final String UPDATE_DEAL_FORM_JSP = "updateRewardForm";

	/** The JSP name for reward list page */
	private static final String DEAL_LIST_JSP = "rewardList";

	/** The module name */
	private static final String MODULE = "rewardManager";

	/**
	 * This method will render the add new reward page
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newReward/{id}")
	public String addReward(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Rendering the add new reward page");
		try {
			/**
			 * Adding the blank Reward object as model attribute for Form
			 */
			model.addAttribute("reward", new Reward());
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new reward Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will persist the reward data in the database
	 * 
	 * @param reward
	 *            category The CARD category added on add reward form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/newReward/{id}", method = RequestMethod.POST)
	public String addReward(@PathVariable("id") int cardId, @Valid Reward reward, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Saving the Reward to the database");

		try {
			/**
			 * If there is any validation error, then reload the Reward form page
			 * with relevant errors
			 */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, reward.getCardId());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return DEAL_FORM_JSP;
			}

			/** Get the Card from the database */
			Card card = cardService.getCardById(reward.getCardId());
			if (card == null) {
				throw new EntityNotFoundException("ErrorAddReward", "Card not found");
			}
			/** Add the reward to Card */
			reward.setCard(card);

			/** Save the Reward in the database */
			service.saveReward(reward);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the Reward", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listRewards/" + reward.getCardId();
	}

	/**
	 * This method will get the list of reward from the database.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/listRewards/{id}")
	public String listAllRewards(@PathVariable("id") int cardId, ModelMap model) {
		LOG.info("Loading the reward  list page");
		try {
			List<Reward> rewards = service.getRewardsByCardId(cardId);
			/**
			 * Adding the reward list to model, to be used for rendering in JSP
			 */
			model.addAttribute("rewards", rewards);
			model.addAttribute(CommonConstants.PAGE_NAME, DEAL_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the reward listing Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_LIST_JSP;
	}

	/**
	 * This method will update the reward details
	 * 
	 * @param id
	 *            Id of the reward, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateReward/{id}/{cardId}")
	public String updateReward(@PathVariable("id") int id, @PathVariable("cardId") int cardId, ModelMap model) {
		LOG.info("Loading update reward page");

		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Reward id to be updated [" + id + "]");
			}
			/** Get the Reward entity by id from the database */
			Reward reward = service.getRewardById(id);
			if (reward == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			/** Add edit to true, to identify the request is coming from edit */
			model.addAttribute("edit", true);
			model.addAttribute(CommonConstants.CARD_ID, cardId);
			model.addAttribute("reward", reward);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the reward", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return DEAL_FORM_JSP;
	}

	/**
	 * This method will update the reward data in the database
	 * 
	 * @param reward
	 *            The CARD details added on add reward form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = "/updateReward/{cardId}", method = RequestMethod.POST)
	public String updateReward(@PathVariable("cardId") int cardId, @Valid Reward reward, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Updating the reward details");
		try {
			/** Reload the update reward page in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_DEAL_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				model.addAttribute(CommonConstants.CARD_ID, reward.getCard());
				model.addAttribute(CommonConstants.CARD_NAME, cardService.getCardName(cardId));
				return "redirect:/admin/updateReward/" + reward.getId() + "/" + reward.getCardId();
			}

			/** Updating the Reward in the database */
			service.updateReward(reward);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Reward Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listRewards/" + reward.getCardId();
	}

	/**
	 * This method will delete the reward from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the reward that has to be deleted
	 * @return the redirect value
	 */
	@RequestMapping(value = "/deleteReward/{id}/{cardId}")
	public String deleteCard(@PathVariable("id") int id, @PathVariable("cardId") int cardId) {
		LOG.info("Deleting the reward from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The Reward id to be deleted [" + id + "]");
			}
			/** Call to database to delete the reward */
			service.deleteRewardById(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the reward", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listRewards/" + cardId;
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
