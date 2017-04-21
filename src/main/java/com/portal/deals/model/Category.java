package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = true)
	private String description;

	public Category(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Category> categories;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CHILD_CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = true)
	private Category category;

	@NotNull
	@Transient
	private int parentId;
	
	/**
	 * Default constructor
	 */
	public Category() {
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
		Category category = (Category) obj;
		return this.id == category.id;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}