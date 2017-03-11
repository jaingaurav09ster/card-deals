package com.portal.deals.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.PasswordResetToken;
import com.portal.deals.model.dao.PasswordResetTokenDao;

/**
 * This class provides implementation to <code>PasswordResetTokenDao</code>
 * interface. It will access the Database and it will perform CRUD operations on
 * password reset token entity
 * 
 * @author Gaurav Jain
 *
 */
@Repository("passwordResetTokenDao")
public class PasswordResetTokenDaoImpl extends AbstractDao<Integer, PasswordResetToken>
		implements PasswordResetTokenDao {

	@Override
	public PasswordResetToken getToken(String token) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
		return passwordResetToken;
	}

	@Override
	public void save(PasswordResetToken token) {
		saveOrUpdate(token);
	}

}
