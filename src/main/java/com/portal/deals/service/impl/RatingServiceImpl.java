package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Rating;
import com.portal.deals.model.dao.RatingDAO;
import com.portal.deals.service.RatingService;

@Service("RatingService")
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDAO ratingDAO;

	@Override
	public List<Rating> listAllRatings() {
		return ratingDAO.listAllRatings();
	}

	@Override
	public Rating getRatingById(Integer id) {
		return ratingDAO.getRatingById(id);
	}

	@Override
	public void deleteRatingById(Integer id) {
		ratingDAO.deleteRatingById(id);
	}

	@Override
	public void saveRating(Rating rating) {
		ratingDAO.saveRating(rating);
	}

	@Override
	public void updateRating(Rating rating) {
		Rating entity = ratingDAO.getRatingById(rating.getId());
		if (entity != null) {
			entity.setComment(rating.getComment());
			entity.setRating(rating.getRating());
		}
	}

	@Override
	public void deleteRating(Rating rating) {
		ratingDAO.deleteRating(rating);
	}

	@Override
	public List<Rating> getRatingsByCardId(Integer cardId) {
		return ratingDAO.getRatingsByCardId(cardId);
	}
}