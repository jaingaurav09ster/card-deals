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
 * The Class GenericDAOImpl.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T, ID extends Serializable> {

	/** The entity class. */
	private Class<T> entityClass;

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new generic dao impl.
	 */
	public GenericDAOImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Gets the current session.
	 * 
	 * @return the current session
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<T> loadAll() {
		return getCurrentSession().createQuery("from " + entityClass.getName()).list();
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(ID id) {
		T entity = get(id);
		delete(entity);
	}

	public void save(T domain) {
		getCurrentSession().saveOrUpdate(domain);
	}

	public void update(T domain) {
		getCurrentSession().merge(domain);
	}

	public T get(ID id) {
		return (T) getCurrentSession().get(entityClass, id);
	}

	public T load(ID id) {
		return (T) getCurrentSession().load(entityClass, id);
	}

	public List<T> findAllByProperty(String propName, Object propValue) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(propName, propValue));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByProperties(Map<String, Object> propMap) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		for (Map.Entry<String, Object> entry : propMap.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByPropertyValues(String propName, Collection<Object> propValues) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.in(propName, propValues));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByRange(String propName, Object start, Object end) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.ge(propName, start));
		criteria.add(Restrictions.le(propName, end));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByChildProperty(String childEntityName, String propName, Object value) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.createAlias(childEntityName, "child");
		criteria.add(Restrictions.eq("child." + propName, value));
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByChildProperties(String childEntityName, Map<String, Object> propMap) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.createAlias(childEntityName, "child");
		for (Map.Entry<String, Object> propEntry : propMap.entrySet()) {
			criteria.add(Restrictions.eq("child." + propEntry.getKey(), propEntry.getValue()));
		}
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByParentChildProperties(String childEntityName, Map<String, Object> parentPropMap,
			Map<String, Object> childPropMap) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
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

}
