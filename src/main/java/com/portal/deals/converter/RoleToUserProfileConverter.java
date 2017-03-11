package com.portal.deals.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.portal.deals.model.UserRole;
import com.portal.deals.service.UserRoleService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 *
 * @author Gaurav Jain
 *
 */

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserRole> {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

	/**
	 * Service class for communicating with DAO layer for getting USER Profiles
	 */
	@Autowired
	UserRoleService userRoleService;

	/**
	 * This method will get the UserRole by Id
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public UserRole convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserRole profile = userRoleService.findById(id);
		LOG.info("Profile : {}", profile);
		return profile;
	}
}