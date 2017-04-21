package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Content;
import com.portal.deals.model.dao.ContentDAO;
import com.portal.deals.service.ContentService;

@Service("ContentService")
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDAO contentDAO;

	@Override
	public List<Content> listAllContents() {
		return contentDAO.listAllContents();
	}

	@Override
	public Content getContentById(Integer id) {
		return contentDAO.getContentById(id);
	}

	@Override
	public void deleteContentById(Integer id) {
		contentDAO.deleteContentById(id);
	}

	@Override
	public void saveContent(Content content) {
		contentDAO.saveContent(content);
	}

	@Override
	public void updateContent(Content content) {
		Content entity = contentDAO.getContentById(content.getId());
		if (entity != null) {
			entity.setPageContent(content.getPageContent());
			entity.setPageTags(content.getPageTags());
			entity.setPageTitle(content.getPageTitle());
			entity.setUrlMapping(content.getUrlMapping());
		}
	}

	@Override
	public void deleteContent(Content content) {
		contentDAO.deleteContent(content);
	}

	@Override
	public Content getContentByUrlMapping(String mapping) {
		List<Content> contentList = contentDAO.getContentByUrlMapping(mapping);
		if (contentList != null && contentList.size() > 0) {
			return contentList.get(0);
		}
		return null;
	}
}