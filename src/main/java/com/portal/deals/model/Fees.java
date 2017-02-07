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

@Entity
@Table(name = "FEES")
public class Fees implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer Id;

	@Column(name = "APR", nullable = false)
	private BigDecimal apr;

	@Column(name = "FIRST_YEAR", nullable = false)
	private BigDecimal firstYear;

	@Column(name = "ONWARDS", nullable = false)
	private BigDecimal onwards;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID", nullable = false)
    private Card card;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		Id = id;
	}

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
	 * @param card the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
	}

}