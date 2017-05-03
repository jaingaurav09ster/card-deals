package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "DEAL")
public class Deal extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DEAL_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "VALUE", nullable = true)
	private String dealValue;

	@Column(name = "MAX_VALUE", nullable = true)
	private String maxValue;

	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OFFER_TYPE_ID")
	private OfferType offerType;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MERCHANT_ID")
	private Merchant merchant;

	@Column(name = "MAX_VALUE_UNIT")
	private String maxValueUnit;

	@Column(name = "COUPON_CODE")
	private String couponCode;

	@Column(name = "VALUE_UNIT")
	private String valueUnit;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
	private String description;

	@Column(name = "RANK", nullable = true)
	private Integer rank;

	@NotNull
	@Column(name = "START_DATE", nullable = true)
	private Date startDate;

	@NotNull
	@Column(name = "END_DATE", nullable = true)
	private Date endDate;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DEAL_CATEGORY_MAP", joinColumns = { @JoinColumn(name = "DEAL_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CATEGORY_ID") })
	private Set<Category> categories;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID", nullable = true)
	private Card card;

	@NotNull
	@Transient
	private int cardId;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		Deal deal = (Deal) obj;
		return this.id == deal.id;
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
	 * @param cardId
	 *            the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the dealValue
	 */
	public String getDealValue() {
		return dealValue;
	}

	/**
	 * @param dealValue
	 *            the dealValue to set
	 */
	public void setDealValue(String dealValue) {
		this.dealValue = dealValue;
	}

	/**
	 * @return the maxValue
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the offerType
	 */
	public OfferType getOfferType() {
		return offerType;
	}

	/**
	 * @param offerType
	 *            the offerType to set
	 */
	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	/**
	 * @return the maxValueUnit
	 */
	public String getMaxValueUnit() {
		return maxValueUnit;
	}

	/**
	 * @param maxValueUnit
	 *            the maxValueUnit to set
	 */
	public void setMaxValueUnit(String maxValueUnit) {
		this.maxValueUnit = maxValueUnit;
	}

	/**
	 * @return the valueUnit
	 */
	public String getValueUnit() {
		return valueUnit;
	}

	/**
	 * @param valueUnit
	 *            the valueUnit to set
	 */
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	/**
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * @param couponCode
	 *            the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant
	 *            the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}