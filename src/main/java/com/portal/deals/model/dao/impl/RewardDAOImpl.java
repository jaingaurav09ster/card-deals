package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Reward;
import com.portal.deals.model.dao.RewardDAO;

/**
 * This class provides implementation to <code>RewardDAO</code> interface. It will
 * access the Database and it will perform CRUD operations on Reward table, this
 * class will be accessible through RewardService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("RewardDAO")
public class RewardDAOImpl extends AbstractDao<Integer, Reward> implements RewardDAO {

	@Override
	public void saveReward(Reward Reward) {
		this.persist(Reward);
	}

	@Override
	public void updateReward(Reward Reward) {
		this.update(Reward);
	}

	@Override
	public void deleteReward(Reward Reward) {
		this.delete(Reward);
	}

	@Override
	public List<Reward> listAllRewards() {
		List<Reward> list = this.loadAll();
		return (List<Reward>) list;
	}

	@Override
	public Reward getRewardById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteRewardById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<Reward> getRewardsByCardId(Integer cardId) {
		List<Reward> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<Reward>) list;

	}
}