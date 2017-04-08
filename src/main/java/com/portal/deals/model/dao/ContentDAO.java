package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Content;

/**
 * This interface will provide the methods to manipulate CONTENT entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface ContentDAO {

	/**
	 * This method will return list of all the CONTENT entity from the database
	 * 
	 * @return List of CONTENT entity
	 */
	List<Content> listAllContents();

	/**
	 * This method will return the Content based on the Content id passed.
	 * 
	 * @param id
	 *            content id
	 * @return CONTENT Category entity
	 */
	Content getContentById(Integer id);

	/**
	 * This method will save the CONTENT entity to the database
	 * 
	 * @param content
	 */
	void saveContent(Content content);

	/**
	 * This method will update the CONTENT entity in the database
	 * 
	 * @param content
	 */
	void updateContent(Content content);

	/**
	 * This method will delete the content passed as parameter
	 * 
	 * @param content
	 *            CONTENT entity object to be deleted
	 */
	void deleteContent(Content content);

	/**
	 * This method will delete the content w.r.t the content id passed as parameter
	 * 
	 * @param id
	 *            content id
	 */
	void deleteContentById(Integer id);
	
	/**
	 * This method will return the Content based on the Content id passed.
	 * 
	 * @param id
	 *            content id
	 * @return CONTENT Category entity
	 */
	List<Content> getContentByUrlMapping(String mapping);
}