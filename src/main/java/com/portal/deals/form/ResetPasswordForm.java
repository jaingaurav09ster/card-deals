package com.portal.deals.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetPasswordForm {

	@NotEmpty
	@Length(min = 4)
	private String newPassword;
	
	@Length(min = 4)
	@NotEmpty
	private String matchPassword;

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the matchPassword
	 */
	public String getMatchPassword() {
		return matchPassword;
	}

	/**
	 * @param matchPassword
	 *            the matchPassword to set
	 */
	public void setMatchPassword(String matchPassword) {
		this.matchPassword = matchPassword;
	}
}