package com.portal.deals.service;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.VerificationToken;

public interface TokenService {

	public VerificationToken getVerificationToken(String VerificationToken);

	public void createVerificationToken(VerificationToken token);

	public void createPasswordResetToken(PasswordResetToken token);
	
	public PasswordResetToken getPasswordResetToken(String passwordResetToken);

}