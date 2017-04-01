package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Rating;

/**
 * This interface will provide the methods to manipulate RATING entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface RatingDAO {

	/**
	 * This method will return list of all the RATING entity from the database
	 * 
	 * @return List of RATING entity
	 */
	List<Rating> listAllRatings();

	/**
	 * This method will return list of all the RATING entity for a CARD from the
	 * database
	 * 
	 * @return List of RATING entity
	 */
	List<Rating> getRatingsByCardId(Integer cardId);

	/**
	 * This method will return the Rating based on the Rating id passed.
	 * 
	 * @param id
	 *            rating id
	 * @return RATING entity
	 */
	Rating getRatingById(Integer id);

	/**
	 * This method will save the RATING entity to the database
	 * 
	 * @param rating
	 */
	void saveRating(Rating rating);

	/**
	 * This method will update the RATING entity in the database
	 * 
	 * @param rating
	 */
	void updateRating(Rating rating);

	/**
	 * This method will delete the rating passed as parameter
	 * 
	 * @param rating
	 *            RATING entity object to be deleted
	 */
	void deleteRating(Rating rating);

	/**
	 * This method will delete the rating w.r.t the rating id passed as
	 * parameter
	 * 
	 * @param id
	 *            rating id
	 */
	void deleteRatingById(Integer id);
}