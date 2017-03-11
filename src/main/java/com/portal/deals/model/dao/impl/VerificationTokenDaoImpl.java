package com.portal.deals.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.VerificationToken;
import com.portal.deals.model.dao.VerificationTokenDao;

/**
 * This class provides implementation to <code>VerificationTokenDao</code>
 * interface. It will access the Database and it will perform CRUD operation on
 * verification token entity
 * 
 * @author Gaurav Jain
 *
 */
@Repository("tokenDao")
public class VerificationTokenDaoImpl extends AbstractDao<Integer, VerificationToken> implements VerificationTokenDao {

	@Override
	public VerificationToken getToken(String token) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		VerificationToken verificationtoken = (VerificationToken) crit.uniqueResult();
		return verificationtoken;
	}

	@Override
	public void save(VerificationToken token) {
		saveOrUpdate(token);
	}

}
