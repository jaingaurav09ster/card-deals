package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Reward;

public interface RewardService {

	List<Reward> listAllRewards();

	Reward getRewardById(Integer id);

	void deleteRewardById(Integer id);

	void saveReward(Reward reward);

	void updateReward(Reward reward);

	void deleteReward(Reward reward);

	List<Reward> getRewardsByCardId(Integer cardId);
}