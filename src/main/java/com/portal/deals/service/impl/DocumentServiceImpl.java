package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Document;
import com.portal.deals.model.dao.DocumentDAO;
import com.portal.deals.service.DocumentService;

@Service("DocumentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDAO documentDAO;

	@Override
	public List<Document> listAllDocuments() {
		return documentDAO.listAllDocuments();
	}

	@Override
	public Document getDocumentById(Integer id) {
		return documentDAO.getDocumentById(id);
	}

	@Override
	public void deleteDocumentById(Integer id) {
		documentDAO.deleteDocumentById(id);
	}

	@Override
	public void saveDocument(Document document) {
		documentDAO.saveDocument(document);
	}

	@Override
	public void updateDocument(Document document) {
		Document entity = documentDAO.getDocumentById(document.getId());
		if (entity != null) {
			entity.setDescription(document.getDescription());
			entity.setName(document.getName());
		}
	}

	@Override
	public void deleteDocument(Document document) {
		documentDAO.deleteDocument(document);
	}

	@Override
	public List<Document> getDocumentsByCardId(Integer cardId) {
		return documentDAO.getDocumentsByCardId(cardId);
	}
}