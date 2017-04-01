package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Rating;

public interface RatingService {

	List<Rating> listAllRatings();

	Rating getRatingById(Integer id);

	void deleteRatingById(Integer id);

	void saveRating(Rating rating);

	void updateRating(Rating rating);

	void deleteRating(Rating rating);

	List<Rating> getRatingsByCardId(Integer cardId);
}