package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Reward;
import com.portal.deals.model.dao.RewardDAO;
import com.portal.deals.service.RewardService;

@Service("RewardService")
@Transactional
public class RewardServiceImpl implements RewardService {

	@Autowired
	private RewardDAO rewardDAO;

	@Override
	public List<Reward> listAllRewards() {
		return rewardDAO.listAllRewards();
	}

	@Override
	public Reward getRewardById(Integer id) {
		return rewardDAO.getRewardById(id);
	}

	@Override
	public void deleteRewardById(Integer id) {
		rewardDAO.deleteRewardById(id);
	}

	@Override
	public void saveReward(Reward reward) {
		rewardDAO.saveReward(reward);
	}

	@Override
	public void updateReward(Reward reward) {
		Reward entity = rewardDAO.getRewardById(reward.getId());
		if (entity != null) {
			entity.setDescription(reward.getDescription());
			entity.setTitle(reward.getTitle());
			entity.setCategories(reward.getCategories());
		}
	}

	@Override
	public void deleteReward(Reward reward) {
		rewardDAO.deleteReward(reward);
	}

	@Override
	public List<Reward> getRewardsByCardId(Integer cardId) {
		return rewardDAO.getRewardsByCardId(cardId);
	}
}