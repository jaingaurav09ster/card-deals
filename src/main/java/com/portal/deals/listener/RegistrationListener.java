package com.portal.deals.listener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.portal.deals.controller.RegistrationController;
import com.portal.deals.event.OnRegistrationCompleteEvent;
import com.portal.deals.model.EmailParams;
import com.portal.deals.model.EmailTemplate;
import com.portal.deals.model.User;
import com.portal.deals.model.VerificationToken;
import com.portal.deals.service.MailService;
import com.portal.deals.service.TokenService;
import com.portal.deals.util.Utils;

/**
 * This is the listener class that will listen for
 * <code>OnRegistrationCompleteEvent</code> event, this event will be fired from
 * Registration flow.
 * 
 * @author Gaurav Jain
 *
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	/** The expire duration for the validation token */
	private static final int EXPIRATION = 60 * 24;

	/**
	 * Service class for communicating with DAO layer for generating and
	 * fetching validation TOKEN
	 */
	@Autowired
	private TokenService service;

	/** Getting resource bundle for reading messages from properties file */
	@Autowired
	private MessageSource messages;

	/**
	 * Service class for sending out email
	 */
	@Autowired
	private MailService mailService;

	/**
	 * This is the overridden method that will be called when the event is
	 * fired. This will create the validation link and send out that link in the
	 * email to the User
	 * 
	 * @param event
	 */
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		LOG.info("in listener class: Completing the registration process");
		this.confirmRegistration(event);
	}

	/**
	 * This method will do the actual task for creating the validation email and
	 * sending out the email
	 * 
	 * @param event
	 *            The Event object
	 */
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		/** Get the user from event object */
		User user = event.getUser();

		/** Create validation token to be used to validate the User */
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = null;

		/**
		 * Token object will be created only, if it is for the first time
		 * otherwise existing token will be updated
		 */
		if (user.getToken() == null) {
			verificationToken = new VerificationToken();
		} else {
			verificationToken = user.getToken();
		}

		/** setting the token entity and saving that to database */
		verificationToken.setExpiryDate(Utils.getExpiryDate(EXPIRATION));
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		service.createVerificationToken(verificationToken);

		/** Creating the Validation URL */
		String confirmationUrl = event.getAppUrl() + "/regitrationConfirm?token=" + token;

		/** Sending the email that contains the Validation token */
		String recipientAddress = user.getEmail();
		String subject = "Registration Confirmation";
		String message = messages.getMessage("message.registration.email.body", null, Locale.getDefault());
		EmailParams emailParams = new EmailParams();
		emailParams.setTo(recipientAddress);
		emailParams.setSubject(subject);
		emailParams.setEmailBody(message + " rn" + confirmationUrl);

		emailParams.setTemplateName(EmailTemplate.REGISTRATION);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", user.getFirstName());
		parameters.put("email", user.getEmail());
		parameters.put("confirmationUrl", confirmationUrl);
		emailParams.setParameters(parameters);

		mailService.sendEmail(emailParams);
	}
}