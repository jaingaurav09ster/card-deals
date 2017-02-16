package com.portal.deals.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is forgot password form to capture email from the front end
 * 
 * @author Gaurav Jain
 *
 */
public class ForgotPasswordForm {

	/** The email to be entered by User */
	@NotEmpty
	@Email
	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}