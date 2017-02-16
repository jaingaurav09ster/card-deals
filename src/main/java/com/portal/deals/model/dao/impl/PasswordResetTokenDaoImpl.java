package com.portal.deals.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.dao.PasswordResetTokenDao;

@Repository("passwordResetTokenDao")
public class PasswordResetTokenDaoImpl extends AbstractDao<Integer, PasswordResetToken>
		implements PasswordResetTokenDao {

	static final Logger logger = LoggerFactory.getLogger(PasswordResetToken.class);

	public PasswordResetToken getToken(String token) {
		logger.info("token : {}", token);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		return passwordResetToken;
	}

	public void save(PasswordResetToken token) {
		saveOrUpdate(token);
	}

}
