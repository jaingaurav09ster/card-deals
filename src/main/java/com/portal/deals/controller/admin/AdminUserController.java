package com.portal.deals.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.portal.deals.model.User;
import com.portal.deals.model.UserProfile;
import com.portal.deals.service.UserProfileService;
import com.portal.deals.service.UserService;

@Controller
@RequestMapping("/admin/user")
@SessionAttributes("roles")
public class AdminUserController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/listUsers", "" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "usersList";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userForm";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "userForm";
		}

		if (!userService.isUserUnique(user.getId(), user.getEmail())) {
			FieldError error = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { user.getEmail() }, Locale.getDefault()));
			result.addError(error);
			return "userForm";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/user/listUsers";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/updateUser/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String id, ModelMap model) {
		User user = userService.findByEmail(id);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userForm";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/updateUser/{id}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String id) {
		model.addAttribute("edit", true);
		if (result.hasErrors()) {
			return "userForm";
		}

		userService.updateUserByAdmin(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/user/listUsers";
	}

	/**
	 * This method will delete an user by it's Email ID value.
	 */
	@RequestMapping(value = { "/deleteUser/{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id) {
		userService.deleteUserByEmail(id);
		return "redirect:/admin/user/listUsers";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
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