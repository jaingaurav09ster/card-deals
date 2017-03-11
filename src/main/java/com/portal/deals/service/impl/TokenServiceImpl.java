package com.portal.deals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.VerificationToken;
import com.portal.deals.model.dao.PasswordResetTokenDao;
import com.portal.deals.model.dao.VerificationTokenDao;
import com.portal.deals.service.TokenService;

@Service("tokenService")
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	private VerificationTokenDao dao;

	@Autowired
	private PasswordResetTokenDao passwordDao;

	@Override
	public VerificationToken getVerificationToken(String token) {
		VerificationToken verificationToken = dao.getToken(token);
		return verificationToken;
	}

	@Override
	public void createVerificationToken(VerificationToken token) {
		dao.save(token);
	}

	@Override
	public void createPasswordResetToken(PasswordResetToken token) {
		passwordDao.save(token);
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordDao.getToken(token);
		return passwordResetToken;
	}

}
