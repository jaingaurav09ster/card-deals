package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARD_DETAILS")
public class Card implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CARD_ID")
	private Integer cardId;

	@Column(name = "CARD_TITLE", nullable = false)
	private String cardTitle;

	@Column(name = "CARD_DESC", nullable = false)
	private String cardDesc;

	@Column(name = "BANK_NAME", nullable = false)
	private String bankName;

	public Card() {
	}

	public Card(String cardTitle, String cardDesc, String bankName) {
		this.cardTitle = cardTitle;
		this.cardDesc = cardDesc;
		this.bankName = bankName;
	}

	/**
	 * @return the cardId
	 */
	public Integer getCardId() {
		return cardId;
	}

	/**
	 * @param cardId
	 *            the cardId to set
	 */
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the cardTitle
	 */
	public String getCardTitle() {
		return cardTitle;
	}

	/**
	 * @param cardTitle
	 *            the cardTitle to set
	 */
	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}

	/**
	 * @return the cardDesc
	 */
	public String getCardDesc() {
		return cardDesc;
	}

	/**
	 * @param cardDesc
	 *            the cardDesc to set
	 */
	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}