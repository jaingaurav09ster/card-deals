package com.portal.deals.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.model.User;
import com.portal.deals.service.UserProfileService;
import com.portal.deals.service.UserService;

@Controller
@RequestMapping("/user")
public class EditProfileController {

	/** Message shown to user */
	private static final String MESSAGE = "message";

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.GET)
	public String editUser(ModelMap model, HttpServletRequest request) {
		User user = userService.findByEmail(getPrincipal());
		model.addAttribute("user", user);
		model.addAttribute("loggedinuser", getPrincipal());
		return "editProfile";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/editProfile" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) {
		model.addAttribute("edit", true);
		if (result.hasErrors()) {
			return "editProfile";
		}
		User userFromSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userFromSession.setFirstName(user.getFirstName());
		userFromSession.setLastName(user.getLastName());
		userFromSession.setPassword(user.getPassword());
		userFromSession.setMobile(user.getMobile());
		userService.updateUser(userFromSession);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		String messageValue = messageSource.getMessage("auth.message.account.updated", null, Locale.getDefault());
		model.addAttribute(MESSAGE, messageValue);
		return "registrationSuccess";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}