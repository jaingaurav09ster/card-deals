package com.portal.deals.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.exception.UserNotFoundException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Card;
import com.portal.deals.model.User;
import com.portal.deals.model.UserDetails;
import com.portal.deals.model.UserRole;
import com.portal.deals.service.CardManagerService;
import com.portal.deals.service.UserService;
import com.portal.deals.util.Utils;

/**
 * This Controller will handle Edit profile flow.
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping("/user")
public class EditProfileController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(EditProfileController.class);

	/** Session key to hold User object in session */
	private static final String USER_SESSION = "userSession";

	/** The JSP name for edit profile form page */
	private static final String EDIT_PROFILE_JSP = "editProfile";

	/** The JSP name for edit profile form page */
	private static final String EDIT_PROFILE__ADMIN_JSP = "editProfileAdmin";

	/** The JSP name for edit profile form page */
	private static final String EDIT_PROFILE__BANK_JSP = "editProfileBank";

	/** The JSP name for edit profile form page */
	private static final String ADD_CARD_JSP = "addUserCard";

	/**
	 * Service class for communicating with DAO layer for USER specific DB
	 * operations
	 */
	@Autowired
	UserService userService;

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	MessageSource messageSource;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private CardManagerService cardServiceManager;

	/**
	 * This method will render the Edit Profile page, it will populate the
	 * User's existing profile.
	 * 
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            The HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.GET)
	public String editProfile(ModelMap model, HttpServletRequest request) {
		LOG.info("Going to render edit profile page ");

		String view = EDIT_PROFILE_JSP;
		try {
			/** Get the logged in User from database */
			UserDetails userDetails = Utils.getLoggedInUser();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Logged In User's Email [" + userDetails.getUsername() + "]");
			}

			Set<UserRole> roles = userDetails.getUserRoles();
			if (roles != null && roles.size() > 0) {
				UserRole role = roles.iterator().next();
				if (CommonConstants.ADMIN_ROLE.equalsIgnoreCase(role.getType())) {
					view = EDIT_PROFILE__ADMIN_JSP;
				} else if (CommonConstants.BANK_ROLE.equalsIgnoreCase(role.getType())) {
					view = EDIT_PROFILE__BANK_JSP;
				}
			}
			User user = userService.findByEmail(userDetails.getUsername());
			if (user == null) {
				throw new UserNotFoundException("ErrorEditProfile", "User not found");
			}
			/**
			 * Set the User object in session to be used during update on page
			 * submit
			 */
			request.getSession().setAttribute(USER_SESSION, user);
			/** Add the User to model to be populated on the JSP page */
			model.addAttribute("user", user);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return view;
	}

	/**
	 * This method will render the Edit Profile page, it will populate the
	 * User's existing profile.
	 * 
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            The HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/addCard" }, method = RequestMethod.GET)
	public String addCard(ModelMap model, HttpServletRequest request) {
		LOG.info("Going to render add Card page ");

		String view = ADD_CARD_JSP;
		try {

			/** Get the logged in User from database */
			UserDetails userDetails = Utils.getLoggedInUser();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Logged In User's Email [" + userDetails.getUsername() + "]");
			}

			User user = userService.findByEmail(userDetails.getUsername());
			if (user == null) {
				throw new UserNotFoundException("ErrorEditProfile", "User not found");
			}
			/**
			 * Set the User object in session to be used during update on page
			 * submit
			 */
			request.getSession().setAttribute(USER_SESSION, user);
			model.addAttribute("user", user);
			model.addAttribute("error", request.getParameter("err"));
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return view;
	}

	/**
	 * This method will be called on edit profile form submission, handling POST
	 * request for saving user in database. It also validates the user input.
	 * 
	 * @param user
	 *            The User details filled by customer on edit profile form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.POST)
	public String editProfile(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Submitting Edit Profile page");

		String view = EDIT_PROFILE_JSP;
		String redirect = EDIT_PROFILE_JSP;

		try {
			/**
			 * Get the user from Session and set the fields that can be modified
			 * by the user
			 */
			User userFromSession = (User) request.getSession().getAttribute(USER_SESSION);

			Set<UserRole> roles = userFromSession.getUserRoles();
			if (roles != null && roles.size() > 0) {
				UserRole role = roles.iterator().next();
				if (CommonConstants.ADMIN_ROLE.equalsIgnoreCase(role.getType())) {
					view = EDIT_PROFILE__ADMIN_JSP;
					redirect = "console";
				} else if (CommonConstants.BANK_ROLE.equalsIgnoreCase(role.getType())) {
					view = EDIT_PROFILE__BANK_JSP;
					redirect = "bank";
				}
			}
			/**
			 * If there is any error during form validation, then render the
			 * edit profile page with errors
			 */
			if (result.hasErrors()) {
				return view;
			}

			userFromSession.setFirstName(user.getFirstName());
			userFromSession.setLastName(user.getLastName());
			userFromSession.setPassword(user.getPassword());
			userFromSession.setMobile(user.getMobile());

			/**
			 * Updating the user details in database, that will call underlying
			 * DAO layer
			 */
			userService.updateUser(userFromSession);

		} catch (Exception ex) {
			LOG.error("Exception occured while submitting Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/" + redirect;
	}

	/**
	 * This method will be called on edit profile form submission, handling POST
	 * request for saving user in database. It also validates the user input.
	 * 
	 * @param user
	 *            The User details filled by customer on edit profile form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/addCard" }, method = RequestMethod.POST)
	public String addCard(HttpServletRequest request) {
		LOG.info("Submitting Edit Profile page");

		try {
			/**
			 * Get the user from Session and set the fields that can be modified
			 * by the user
			 */
			User userFromSession = (User) request.getSession().getAttribute(USER_SESSION);

			String cardName = request.getParameter("cardName");
			Card card = cardServiceManager.getCardByTitle(cardName);
			if (card == null) {
				return "redirect:/user/addCard?err=err";
			}
			userFromSession.getCards().add(card);
			/**
			 * Updating the user details in database, that will call underlying
			 * DAO layer
			 */
			userService.updateUser(userFromSession);
		} catch (Exception ex) {
			LOG.error("Exception occured while submitting Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/user/addCard";
	}

	/**
	 * This method will be called on edit profile form submission, handling POST
	 * request for saving user in database. It also validates the user input.
	 * 
	 * @param user
	 *            The User details filled by customer on edit profile form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/deleteCard/{id}" }, method = RequestMethod.GET)
	public String deleteCard(@PathVariable("id") int id, HttpServletRequest request) {
		LOG.info("Submitting delete card page");

		try {
			/**
			 * Get the user from Session and set the fields that can be modified
			 * by the user
			 */
			User userFromSession = (User) request.getSession().getAttribute(USER_SESSION);

			Card card = cardServiceManager.getCardById(id);
			if (card == null) {
				return "redirect:/user/addCard?err=err";
			}
			userFromSession.getCards().remove(card);
			/**
			 * Updating the user details in database, that will call underlying
			 * DAO layer
			 */
			userService.updateUser(userFromSession);
		} catch (Exception ex) {
			LOG.error("Exception occured while submitting Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/user/addCard";
	}

	/**
	 * This method will render the Edit Profile page, it will populate the
	 * User's existing profile.
	 * 
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            The HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/myDeals" }, method = RequestMethod.GET)
	public String myDeals(ModelMap model, HttpServletRequest request) {
		LOG.info("Going to render edit profile page ");
		String query = "";
		try {
			/** Get the logged in User from database */
			UserDetails userDetails = Utils.getLoggedInUser();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Logged In User's Email [" + userDetails.getUsername() + "]");
			}

			User user = userService.findByEmail(userDetails.getUsername());
			if (user == null) {
				throw new UserNotFoundException("ErrorEditProfile", "User not found");
			}
			if (user.getCards() != null && user.getCards().size() > 0) {
				query = "card:";
				int index = 1;
				for (Card card : user.getCards()) {
					if (index == user.getCards().size()) {
						query = query + card.getId();
					} else {
						query = query + card.getId() + ",";
					}
					index++;
				}
			}
		} catch (Exception ex) {
			LOG.error("Exception occured while loading Edit Profile Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/searchDeals?query=" + query;
	}
}