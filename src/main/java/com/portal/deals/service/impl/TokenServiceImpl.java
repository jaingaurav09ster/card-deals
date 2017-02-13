package com.portal.deals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.VerificationToken;
import com.portal.deals.model.dao.PasswordResetTokenDao;
import com.portal.deals.model.dao.TokenDao;
import com.portal.deals.service.TokenService;

@Service("tokenService")
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao dao;

	@Autowired
	private PasswordResetTokenDao passwordDao;

	public VerificationToken getVerificationToken(String token) {
		VerificationToken verificationToken = dao.getToken(token);
		return verificationToken;
	}

	public void createVerificationToken(VerificationToken token) {
		dao.save(token);
	}

	public void createPasswordResetToken(PasswordResetToken token) {
		passwordDao.save(token);
	}

	public PasswordResetToken getPasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordDao.getToken(token);
		return passwordResetToken;
	}

}
