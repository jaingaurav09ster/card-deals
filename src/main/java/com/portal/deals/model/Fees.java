package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FEES")
public class Fees implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FEES_ID")
	private Integer id;

	@Column(name = "APR", nullable = true)
	private BigDecimal apr;

	@NotNull
	@Column(name = "FIRST_YEAR", nullable = false)
	private BigDecimal firstYear;

	@NotNull
	@Column(name = "ONWARDS", nullable = false)
	private BigDecimal onwards;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID", nullable = false)
	private Card card;
	
	@NotNull
	@Transient
	private int cardId;

	/**
	 * @return the apr
	 */
	public BigDecimal getApr() {
		return apr;
	}

	/**
	 * @param apr
	 *            the apr to set
	 */
	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	/**
	 * @return the firstYear
	 */
	public BigDecimal getFirstYear() {
		return firstYear;
	}

	/**
	 * @param firstYear
	 *            the firstYear to set
	 */
	public void setFirstYear(BigDecimal firstYear) {
		this.firstYear = firstYear;
	}

	/**
	 * @return the onwards
	 */
	public BigDecimal getOnwards() {
		return onwards;
	}

	/**
	 * @param onwards
	 *            the onwards to set
	 */
	public void setOnwards(BigDecimal onwards) {
		this.onwards = onwards;
	}

	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @param card
	 *            the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		Fees fees = (Fees) obj;
		return this.id == fees.id;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

}