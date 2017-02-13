package com.portal.deals.service.impl;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.portal.deals.model.EmailParams;
import com.portal.deals.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(EmailParams params) {
		params.setFrom("admin@cardDeals.com");
		MimeMessagePreparator preparator = getMessagePreparator(params);
		mailSender.send(preparator);
	}

	private MimeMessagePreparator getMessagePreparator(EmailParams params) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(params.getFrom());
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(params.getTo()));
				mimeMessage.setText(params.getEmailBody());
				mimeMessage.setSubject(params.getSubject());
			}
		};
		return preparator;
	}

}
