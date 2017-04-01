package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Document;

/**
 * This interface will provide the methods to manipulate DOCUMENT entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface DocumentDAO {

	/**
	 * This method will return list of all the DOCUMENT entity from the database
	 * 
	 * @return List of DOCUMENT entity
	 */
	List<Document> listAllDocuments();

	/**
	 * This method will return list of all the DOCUMENT entity for a CARD from
	 * the database
	 * 
	 * @return List of DOCUMENT entity
	 */
	List<Document> getDocumentsByCardId(Integer cardId);

	/**
	 * This method will return the Document based on the Document id passed.
	 * 
	 * @param id
	 *            document id
	 * @return DOCUMENT entity
	 */
	Document getDocumentById(Integer id);

	/**
	 * This method will save the DOCUMENT entity to the database
	 * 
	 * @param document
	 */
	void saveDocument(Document document);

	/**
	 * This method will update the DOCUMENT entity in the database
	 * 
	 * @param document
	 */
	void updateDocument(Document document);

	/**
	 * This method will delete the document passed as parameter
	 * 
	 * @param document
	 *            DOCUMENT entity object to be deleted
	 */
	void deleteDocument(Document document);

	/**
	 * This method will delete the document w.r.t the document id passed as
	 * parameter
	 * 
	 * @param id
	 *            document id
	 */
	void deleteDocumentById(Integer id);
}