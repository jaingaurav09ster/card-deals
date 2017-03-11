package com.portal.deals.model.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.PersistentLogin;

/**
 * This class provides implementation to <code>PersistentTokenRepository</code>
 * interface. It will access the Database and it will persist the session
 * password reset token entity
 * 
 * @author Gaurav Jain
 *
 */
@Repository("tokenRepositoryDao")
@Transactional
public class TokenRepositoryImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

	/** Initializing the logger */
	static final Logger LOG = LoggerFactory.getLogger(TokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		LOG.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		LOG.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			LOG.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		LOG.info("Removing Token if any for user : {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
		if (persistentLogin != null) {
			LOG.info("rememberMe was selected");
			delete(persistentLogin);
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		LOG.info("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}
}
