package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Reward;

/**
 * This interface will provide the methods to manipulate REWARD entity in the
 * database
 * 
 * @author Gaurav Jain
 *
 */
public interface RewardDAO {

	/**
	 * This method will return list of all the REWARD entity from the database
	 * 
	 * @return List of REWARD entity
	 */
	List<Reward> listAllRewards();

	/**
	 * This method will return list of all the REWARD entity for a CARD from the
	 * database
	 * 
	 * @return List of REWARD entity
	 */
	List<Reward> getRewardsByCardId(Integer cardId);

	/**
	 * This method will return the Reward based on the Reward id passed.
	 * 
	 * @param id
	 *            reward id
	 * @return REWARD entity
	 */
	Reward getRewardById(Integer id);

	/**
	 * This method will save the REWARD entity to the database
	 * 
	 * @param reward
	 */
	void saveReward(Reward reward);

	/**
	 * This method will update the REWARD entity in the database
	 * 
	 * @param reward
	 */
	void updateReward(Reward reward);

	/**
	 * This method will delete the reward passed as parameter
	 * 
	 * @param reward
	 *            REWARD entity object to be deleted
	 */
	void deleteReward(Reward reward);

	/**
	 * This method will delete the reward w.r.t the reward id passed as
	 * parameter
	 * 
	 * @param id
	 *            reward id
	 */
	void deleteRewardById(Integer id);
}