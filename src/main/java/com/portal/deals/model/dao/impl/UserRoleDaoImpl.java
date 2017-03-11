package com.portal.deals.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.UserRole;
import com.portal.deals.model.dao.UserRoleDao;

/**
 * This class provides implementation to <code>UserRoleDao</code> interface. It
 * will access the Database and it will perform CRUD operation on UserRole
 * entity
 * 
 * @author Gaurav Jain
 *
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<Integer, UserRole> implements UserRoleDao {

	@Override
	public UserRole findById(int id) {
		return getByKey(id);
	}

	@Override
	public UserRole findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserRole) crit.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserRole> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<UserRole>) crit.list();
	}

}
