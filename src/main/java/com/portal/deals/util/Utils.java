package com.portal.deals.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This is the Utility class that will contain static Utility methods
 * 
 * @author Gaurav Jain
 *
 */
public class Utils {

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 * 
	 * @return The User name
	 */
	public static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns the logged-in user.
	 * 
	 * @return The User name
	 */
	public static com.portal.deals.model.UserDetails getLoggedInUser() {
		com.portal.deals.model.UserDetails user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			user = ((com.portal.deals.model.UserDetails) principal);
		}
		return user;
	}

	/**
	 * This method will calculate the expire date, by adding expire duration to
	 * current date
	 * 
	 * @param expiryDuration
	 *            The Expire duration
	 * @return The Expired Date
	 */
	public static Date getExpiryDate(int expiryDuration) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryDuration);
		return new Date(cal.getTime().getTime());
	}
}