package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Rating;
import com.portal.deals.model.dao.RatingDAO;

/**
 * This class provides implementation to <code>RatingDAO</code> interface. It
 * will access the Database and it will perform CRUD operations on FEATURE
 * table, this class will be accessible through RatingService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("RatingDAO")
public class RatingDAOImpl extends AbstractDao<Integer, Rating> implements RatingDAO {

	@Override
	public void saveRating(Rating Rating) {
		this.persist(Rating);
	}

	@Override
	public void updateRating(Rating Rating) {
		this.update(Rating);
	}

	@Override
	public void deleteRating(Rating Rating) {
		this.delete(Rating);
	}

	@Override
	public List<Rating> listAllRatings() {
		List<Rating> list = this.loadAll();
		return (List<Rating>) list;
	}

	@Override
	public Rating getRatingById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteRatingById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Rating> getRatingsByCardId(Integer cardId) {
		List<Rating> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Rating>) list;

	}
}