package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARD")
public class Card extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer Id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "CARD_DESC", nullable = false)
	private String description;

	@Column(name = "LAUNCH_DATE", nullable = true)
	private Date launchDate;

	@Column(name = "IMAGE", nullable = true)
	private String image;

	@Column(name = "RANK", nullable = true)
	private String rank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_CATEGORY_ID")
	private CardCategory cardCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_TYPE")
	private CardType cardType;

	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Bank bank;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<JoiningPerk> joiningPerks;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Feature> features;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Reward> rewards;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Deal> deals;

	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Fees fees;

	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Rating rating;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Document> documents;

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
	 * @return the launchDate
	 */
	public Date getLaunchDate() {
		return launchDate;
	}

	/**
	 * @param launchDate
	 *            the launchDate to set
	 */
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the cardCategory
	 */
	public CardCategory getCardCategory() {
		return cardCategory;
	}

	/**
	 * @param cardCategory
	 *            the cardCategory to set
	 */
	public void setCardCategory(CardCategory cardCategory) {
		this.cardCategory = cardCategory;
	}

	/**
	 * @return the cardType
	 */
	public CardType getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the fees
	 */
	public Fees getFees() {
		return fees;
	}

	/**
	 * @param fees
	 *            the fees to set
	 */
	public void setFees(Fees fees) {
		this.fees = fees;
	}

	/**
	 * @return the rating
	 */
	public Rating getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}

	/**
	 * @return the joiningPerks
	 */
	public Set<JoiningPerk> getJoiningPerks() {
		return joiningPerks;
	}

	/**
	 * @param joiningPerks
	 *            the joiningPerks to set
	 */
	public void setJoiningPerks(Set<JoiningPerk> joiningPerks) {
		this.joiningPerks = joiningPerks;
	}

	/**
	 * @return the features
	 */
	public Set<Feature> getFeatures() {
		return features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	/**
	 * @return the rewards
	 */
	public Set<Reward> getRewards() {
		return rewards;
	}

	/**
	 * @param rewards
	 *            the rewards to set
	 */
	public void setRewards(Set<Reward> rewards) {
		this.rewards = rewards;
	}

	/**
	 * @return the deals
	 */
	public Set<Deal> getDeals() {
		return deals;
	}

	/**
	 * @param deals
	 *            the deals to set
	 */
	public void setDeals(Set<Deal> deals) {
		this.deals = deals;
	}

	/**
	 * @return the documents
	 */
	public Set<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}