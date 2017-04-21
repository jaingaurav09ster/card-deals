package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REWARD")
public class Reward extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "REWARD_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
	private String description;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "REWARD_CATEGORY_MAP", joinColumns = { @JoinColumn(name = "REWARD_ID") }, inverseJoinColumns = {
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

	@Override
	public boolean equals(Object obj) {
		Reward reward = (Reward) obj;
		return this.id == reward.id;
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