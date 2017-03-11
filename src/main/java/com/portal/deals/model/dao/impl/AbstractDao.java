package com.portal.deals.model.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the abstract class that provides generic method implementation for
 * database operations
 * 
 * @author Gaurav Jain
 *
 * @param <PK>
 *            The entity id type
 * @param <T>
 *            The Entity Class
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDao<PK extends Serializable, T> {

	/** The entity class that has to be worked upon */
	private final Class<T> persistentClass;

	/** HIBERNATE session factory */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * The Constructor, this sets the entity class
	 */
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	/**
	 * This method gets the HIBERNATE session
	 * 
	 * @return session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * This method gets the entity by id
	 * 
	 * @param entity
	 *            primary key
	 * @return entity
	 */
	protected T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	/**
	 * This method persists the entity
	 * 
	 * @param entity
	 *            object to be persisted
	 */
	protected void persist(T entity) {
		getSession().persist(entity);
	}

	/**
	 * This method save or updated the entity
	 * 
	 * @param entity
	 *            the entity object
	 */
	protected void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * This method updates the entity
	 * 
	 * @param entity
	 *            the entity object
	 */
	protected void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * This method deletes the entity object
	 * 
	 * @param entity
	 *            the entity object
	 */
	protected void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * This method get all the rows for the entity from the database
	 * 
	 * @return list of entity object
	 */
	protected List<T> loadAll() {
		return getSession().createQuery("from " + persistentClass.getName()).list();
	}

	/**
	 * This method deletes the entity based on key passed
	 * 
	 * @param id
	 *            the primary key
	 */
	protected void deleteById(PK id) {
		T entity = get(id);
		delete(entity);
	}

	/**
	 * This method get the entity object by id
	 * 
	 * @param id
	 * @return
	 */
	protected T get(PK id) {
		return (T) getSession().get(persistentClass, id);
	}

	/**
	 * This method loads the entity object
	 * 
	 * @param id
	 *            primary key
	 * @return entity object
	 */
	protected T load(PK id) {
		return (T) getSession().load(persistentClass, id);
	}

	/**
	 * This method get the entity list based on entity's property value
	 * 
	 * @param propName
	 *            property name
	 * @param propValue
	 *            property value
	 * @return Entity object list
	 */
	protected List<T> findAllByProperty(String propName, Object propValue) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propName, propValue));
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on multiple entity's property value
	 * 
	 * @param propMap
	 *            key value pair for property name and property value
	 * @return Entity object list
	 */
	protected List<T> findAllByProperties(Map<String, Object> propMap) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		for (Map.Entry<String, Object> entry : propMap.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on entity's one property with
	 * multiple values
	 * 
	 * @param propName
	 *            property name
	 * @param propValues
	 *            property value list
	 * @return Entity object list
	 */
	protected List<T> findAllByPropertyValues(String propName, Collection<Object> propValues) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.in(propName, propValues));
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on entity's property value's range
	 * 
	 * @param propName
	 *            property name
	 * @param start
	 *            start property value
	 * @param end
	 *            end property value
	 * @return Entity object list
	 */
	protected List<T> findAllByRange(String propName, Object start, Object end) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.ge(propName, start));
		criteria.add(Restrictions.le(propName, end));
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on entity's child's property value
	 * 
	 * @param childEntityName
	 *            child entity name
	 * @param propName
	 *            property name
	 * @param propValue
	 *            property value
	 * @return Entity object list
	 */
	protected List<T> findAllByChildProperty(String childEntityName, String propName, Object value) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.createAlias(childEntityName, "child");
		criteria.add(Restrictions.eq("child." + propName, value));
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on entity's child's property values
	 * 
	 * @param childEntityName
	 *            child entity name
	 * @param propName
	 *            property name
	 * @param propMap
	 *            property key value map
	 * @return Entity object list
	 */
	protected List<T> findAllByChildProperties(String childEntityName, Map<String, Object> propMap) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.createAlias(childEntityName, "child");
		for (Map.Entry<String, Object> propEntry : propMap.entrySet()) {
			criteria.add(Restrictions.eq("child." + propEntry.getKey(), propEntry.getValue()));
		}
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * This method get the entity list based on entity's properties values and
	 * child's properties values
	 * 
	 * @param childEntityName
	 *            child entity name
	 * @param parentPropMap
	 *            property name and value map
	 * @param childPropMap
	 *            child property name and value map
	 * @return Entity object list
	 */
	protected List<T> findAllByParentChildProperties(String childEntityName, Map<String, Object> parentPropMap,
			Map<String, Object> childPropMap) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.createAlias(childEntityName, "child");
		for (Map.Entry<String, Object> propEntry : childPropMap.entrySet()) {
			criteria.add(Restrictions.eq("child." + propEntry.getKey(), propEntry.getValue()));
		}
		for (Map.Entry<String, Object> propEntry : parentPropMap.entrySet()) {
			criteria.add(Restrictions.eq(propEntry.getKey(), propEntry.getValue()));
		}
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	/**
	 * Gets the entities by criteria.
	 * 
	 * @param criteria
	 *            the criteria
	 * @return the entities by criteria
	 */
	protected List<T> getEntitiesByCriteria(Criteria criteria) {
		return criteria.list();
	}

	/**
	 * This method gets the HIBERNATE Criteria
	 * 
	 * @return The Criteria
	 */
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
