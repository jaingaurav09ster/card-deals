package com.portal.deals.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.portal.deals.model.EmailParams;
import com.portal.deals.service.MailService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration freemarkerConfiguration;

	@Override
	public void sendEmail(EmailParams params) {
		params.setFrom("admin@cardDeals.com");
		MimeMessagePreparator preparator = getMessagePreparator(params);
		mailSender.send(preparator);
	}

	private MimeMessagePreparator getMessagePreparator(EmailParams params) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject(params.getSubject());
				helper.setFrom(params.getFrom());
				helper.setTo(params.getTo());

				String text = geFreeMarkerTemplateContent(params.getParameters(),
						params.getTemplateName().getTemplate());

				/**
				 * use the true flag to indicate you need a MULTI part message
				 */
				helper.setText(text, true);
			}
		};
		return preparator;
	}

	private String geFreeMarkerTemplateContent(Map<String, Object> model, String tempateName)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		StringBuffer content = new StringBuffer();
		content.append(FreeMarkerTemplateUtils
				.processTemplateIntoString(freemarkerConfiguration.getTemplate(tempateName), model));
		return content.toString();
	}
}
