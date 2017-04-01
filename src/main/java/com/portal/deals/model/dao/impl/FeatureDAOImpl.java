package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Feature;
import com.portal.deals.model.dao.FeatureDAO;

/**
 * This class provides implementation to <code>FeatureDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on FEATURE table, this
 * class will be accessible through FeatureService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("FeatureDAO")
public class FeatureDAOImpl extends AbstractDao<Integer, Feature> implements FeatureDAO {

	@Override
	public void saveFeature(Feature Feature) {
		this.persist(Feature);
	}

	@Override
	public void updateFeature(Feature Feature) {
		this.update(Feature);
	}

	@Override
	public void deleteFeature(Feature Feature) {
		this.delete(Feature);
	}

	@Override
	public List<Feature> listAllFeatures() {
		List<Feature> list = this.loadAll();
		return (List<Feature>) list;
	}

	@Override
	public Feature getFeatureById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteFeatureById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Feature> getFeaturesByCardId(Integer cardId) {
		List<Feature> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Feature>) list;

	}
}