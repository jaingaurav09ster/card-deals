package com.portal.deals.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portal.deals.model.Card;

@Component
public class CardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Card.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardTitle", "NotEmpty.card.cardTitle");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankName", "NotEmpty.card.bankName");
		ValidationUtils.rejectIfEmpty(errors, "cardDesc", "NotEmpty.card.cardDesc");
	}
}
