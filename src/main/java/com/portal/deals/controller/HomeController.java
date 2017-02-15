package com.portal.deals.controller;

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
}
