package com.portal.deals.event;

import org.springframework.context.ApplicationEvent;

import com.portal.deals.model.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private String appUrl;
	private User user;

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