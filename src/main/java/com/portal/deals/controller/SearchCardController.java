package com.portal.deals.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.deals.model.Card;
import com.portal.deals.service.CardServiceManager;

@Controller
public class SearchCardController {

	@Autowired
	private CardServiceManager cardServiceManager;

	@RequestMapping(value = "/searchCards/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Card> search(@PathVariable String query) throws IOException {
		List<Card> cards = cardServiceManager.searchCard(query);
		return cards;
	}
}
