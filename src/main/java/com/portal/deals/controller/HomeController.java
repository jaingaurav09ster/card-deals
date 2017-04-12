package com.portal.deals.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the controller class for Home page
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class HomeController {

	/**
	 * This method will render the Home page
	 * 
	 * @return The view JSP
	 */
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	/**
	 * This method will render the logged in ADMIN Home page
	 * 
	 * @return The view JSP
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = { "/console" })
	public ModelAndView console() {
		return new ModelAndView("console");
	}

	/**
	 * This method will render the logged in Home page
	 * 
	 * @return The view JSP
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = { "/welcome" })
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}

	/**
	 * This method will render the Bank logged in home page
	 * 
	 * @return The view JSP
	 */
	@Secured("ROLE_BANK")
	@RequestMapping(value = { "/bank" })
	public ModelAndView bank() {
		return new ModelAndView("bank");
	}
}
