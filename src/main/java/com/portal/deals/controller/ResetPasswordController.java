package com.portal.deals.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.deals.exception.BaseException;
import com.portal.deals.exception.GenericException;
import com.portal.deals.form.ForgotPasswordForm;
import com.portal.deals.form.ResetPasswordForm;
import com.portal.deals.model.EmailParams;
import com.portal.deals.model.EmailTemplate;
import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.User;
import com.portal.deals.service.MailService;
import com.portal.deals.service.TokenService;
import com.portal.deals.service.UserService;
import com.portal.deals.util.Utils;

/**
 * This is the controller class for Reset Password flow
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class ResetPasswordController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordController.class);

	/** The JSP name for forgot password form */
	private static final String FORGOT_PASSWORD_JSP = "forgotPassword";

	/** The JSP name for forgot password success form */
	private static final String FORGOT_PASSWORD_SUCCESS_JSP = "forgotPasswordSuccess";

	/** The JSP name for reset password page */
	private static final String RESET_PASSWORD_JSP = "resetPassword";

	/** The JSP name for reset password success page */
	private static final String RESET_PASSWORD_SUCCESS_JSP = "resetPasswordSuccess";

	/** The JSP name for reset password success page */
	private static final String RESET_PASSWORD_ERROR_JSP = "resetPasswordError";

	/** The Expiration time for token */
	private static final int EXPIRATION = 60 * 24;

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
	 * Service class for sending out email
	 */
	@Autowired
	private MailService mailService;

	/**
	 * Service class for communicating with DAO layer for generating and
	 * fetching validation TOKEN
	 */
	@Autowired
	TokenService tokenService;

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	MessageSource messageSource;

	/**
	 * This method will render the forgot password page.
	 * 
	 * @param model
	 *            The model to carry data
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/forgotPassword" }, method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		LOG.info("Going to render forgot password page ");

		try {
			/** Adding the blank form object */
			ForgotPasswordForm form = new ForgotPasswordForm();
			model.addAttribute("forgotPasswordForm", form);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading forgot password Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return FORGOT_PASSWORD_JSP;
	}

	/**
	 * This method will be called on forgot password form submission, it will
	 * generate the token and send the email.
	 * 
	 * @param email
	 *            The email entered by User
	 * @param request
	 *            HTTP request object
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/forgotPassword" }, method = RequestMethod.POST)
	public String submitForgotPassword(@Valid ForgotPasswordForm form, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Going to submit forgot password page ");

		try {
			/**
			 * In case of any error, render the forgot password page with error
			 */
			if (result.hasErrors()) {
				return FORGOT_PASSWORD_JSP;
			}
			/** Get the user from DB, based on email */
			User user = userService.findByEmail(form.getEmail());
			if (user == null) {
				/** User not found, so adding error message and redirecting */
				String message = messageSource.getMessage("forgot.password.error", null, Locale.getDefault());
				model.addAttribute(MESSAGE, message);
				return FORGOT_PASSWORD_SUCCESS_JSP;
			}
			/** Generate token and persist the token in database */
			String token = UUID.randomUUID().toString();
			PasswordResetToken passwordResetToken = null;
			if (user.getPasswordResettoken() == null) {
				passwordResetToken = new PasswordResetToken();
			} else {
				passwordResetToken = user.getPasswordResettoken();
			}
			passwordResetToken.setExpiryDate(Utils.getExpiryDate(EXPIRATION));
			passwordResetToken.setToken(token);
			passwordResetToken.setUser(user);
			tokenService.createPasswordResetToken(passwordResetToken);

			/** Create reset password link */
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
			String resetPasswordUrl = appUrl.append("/resetPassword?token=").append(token).toString();
			String message = messageSource.getMessage("forgot.password.email.body", null, Locale.getDefault());

			/** Send Password reset email to user */
			String recipientAddress = user.getEmail();
			String subject = "Reset Password";
			EmailParams emailParams = new EmailParams();
			emailParams.setTo(recipientAddress);
			emailParams.setSubject(subject);
			emailParams.setEmailBody(message + " :: " + resetPasswordUrl);
			emailParams.setTemplateName(EmailTemplate.FORGOT_PASSWORD);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("body", message);
			parameters.put("resetPasswordUrl", resetPasswordUrl);
			parameters.put("name", user.getFirstName());
			parameters.put("email", user.getEmail());

			emailParams.setParameters(parameters);

			message = messageSource.getMessage("forgot.password.success", null, Locale.getDefault());
			model.addAttribute(MESSAGE, message);

			mailService.sendEmail(emailParams);
		} catch (Exception ex) {
			LOG.error("Exception occured while submitting forgot password Page", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return FORGOT_PASSWORD_SUCCESS_JSP;

	}

	/**
	 * This method will be called when user hits the reset password link from
	 * the email, token in the email will be validated and based on that user
	 * will be redirected to reset password page.
	 * 
	 * @param request
	 *            HTTP request
	 * @param model
	 *            The model to carry data
	 * @param token
	 *            The validation token to be verified
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, Model model, @RequestParam("token") String token) {
		LOG.info("Validating the token");
		try {
			/** Get the reset password token object from Database */
			PasswordResetToken passwordResetToken = tokenService.getPasswordResetToken(token);
			if (passwordResetToken == null) {
				/**
				 * If the verification token not found in the Database, token is
				 * considered invalid
				 */
				String message = messageSource.getMessage("reset.password.invalidToken", null, Locale.getDefault());
				model.addAttribute(MESSAGE, message);
				model.addAttribute(TOKEN_STATE, "invalid");
				return RESET_PASSWORD_ERROR_JSP;
			}

			User user = passwordResetToken.getUser();
			Calendar cal = Calendar.getInstance();
			/** Check the expire duration for the token */
			if ((passwordResetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				/**
				 * If the verification token is expired, set the message and
				 * redirect user
				 */
				String messageValue = messageSource.getMessage("reset.password.expired", null, Locale.getDefault());
				model.addAttribute(MESSAGE, messageValue);
				model.addAttribute(TOKEN_STATE, "expired");
				model.addAttribute("email", user.getEmail());

				return RESET_PASSWORD_ERROR_JSP;
			}

			ResetPasswordForm form = new ResetPasswordForm();
			form.setResetPasswordToken(token);
			model.addAttribute("resetPasswordForm", form);
		} catch (Exception ex) {
			LOG.error("Exception occured while validating the token", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return RESET_PASSWORD_JSP;
	}

	/**
	 * This method will save the newly entered password by User in database
	 * 
	 * @param resetPassword
	 *            The reset password form
	 * @param result
	 *            The binding result, that will contain the error details
	 * @param model
	 *            The
	 * @param request
	 *            The HTTP request
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String savePassword(@Valid ResetPasswordForm resetPassword, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		LOG.info("Resetting the password in the database ");

		try {
			/**
			 * In case of any error, render the reset password page with error
			 */
			if (result.hasErrors()) {
				return RESET_PASSWORD_JSP;
			}

			PasswordResetToken passwordResetToken = tokenService
					.getPasswordResetToken(resetPassword.getResetPasswordToken());

			if (passwordResetToken == null) {
				/**
				 * If the verification token not found in the Database, token is
				 * considered invalid
				 */
				String message = messageSource.getMessage("reset.password.invalidToken", null, Locale.getDefault());
				model.addAttribute(MESSAGE, message);
				model.addAttribute(TOKEN_STATE, "invalid");
				return RESET_PASSWORD_ERROR_JSP;
			}

			/** Get the user and update the User in DB with updated password */
			User user = passwordResetToken.getUser();
			user.setPassword(resetPassword.getNewPassword());
			userService.updateUser(user);
		} catch (Exception ex) {
			LOG.error("Exception occured while loading resetting the password", ex);
			if (ex instanceof BaseException) {
				BaseException baseException = (BaseException) ex;
				throw new GenericException(baseException.getErrCode(), baseException.getErrMsg());
			}
		}
		return RESET_PASSWORD_SUCCESS_JSP;
	}
}