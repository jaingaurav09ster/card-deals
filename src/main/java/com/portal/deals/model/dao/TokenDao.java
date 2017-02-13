package com.portal.deals.model.dao;

import com.portal.deals.model.VerificationToken;

public interface TokenDao {

	VerificationToken getToken(String token);

	void save(VerificationToken token);

}
