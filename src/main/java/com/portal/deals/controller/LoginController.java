package com.portal.deals.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portal.deals.util.Utils;

/**
 * This Controller will handle the Login flow
 * 
 * @author Gaurav Jain
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	/** The Service for persisting the session, when user checks remember me */
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	/** Evaluates <code>Authentication</code> tokens */
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	/**
	 * This method handles Access-Denied redirect.
	 * 
	 * @param model
	 *            The model to carry DATA
	 * @return The view JSP
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		LOG.info("User does not have permission to access the page");
		model.addAttribute("loggedinuser", Utils.getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to home page.
	 * 
	 * @return The view JSP
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		LOG.info("Rendering Login Page");
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/home";
		}
	}

	/**
	 * This method handles logout requests.
	 * 
	 * @param request
	 *            The HTTP request
	 * @param response
	 *            The HTTP response
	 * @return The redirect to login page with message
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		LOG.info("Logging out the User");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

}