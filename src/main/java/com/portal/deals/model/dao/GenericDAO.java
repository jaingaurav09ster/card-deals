package com.portal.deals.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The Interface GenericDAO.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * Load all.
	 * 
	 * @return the list
	 */
	List<T> loadAll();

	/**
	 * Save.
	 * 
	 * @param domain
	 *            the domain
	 */
	void save(T domain);

	/**
	 * Update.
	 * 
	 * @param domain
	 *            the domain
	 */
	void update(T domain);

	/**
	 * Delete.
	 * 
	 * @param domain
	 *            the domain
	 */
	void delete(T domain);

	/**
	 * Delete by id.
	 * 
	 * @param id
	 *            the id
	 */
	void deleteById(ID id);

	/**
	 * Gets the.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */
	T get(ID id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */
	T load(ID id);

	/**
	 * Find all by property.
	 * 
	 * @param propName
	 *            the prop name
	 * @param propValue
	 *            the prop value
	 * @return the list
	 */
	List<T> findAllByProperty(String propName, Object propValue);

	/**
	 * Find all by properties.
	 * 
	 * @param propMap
	 *            the prop map
	 * @return the list
	 */
	List<T> findAllByProperties(Map<String, Object> propMap);

	/**
	 * Find all by property values.
	 * 
	 * @param propName
	 *            the prop name
	 * @param propValues
	 *            the prop values
	 * @return the list
	 */
	List<T> findAllByPropertyValues(String propName, Collection<Object> propValues);

	/**
	 * Find all by range.
	 * 
	 * @param propName
	 *            the prop name
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the list
	 */
	List<T> findAllByRange(String propName, Object start, Object end);

	/**
	 * Find all by child property.
	 * 
	 * @param childEntityName
	 *            the child entity name
	 * @param propName
	 *            the prop name
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<T> findAllByChildProperty(String childEntityName, String propName, Object value);

	/**
	 * Find all by child properties.
	 * 
	 * @param childEntityName
	 *            the child entity name
	 * @param propMap
	 *            the prop map
	 * @return the list
	 */
	List<T> findAllByChildProperties(String childEntityName, Map<String, Object> propMap);

	/**
	 * Find all by parent child properties.
	 * 
	 * @param childEntityName
	 *            the child entity name
	 * @param parentPropMap
	 *            the parent prop map
	 * @param childPropMap
	 *            the child prop map
	 * @return the list
	 */
	List<T> findAllByParentChildProperties(String childEntityName, Map<String, Object> parentPropMap,
			Map<String, Object> childPropMap);

}
