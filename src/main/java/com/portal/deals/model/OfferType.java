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
@Table(name = "OFFER_TYPE")
public class OfferType extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "OFFER_TYPE_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "offerType", fetch = FetchType.LAZY)
	private Set<Deal> dealSet;

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
	 * @return the dealSet
	 */
	public Set<Deal> getDealSet() {
		return dealSet;
	}

	/**
	 * @param dealSet the dealSet to set
	 */
	public void setDealSet(Set<Deal> dealSet) {
		this.dealSet = dealSet;
	}

}