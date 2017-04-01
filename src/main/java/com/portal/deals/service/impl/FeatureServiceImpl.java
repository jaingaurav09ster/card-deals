package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Feature;
import com.portal.deals.model.dao.FeatureDAO;
import com.portal.deals.service.FeatureService;

@Service("FeatureService")
@Transactional
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private FeatureDAO featureDAO;

	@Override
	public List<Feature> listAllFeatures() {
		return featureDAO.listAllFeatures();
	}

	@Override
	public Feature getFeatureById(Integer id) {
		return featureDAO.getFeatureById(id);
	}

	@Override
	public void deleteFeatureById(Integer id) {
		featureDAO.deleteFeatureById(id);
	}

	@Override
	public void saveFeature(Feature feature) {
		featureDAO.saveFeature(feature);
	}

	@Override
	public void updateFeature(Feature feature) {
		Feature entity = featureDAO.getFeatureById(feature.getId());
		if (entity != null) {
			entity.setDescription(feature.getDescription());
			entity.setTitle(feature.getTitle());
			entity.setRank(feature.getRank());
			entity.setCategories(feature.getCategories());
		}
	}

	@Override
	public void deleteFeature(Feature feature) {
		featureDAO.deleteFeature(feature);
	}

	@Override
	public List<Feature> getFeaturesByCardId(Integer cardId) {
		return featureDAO.getFeaturesByCardId(cardId);
	}
}