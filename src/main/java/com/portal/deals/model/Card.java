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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CARD")
public class Card extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CARD_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "CARD_DESC", columnDefinition = "TEXT", nullable = true)
	private String description;

	@Column(name = "LAUNCH_DATE", nullable = true)
	private Date launchDate;

	@NotNull
	@Transient
	private MultipartFile image;

	@NotEmpty
	@Column(name = "IMAGE", nullable = true)
	private String imagePath;

	@Column(name = "RANK", nullable = true)
	private String rank;

	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARD_CATEGORY_ID")
	private CardCategory cardCategory;

	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARD_TYPE_ID")
	private CardType cardType;

	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BANK_ID")
	private Bank bank;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CARD_CATEGORY_MAP", joinColumns = { @JoinColumn(name = "CARD_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CATEGORY_ID") })
	private Set<Category> categories;

	@JsonIgnore
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<JoiningPerk> joiningPerks;

	@JsonManagedReference
	@OneToMany(mappedBy = "card", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Feature> features;

	@JsonIgnore
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Reward> rewards;

	@JsonIgnore
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Deal> deals;

	@JsonIgnore
	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Fees fees;

	@JsonIgnore
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Rating> rating;

	@JsonIgnore
	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Document> documents;

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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the image
	 */
	public MultipartFile getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(MultipartFile image) {
		this.image = image;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	/**
	 * @return the rating
	 */
	public Set<Rating> getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}
}