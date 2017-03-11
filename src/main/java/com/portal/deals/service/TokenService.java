package com.portal.deals.service;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.VerificationToken;

public interface TokenService {

	VerificationToken getVerificationToken(String VerificationToken);

	void createVerificationToken(VerificationToken token);

	void createPasswordResetToken(PasswordResetToken token);
	
	PasswordResetToken getPasswordResetToken(String passwordResetToken);

}