package com.portal.deals.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The Class AbstractEntity.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/** The last modified date. */
	@UpdateTimestamp
	@Column(name = "LAST_MOD_DT")
	private Timestamp lastModifiedDate;

	/** The last modified date. */
	@CreationTimestamp
	@Column(name = "CREATION_DT")
	private Timestamp creationDate;

	/**
	 * Gets the last modified date.
	 * 
	 * @return the last modified date
	 */
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}
}
