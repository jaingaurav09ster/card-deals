package com.portal.deals.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CONTENT")
public class Content implements java.io.Serializable {

	private static final long serialVersionUID = -7420477606415096968L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CONTENT_ID")
	private Integer id;

	@NotEmpty
	@Column(name = "PAGE_CONTENT", columnDefinition = "TEXT", nullable = false)
	private String pageContent;

	@NotEmpty
	@Column(name = "PAGE_TITLE", nullable = false)
	private String pageTitle;

	@NotEmpty
	@Column(name = "URL_MAPPING", nullable = false, unique = true)
	private String urlMapping;

	@Column(name = "PAGE_META_TAGS", nullable = true)
	private String pageTags;

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
	 * @return the pageContent
	 */
	public String getPageContent() {
		return pageContent;
	}

	/**
	 * @param pageContent
	 *            the pageContent to set
	 */
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @return the pageTags
	 */
	public String getPageTags() {
		return pageTags;
	}

	/**
	 * @param pageTags
	 *            the pageTags to set
	 */
	public void setPageTags(String pageTags) {
		this.pageTags = pageTags;
	}

	/**
	 * @return the urlMapping
	 */
	public String getUrlMapping() {
		return urlMapping;
	}

	/**
	 * @param urlMapping
	 *            the urlMapping to set
	 */
	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}

}