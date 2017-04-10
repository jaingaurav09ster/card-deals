package com.portal.deals.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.portal.deals.controller.RegistrationController;
import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.EntityNotFoundException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.CommonConstants;
import com.portal.deals.model.Bank;
import com.portal.deals.model.User;
import com.portal.deals.model.UserRole;
import com.portal.deals.service.BankService;
import com.portal.deals.service.UserRoleService;
import com.portal.deals.service.UserService;

/**
 * This is the controller class for User management, it will handle CRUD
 * functionality on USER entity. Only ADMIN will have access to this controller
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("roles")
public class AdminUserController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	/** The JSP name for add new user page */
	private static final String USER_FORM_JSP = "userForm";

	/** The update user page */
	private static final String UPDATE_USER_FORM_JSP = "updateUserForm";

	/** The JSP name for user list page */
	private static final String USER_LIST_JSP = "userList";

	/** The module name */
	private static final String MODULE = "userManager";

	/**
	 * Service class for communicating with DAO layer for USER specific DB
	 * operations
	 */
	@Autowired
	UserService userService;

	/**
	 * Service class for communicating with DAO layer for USER Profile specific
	 * DB operations
	 */
	@Autowired
	UserRoleService userRoleService;

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	MessageSource messageSource;

	/**
	 * Service class for communicating with DAO layer for CRUD operation for
	 * CARD entity
	 */
	@Autowired
	private BankService bankService;

	/**
	 * This method will list all existing users.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/", "/listUsers", "" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		LOG.info("Loading User list page");
		try {
			/** Get the user list from database */
			List<User> users = userService.findAllUsers();
			model.addAttribute("users", users);
			model.addAttribute(CommonConstants.PAGE_NAME, USER_LIST_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading user list Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return USER_LIST_JSP;
	}

	/**
	 * This method will render add new user page.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		LOG.info("Loading add new user page");
		try {

			/** Adding blank model attribute to be used on form */
			User user = new User();
			model.addAttribute("user", user);
			model.addAttribute(CommonConstants.PAGE_NAME, USER_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading add new user Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return USER_FORM_JSP;
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 * 
	 * @param user
	 *            The USER details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
	public String newUser(@Valid User user, BindingResult result, ModelMap model) {
		LOG.info("Submitting the user details to database");

		try {
			/** Reload the form JSP, in case of any validation errors */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, USER_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return USER_FORM_JSP;
			}
			user.setEnabled(true);
			/** Checking if the email id entered is unique */
			if (!userService.isUserUnique(user.getId(), user.getEmail())) {
				FieldError error = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
						new String[] { user.getEmail() }, Locale.getDefault()));
				result.addError(error);
				model.addAttribute(CommonConstants.PAGE_NAME, USER_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return USER_FORM_JSP;
			}

			Bank bank = null;
			if (!StringUtils.isEmpty(user.getBank().getId())) {
				bank = bankService.getBankById(user.getBank().getId());
			}

			user.setBank(bank);
			/** Saving the user in the database */
			userService.saveUser(user);
		} catch (Exception ex) {
			LOG.error("Exception occured while saving the user to database", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listUsers";
	}

	/**
	 * This method will provide the medium to update an existing user, it will
	 * render the update user page with populated details
	 * 
	 * @param id
	 *            Id of the User, that has to be updated
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/updateUser/{id}" }, method = RequestMethod.GET)
	public String updateUser(@PathVariable String id, ModelMap model) {
		LOG.info("Loading the update user page");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The User id to be updated [" + id + "]");
			}
			/** Get the user to be updated from database */
			User user = userService.findByEmail(id);
			if (user == null) {
				throw new EntityNotFoundException("Error", "Entity not found");
			}
			model.addAttribute("user", user);
			model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_USER_FORM_JSP);
			model.addAttribute(CommonConstants.MODULE, MODULE);
			/** Identifier for Edit user flow */
			model.addAttribute("edit", true);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading the update user page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return USER_FORM_JSP;
	}

	/**
	 * This method will update the user data in the database
	 * 
	 * @param card
	 *            The CARD details added on add card form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model) {
		LOG.info("Updating the user profile in database");
		try {
			/** Edit user flow identifier */
			model.addAttribute("edit", true);
			/** Reload the User update form in case of any error */
			if (result.hasErrors()) {
				model.addAttribute(CommonConstants.PAGE_NAME, UPDATE_USER_FORM_JSP);
				model.addAttribute(CommonConstants.MODULE, MODULE);
				return USER_FORM_JSP;
			}
			/** Update the user in database */
			userService.updateUserByAdmin(user);
		} catch (Exception ex) {
			LOG.error("Exception occured while updating the user", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listUsers";
	}

	/**
	 * This method will delete the user from the database, based on the id
	 * passed
	 * 
	 * @param id
	 *            The id of the card that has to be deleted
	 * @return the redirect value
	 */

	@RequestMapping(value = { "/deleteUser/{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id) {
		LOG.info("Deleting the user from database");
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The User id to be deleted [" + id + "]");
			}
			/** Delete the user from database */
			userService.deleteUserByEmail(id);
		} catch (Exception ex) {
			LOG.error("Exception occured while deleting the user", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return "redirect:/admin/listUsers";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserRole> initializeProfiles() {
		return userRoleService.findAll();
	}

	/**
	 * This method will provide Bank list to views
	 */
	@ModelAttribute("banks")
	public List<Bank> initializeBanks() {
		return bankService.listAllBanks();
	}

}