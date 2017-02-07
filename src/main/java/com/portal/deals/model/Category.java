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

@Entity
@Table(name = "CATEGORY")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer Id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@OneToMany(mappedBy = "rewardCategory", fetch = FetchType.LAZY)
	private Set<Reward> rewardSet;

	@OneToMany(mappedBy = "perkCategory", fetch = FetchType.LAZY)
	private Set<JoiningPerk> perkSet;

	@OneToMany(mappedBy = "featureCategory", fetch = FetchType.LAZY)
	private Set<Feature> featureSet;

	@OneToMany(mappedBy = "dealCategory", fetch = FetchType.LAZY)
	private Set<Deal> dealSet;

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
	 * @return the rewardSet
	 */
	public Set<Reward> getRewardSet() {
		return rewardSet;
	}

	/**
	 * @param rewardSet the rewardSet to set
	 */
	public void setRewardSet(Set<Reward> rewardSet) {
		this.rewardSet = rewardSet;
	}

	/**
	 * @return the perkSet
	 */
	public Set<JoiningPerk> getPerkSet() {
		return perkSet;
	}

	/**
	 * @param perkSet the perkSet to set
	 */
	public void setPerkSet(Set<JoiningPerk> perkSet) {
		this.perkSet = perkSet;
	}

	/**
	 * @return the featureSet
	 */
	public Set<Feature> getFeatureSet() {
		return featureSet;
	}

	/**
	 * @param featureSet the featureSet to set
	 */
	public void setFeatureSet(Set<Feature> featureSet) {
		this.featureSet = featureSet;
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