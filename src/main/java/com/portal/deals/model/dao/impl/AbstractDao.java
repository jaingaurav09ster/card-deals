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

@SuppressWarnings("unchecked")
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public List<T> loadAll() {
		return getSession().createQuery("from " + persistentClass.getName()).list();
	}

	public void deleteById(PK id) {
		T entity = get(id);
		delete(entity);
	}

	public void save(T domain) {
		getSession().saveOrUpdate(domain);
	}

	public T get(PK id) {
		return (T) getSession().get(persistentClass, id);
	}

	public T load(PK id) {
		return (T) getSession().load(persistentClass, id);
	}

	public List<T> findAllByProperty(String propName, Object propValue) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propName, propValue));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByProperties(Map<String, Object> propMap) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		for (Map.Entry<String, Object> entry : propMap.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByPropertyValues(String propName, Collection<Object> propValues) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.in(propName, propValues));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByRange(String propName, Object start, Object end) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.ge(propName, start));
		criteria.add(Restrictions.le(propName, end));
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByChildProperty(String childEntityName, String propName, Object value) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.createAlias(childEntityName, "child");
		criteria.add(Restrictions.eq("child." + propName, value));
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByChildProperties(String childEntityName, Map<String, Object> propMap) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.createAlias(childEntityName, "child");
		for (Map.Entry<String, Object> propEntry : propMap.entrySet()) {
			criteria.add(Restrictions.eq("child." + propEntry.getKey(), propEntry.getValue()));
		}
		criteria.setFetchMode(childEntityName, FetchMode.JOIN);
		return getEntitiesByCriteria(criteria);
	}

	public List<T> findAllByParentChildProperties(String childEntityName, Map<String, Object> parentPropMap,
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

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
