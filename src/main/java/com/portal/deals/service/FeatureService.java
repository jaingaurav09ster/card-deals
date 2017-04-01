package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Feature;

public interface FeatureService {

	List<Feature> listAllFeatures();

	Feature getFeatureById(Integer id);

	void deleteFeatureById(Integer id);

	void saveFeature(Feature feature);

	void updateFeature(Feature feature);

	void deleteFeature(Feature feature);

	List<Feature> getFeaturesByCardId(Integer cardId);
}