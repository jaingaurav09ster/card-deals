package com.portal.deals.service;

import com.portal.deals.model.EmailParams;

public interface MailService {

	void sendEmail(EmailParams params);
}
