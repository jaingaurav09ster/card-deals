package com.portal.deals.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is reset Password form used to get the new password input from the User.
 * 
 * @author Gaurav Jain
 *
 */
public class ResetPasswordForm {

	/**
	 * New password entered by user, should not be empty and length should be at
	 * least four characters
	 */
	@NotEmpty
	@Length(min = 4)
	private String newPassword;

	/** Confirm password field */
	@Length(min = 4)
	@NotEmpty
	private String matchPassword;
	
	private String resetPasswordToken;

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

	/**
	 * @return the resetPasswordToken
	 */
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	/**
	 * @param resetPasswordToken the resetPasswordToken to set
	 */
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

}