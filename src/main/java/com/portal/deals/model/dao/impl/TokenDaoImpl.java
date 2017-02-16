package com.portal.deals.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.VerificationToken;
import com.portal.deals.model.dao.TokenDao;

@Repository("tokenDao")
public class TokenDaoImpl extends AbstractDao<Integer, VerificationToken> implements TokenDao {

	static final Logger logger = LoggerFactory.getLogger(TokenDaoImpl.class);

	public VerificationToken getToken(String token) {
		logger.info("token : {}", token);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		VerificationToken verificationtoken = (VerificationToken) crit.uniqueResult();
		return verificationtoken;
	}

	public void save(VerificationToken token) {
		saveOrUpdate(token);
	}

}
