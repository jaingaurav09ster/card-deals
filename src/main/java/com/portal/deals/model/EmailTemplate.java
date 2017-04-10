package com.portal.deals.model;

public enum EmailTemplate {
	REGISTRATION("registration_mailTemplate.txt"), FORGOT_PASSWORD("forgot_password_mailTemplate.txt");

	String template;

	private EmailTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

}
