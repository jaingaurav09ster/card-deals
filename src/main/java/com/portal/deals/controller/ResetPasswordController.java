package com.portal.deals.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.deals.form.ResetPasswordForm;
import com.portal.deals.model.EmailParams;
import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.User;
import com.portal.deals.service.MailService;
import com.portal.deals.service.TokenService;
import com.portal.deals.service.UserService;

/**
 * This is the controller class for Reset Password flow
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class ResetPasswordController {

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
	public String forgotPassword(@PathVariable("email") String email, HttpServletRequest request, Model model) {
		LOG.info("Going to submit forgot password page ");

		/** Get the user from DB, based on email */
		User user = userService.findByEmail(email);
		if (user == null) {
			/** User not found, so adding error message and redirecting */
			String message = messageSource.getMessage("forgot.password.error", null, Locale.getDefault());
			model.addAttribute(MESSAGE, message);
			return FORGOT_PASSWORD_SUCCESS_JSP;
		}
		/** Generate token and persist the token in database */
		String token = UUID.randomUUID().toString();
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setExpiryDate(getExpiryDate());
		passwordResetToken.setToken(token);
		passwordResetToken.setUser(user);
		tokenService.createPasswordResetToken(passwordResetToken);

		/** Create reset password link */
		StringBuffer appUrl = new StringBuffer();
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
		mailService.sendEmail(emailParams);

		message = messageSource.getMessage("forgot.password.success", null, Locale.getDefault());
		model.addAttribute(MESSAGE, message);
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

		/**
		 * Giving User the access to Reset Password
		 */
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return RESET_PASSWORD_JSP;
	}

	/**
	 * This method will get the expire date for token
	 * 
	 * @return Expire Date
	 */
	public Date getExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, EXPIRATION);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * This method will save the newly entered password by User in database
	 * 
	 * @param resetPassword
	 * @return
	 */
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public String savePassword(@Validated ResetPasswordForm resetPassword) {
		/** Get the user from Session */
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setPassword(resetPassword.getNewPassword());
		userService.saveUser(user);
		return RESET_PASSWORD_SUCCESS_JSP;
	}
}