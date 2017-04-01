package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Feature;

/**
 * This interface will provide the methods to manipulate FEATURE entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface FeatureDAO {

	/**
	 * This method will return list of all the FEATURE entity from the database
	 * 
	 * @return List of FEATURE entity
	 */
	List<Feature> listAllFeatures();

	/**
	 * This method will return list of all the FEATURE entity for a CARD from
	 * the database
	 * 
	 * @return List of FEATURE entity
	 */
	List<Feature> getFeaturesByCardId(Integer cardId);

	/**
	 * This method will return the Feature based on the Feature id passed.
	 * 
	 * @param id
	 *            feature id
	 * @return FEATURE entity
	 */
	Feature getFeatureById(Integer id);

	/**
	 * This method will save the FEATURE entity to the database
	 * 
	 * @param feature
	 */
	void saveFeature(Feature feature);

	/**
	 * This method will update the FEATURE entity in the database
	 * 
	 * @param feature
	 */
	void updateFeature(Feature feature);

	/**
	 * This method will delete the feature passed as parameter
	 * 
	 * @param feature
	 *            FEATURE entity object to be deleted
	 */
	void deleteFeature(Feature feature);

	/**
	 * This method will delete the feature w.r.t the feature id passed as
	 * parameter
	 * 
	 * @param id
	 *            feature id
	 */
	void deleteFeatureById(Integer id);
}