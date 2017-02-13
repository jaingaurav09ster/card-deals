package com.portal.deals.model;

public class EmailParams {

	private String from;
	private String to;
	private String subject;
	private String emailBody;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the emailBody
	 */
	public String getEmailBody() {
		return emailBody;
	}

	/**
	 * @param emailBody
	 *            the emailBody to set
	 */
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
}