package com.portal.deals.controller;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.deals.event.OnRegistrationCompleteEvent;
import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.model.User;
import com.portal.deals.model.UserRole;
import com.portal.deals.model.VerificationToken;
import com.portal.deals.service.TokenService;
import com.portal.deals.service.UserRoleService;
import com.portal.deals.service.UserService;

/**
 * This is the controller class for Registration flow
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class RegistrationController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	/** The JSP name for registration form */
	private static final String REGISTRATION_JSP = "registration";

	/** The JSP name for registration success page */
	private static final String REGISTRATION_SUCCESS_JSP = "registrationSuccess";

	/** The JSP name for invalidated user page */
	private static final String UNVALIDATED_USER_JSP = "unvalidated";

	/** The JSP name for verification success page */
	private static final String VERIFICATION_SUCCESS_JSP = "verificationSuccess";

	/** Token state */
	private static final String TOKEN_STATE = "tokenState";

	/** Message shown to user */
	private static final String MESSAGE = "message";

	/**
	 * Service class for communicating with DAO layer for USER specific DB
	 * operations
	 */
	@Autowired
	UserService userService;

	/**
	 * Service class for communicating with DAO layer for generating and
	 * fetching validation TOKEN
	 */
	@Autowired
	TokenService tokenService;

	/**
	 * Service class for communicating with DAO layer for getting USER Profiles
	 */
	@Autowired
	UserRoleService userRoleService;

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	MessageSource messageSource;

	/**
	 * Event to be published on Registration completion, this event will
	 * generate the validation token and send out email to customer
	 */
	@Autowired
	ApplicationEventPublisher eventPublisher;

	/**
	 * This method will render the registration page.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(ModelMap model) {
		LOG.info("Going to render registeration page ");
		try {
			/** Adding the command object for Spring form */
			User user = new User();
			model.addAttribute("user", user);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading registration Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return REGISTRATION_JSP;
	}

	/**
	 * This method will be called on registration form submission, handling POST
	 * request for saving user in database. It also validates the user input.
	 * 
	 * @param user
	 *            The User details filled by customer on registration form
	 * @param result
	 *            The binding result, from validation etc.
	 * @param model
	 *            The model to carry data
	 * @param request
	 *            HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) {
		LOG.info("Going to submit registeration page ");

		try {
			/** Render the registration page with errors, in case of any */
			if (result.hasErrors()) {
				return REGISTRATION_JSP;
			}

			/**
			 * Get roles from DB, in this case the USER role. Add the default
			 * role "USER" to the user getting registered
			 */
			UserRole role = userRoleService.findByType("USER");
			user.getUserRoles().add(role);

			/**
			 * Check if the user is unique, uniqueness is derived from the
			 * user's email
			 */
			if (!userService.isUserUnique(user.getId(), user.getEmail())) {
				/**
				 * User is not unique, so render the registration page with
				 * error
				 */
				FieldError error = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
						new String[] { user.getEmail() }, Locale.getDefault()));
				result.addError(error);
				if (LOG.isDebugEnabled()) {
					LOG.debug("User is not unique, reloading the registration page with errors");
				}
				return REGISTRATION_JSP;
			}
			/**
			 * Call User service, that will call underlying DAO layer to save
			 * User to Database.
			 */
			user.setEnabled(true);
			userService.saveUser(user);

			String messageValue = messageSource.getMessage("auth.message.account.created", null, Locale.getDefault());
			model.addAttribute(MESSAGE, messageValue);
			
			/** Generate validation token and send out email to User */
			generateToken(user, request);
		} catch (Exception ex) {
			LOG.error("Exception occured while registering the User", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return REGISTRATION_SUCCESS_JSP;
	}

	private void generateToken(User user, HttpServletRequest request) {
		/** Generate validation token and send out email to User */
		StringBuffer appUrl = new StringBuffer();
		if (request.isSecure()) {
			appUrl.append("https://");
		} else {
			appUrl.append("http://");
		}
		String serverName = request.getServerName();
		int portNumber = request.getServerPort();
		if (StringUtils.isEmpty(portNumber)) {
			appUrl.append(serverName).append(request.getContextPath());
		} else {
			appUrl.append(serverName).append(":").append(portNumber).append(request.getContextPath());
		}

		/** Register token generation and email sending event */
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl.toString()));
	}

	/**
	 * This method will be called when user hits the validation link from the
	 * email, token in the email will be validated and based on that user will
	 * be activated and registration process will be completed.
	 * 
	 * @param request
	 *            HTTP request
	 * @param model
	 *            The model to carry data
	 * @param token
	 *            The validation token to be verified
	 * @return
	 */
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(HttpServletRequest request, Model model, @RequestParam("token") String token) {
		LOG.info("Validating the registration token");

		try {
			/** Get the verification token object from Database */
			VerificationToken verificationToken = tokenService.getVerificationToken(token);
			if (verificationToken == null) {
				/**
				 * If the verification token not found in the Database, token is
				 * considered invalid
				 */
				String message = messageSource.getMessage("auth.message.invalidToken", null, Locale.getDefault());
				model.addAttribute(MESSAGE, message);
				model.addAttribute(TOKEN_STATE, "invalid");
				return UNVALIDATED_USER_JSP;
			}

			User user = verificationToken.getUser();
			Calendar cal = Calendar.getInstance();
			/** Check the expire duration for the token */
			if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				/**
				 * If the verification token is expired, set the message and
				 * redirect user
				 */
				String messageValue = messageSource.getMessage("auth.message.expired", null, Locale.getDefault());
				model.addAttribute(MESSAGE, messageValue);
				model.addAttribute(TOKEN_STATE, "expired");
				model.addAttribute("email", user.getEmail());
				return UNVALIDATED_USER_JSP;
			}

			if (user.isEnabled()) {
				String messageValue = messageSource.getMessage("auth.message.already.validated", null,
						Locale.getDefault());
				model.addAttribute(MESSAGE, messageValue);
				return VERIFICATION_SUCCESS_JSP;
			}
			/**
			 * Token is valid, so activating the User
			 */
			user.setEnabled(true);

			/**
			 * Updating the user activation status in the database
			 */
			userService.activateDeactivateUser(user);
			String messageValue = messageSource.getMessage("auth.message.validated", null, Locale.getDefault());
			model.addAttribute(MESSAGE, messageValue);
		} catch (Exception ex) {
			LOG.error("Exception occured while validating the token", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return VERIFICATION_SUCCESS_JSP;
	}

	/**
	 * This method will re-generate the validation token and send out the email
	 * 
	 * @param request
	 * @param model
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/reGenerateToken", method = RequestMethod.GET)
	public String regenerateToken(HttpServletRequest request, Model model, @RequestParam("email") String email) {
		LOG.info("Regenerating the token");

		try {
			if (StringUtils.isEmpty(email)) {
				String message = messageSource.getMessage("auth.message.invalidToken", null, Locale.getDefault());
				model.addAttribute(MESSAGE, message);
				return UNVALIDATED_USER_JSP;
			}
			User user = userService.findByEmail(email);
			/** Generate validation token and send out email to User */
			generateToken(user, request);

			String message = messageSource.getMessage("auth.message.regenerate.token", null, Locale.getDefault());
			model.addAttribute(MESSAGE, message);
		} catch (Exception ex) {
			LOG.error("Exception occured while regenerating the token", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return VERIFICATION_SUCCESS_JSP;
	}

}