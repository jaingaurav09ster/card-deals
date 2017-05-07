package com.portal.deals.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.deals.helper.FilterHelper;
import com.portal.deals.model.NavElement;

/**
 * This is the controller class for doing CARD search
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class GetNavigationController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(GetNavigationController.class);

	/**
	 * Helper class for filter related methods
	 */
	@Autowired
	private FilterHelper filterHelper;

	/**
	 * This method will search for cards in the database based on Query string
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/getNavElements", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<NavElement>> search() throws IOException {
		LOG.info("Searching for Cards in the database");
		Map<String, List<NavElement>> navigation = filterHelper.getNavigation();
		return navigation;
	}
}
