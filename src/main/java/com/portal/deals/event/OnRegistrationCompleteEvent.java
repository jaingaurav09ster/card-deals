package com.portal.deals.event;

import org.springframework.context.ApplicationEvent;

import com.portal.deals.model.User;

/**
 * This is an event class fired on Registration completion.
 * 
 * @author Gaurav Jain
 *
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	/** Default serial version ID */
	private static final long serialVersionUID = 1L;

	/** The application URL for validating the user's profile */
	private String appUrl;

	/** The User entity object */
	private User user;

	/**
	 * The Constructor
	 * 
	 * @param user
	 *            The User object
	 * @param appUrl
	 *            The application URL for validating the user's profile
	 */
	public OnRegistrationCompleteEvent(User user, String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	/**
	 * @return the appUrl
	 */
	public String getAppUrl() {
		return appUrl;
	}

	/**
	 * @param appUrl
	 *            the appUrl to set
	 */
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}