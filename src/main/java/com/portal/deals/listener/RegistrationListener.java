package com.portal.deals.listener;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.portal.deals.event.OnRegistrationCompleteEvent;
import com.portal.deals.model.EmailParams;
import com.portal.deals.model.User;
import com.portal.deals.model.VerificationToken;
import com.portal.deals.service.MailService;
import com.portal.deals.service.TokenService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private TokenService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private MailService mailService;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	public Date getExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, EXPIRATION);
		return new Date(cal.getTime().getTime());
	}

	private static final int EXPIRATION = 60 * 24;

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = null;
		if (user.getToken() == null) {
			verificationToken = new VerificationToken();
		} else {
			verificationToken = user.getToken();
		}
		verificationToken.setExpiryDate(getExpiryDate());
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		service.createVerificationToken(verificationToken);

		String recipientAddress = user.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl = event.getAppUrl() + "/regitrationConfirm?token=" + token;
		String message = messages.getMessage("message.registration.email.body", null, Locale.getDefault());
		EmailParams emailParams = new EmailParams();
		emailParams.setTo(recipientAddress);
		emailParams.setSubject(subject);
		emailParams.setEmailBody(message + " rn" + confirmationUrl);
		mailService.sendEmail(emailParams);
	}
}