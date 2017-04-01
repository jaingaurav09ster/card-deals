package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Document;

public interface DocumentService {

	List<Document> listAllDocuments();

	Document getDocumentById(Integer id);

	void deleteDocumentById(Integer id);

	void saveDocument(Document document);

	void updateDocument(Document document);

	void deleteDocument(Document document);
	
	List<Document> getDocumentsByCardId(Integer cardId);
}