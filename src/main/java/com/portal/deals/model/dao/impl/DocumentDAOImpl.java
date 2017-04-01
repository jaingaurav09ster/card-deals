package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Document;
import com.portal.deals.model.dao.DocumentDAO;

/**
 * This class provides implementation to <code>DocumentDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on Document table, this
 * class will be accessible through DocumentService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("DocumentDAO")
public class DocumentDAOImpl extends AbstractDao<Integer, Document> implements DocumentDAO {

	@Override
	public void saveDocument(Document Document) {
		this.persist(Document);
	}

	@Override
	public void updateDocument(Document Document) {
		this.update(Document);
	}

	@Override
	public void deleteDocument(Document Document) {
		this.delete(Document);
	}

	@Override
	public List<Document> listAllDocuments() {
		List<Document> list = this.loadAll();
		return (List<Document>) list;
	}

	@Override
	public Document getDocumentById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteDocumentById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Document> getDocumentsByCardId(Integer cardId) {
		List<Document> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Document>) list;

	}
}