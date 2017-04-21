package com.portal.deals.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Category;
import com.portal.deals.model.dao.CategoryDAO;

/**
 * This class provides implementation to <code>CategoryDAO</code> interface. It
 * will access the Database and it will perform CRUD operations on CATEGORY
 * table, this class will be accessible through CategoryService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("CategoryDAO")
public class CategoryDAOImpl extends AbstractDao<Integer, Category> implements CategoryDAO {

	@Override
	public void saveCategory(Category Category) {
		this.persist(Category);
	}

	@Override
	public void updateCategory(Category Category) {
		this.update(Category);
	}

	@Override
	public void deleteCategory(Category Category) {
		this.delete(Category);
	}

	@Override
	public List<Category> listAllCategories() {
		List<Category> list = this.loadAll();
		return (List<Category>) list;
	}

	@Override
	public Category getCategoryById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Category> listAllRootCategories() {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.isNull("category.id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return getEntitiesByCriteria(criteria);
	}
}