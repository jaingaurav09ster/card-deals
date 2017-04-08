package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Content;

public interface ContentService {

	List<Content> listAllContents();

	Content getContentById(Integer id);
	
	Content getContentByUrlMapping(String mapping);

	void deleteContentById(Integer id);

	void saveContent(Content content);

	void updateContent(Content content);

	void deleteContent(Content content);
}