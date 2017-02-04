package com.portal.deals.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portal.deals.form.validator.CardValidator;
import com.portal.deals.model.Card;
import com.portal.deals.service.CardServiceManager;

@Controller
@RequestMapping(value = "/admin/card")
public class CardController {

	@Autowired
	private CardServiceManager cardServiceManager;

	@Autowired
	CardValidator cardValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(cardValidator);
	}

	@RequestMapping(value = "/showAddForm")
	public ModelAndView createCard(HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("cardForm");
		model.addObject("card", new Card());
		return model;
	}

	@RequestMapping(value = "/listCards")
	public ModelAndView listAllCards(HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("cardList");
		List<Card> cards = cardServiceManager.listAllCards();
		model.addObject("cards", cards);
		return model;
	}

	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	public String addCard(@ModelAttribute("card") @Validated Card card, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:admin/card/showAddForm";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Card added successfully!");
			cardServiceManager.saveCard(card);
			return "redirect:/admin/card/listCards";
		}
	}

	@RequestMapping(value = "/showUpdateForm/{id}")
	public ModelAndView updateCardForm(@PathVariable("id") int id) throws IOException {
		ModelAndView model = new ModelAndView("cardForm");
		Card card = cardServiceManager.getCardById(id);
		model.addObject("card", card);
		return model;
	}

	@RequestMapping(value = "/deleteCard/{id}")
	public String deleteCard(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		cardServiceManager.deleteCardById(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");

		return "redirect:/admin/card/listCards";

	}

	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	public String updateCard(@ModelAttribute("card") @Validated Card card, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/admin/card/showUpdateForm/"+card.getCardId();
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Card added successfully!");
			cardServiceManager.updateCard(card);
			return "redirect:/admin/card/listCards";
		}
	}

}
