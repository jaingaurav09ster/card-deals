package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "BANK")
public class Bank implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BANK_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "SECTOR", nullable = true)
	private String sector;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
	private Set<Card> cardSet;
	
	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
	private Set<User> userSet;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the cardSet
	 */
	public Set<Card> getCardSet() {
		return cardSet;
	}

	/**
	 * @param cardSet
	 *            the cardSet to set
	 */
	public void setCardSet(Set<Card> cardSet) {
		this.cardSet = cardSet;
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
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

}