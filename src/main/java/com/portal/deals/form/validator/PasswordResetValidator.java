package com.portal.deals.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portal.deals.form.ResetPasswordForm;

@Component
public class PasswordResetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPasswordForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "NotEmpty.new.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchPassword", "NotEmpty.match.password");
	}
}
