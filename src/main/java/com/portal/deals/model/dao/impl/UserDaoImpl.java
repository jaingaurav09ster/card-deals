package com.portal.deals.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.portal.deals.model.User;
import com.portal.deals.model.dao.UserDao;

/**
 * This class provides implementation to <code>UserDao</code> interface. It will
 * access the Database and it will perform CRUD operation on User entity
 * 
 * @author Gaurav Jain
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserRoles());
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserRoles());
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	@Override
	public void save(User user) {
		persist(user);
	}

	@Override
	public void deleteByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}
}
