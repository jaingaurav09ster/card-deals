package com.portal.deals.model.dao;

import com.portal.deals.model.PasswordResetToken;

public interface PasswordResetTokenDao {

	PasswordResetToken getToken(String token);

	void save(PasswordResetToken token);

}
