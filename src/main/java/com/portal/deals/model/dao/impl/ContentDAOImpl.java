package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Content;
import com.portal.deals.model.dao.ContentDAO;

/**
 * This class provides implementation to <code>ContentDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on CONTENT table,
 * this class will be accessible through ContentService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("ContentDAO")
public class ContentDAOImpl extends AbstractDao<Integer, Content> implements ContentDAO {

	@Override
	public void saveContent(Content Content) {
		this.persist(Content);
	}

	@Override
	public void updateContent(Content Content) {
		this.update(Content);
	}

	@Override
	public void deleteContent(Content Content) {
		this.delete(Content);
	}

	@Override
	public List<Content> listAllContents() {
		List<Content> list = this.loadAll();
		return (List<Content>) list;
	}

	@Override
	public Content getContentById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteContentById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Content> getContentByUrlMapping(String mapping) {
		return this.findAllByProperty("urlMapping", mapping);
	}
}