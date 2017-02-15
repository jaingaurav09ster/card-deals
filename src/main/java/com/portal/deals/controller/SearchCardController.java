package com.portal.deals.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.deals.model.Card;
import com.portal.deals.service.CardServiceManager;

/**
 * This is the controller class for doing CARD search
 * 
 * @author Gaurav Jain
 *
 */
@Controller
public class SearchCardController {

	/** Initializing the Logger */
	private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordController.class);

	/**
	 * Service class for communicating with DAO layer for Card specific DB
	 * operations
	 */
	@Autowired
	private CardServiceManager cardServiceManager;

	/**
	 * This method will search for cards in the database based on Query string
	 * 
	 * @param query
	 *            The Query string
	 * @return List of cards
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchCards/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Card> search(@PathVariable String query) throws IOException {
		LOG.info("Searching for Cards in the database");

		List<Card> cards = cardServiceManager.searchCard(query);
		return cards;
	}
}
