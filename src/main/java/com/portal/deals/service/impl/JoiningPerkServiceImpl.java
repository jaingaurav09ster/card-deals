package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.JoiningPerk;
import com.portal.deals.model.dao.JoiningPerkDAO;
import com.portal.deals.service.JoiningPerkService;

@Service("JoiningPerkService")
@Transactional
public class JoiningPerkServiceImpl implements JoiningPerkService {

	@Autowired
	private JoiningPerkDAO joiningPerkDAO;

	@Override
	public List<JoiningPerk> listAllJoiningPerks() {
		return joiningPerkDAO.listAllJoiningPerks();
	}

	@Override
	public JoiningPerk getJoiningPerkById(Integer id) {
		return joiningPerkDAO.getJoiningPerkById(id);
	}

	@Override
	public void deleteJoiningPerkById(Integer id) {
		joiningPerkDAO.deleteJoiningPerkById(id);
	}

	@Override
	public void saveJoiningPerk(JoiningPerk joiningPerk) {
		joiningPerkDAO.saveJoiningPerk(joiningPerk);
	}

	@Override
	public void updateJoiningPerk(JoiningPerk joiningPerk) {
		JoiningPerk entity = joiningPerkDAO.getJoiningPerkById(joiningPerk.getId());
		if (entity != null) {
			entity.setDescription(joiningPerk.getDescription());
			entity.setTitle(joiningPerk.getTitle());
			entity.setRank(joiningPerk.getRank());
			entity.setCategories(joiningPerk.getCategories());
		}
	}

	@Override
	public void deleteJoiningPerk(JoiningPerk joiningPerk) {
		joiningPerkDAO.deleteJoiningPerk(joiningPerk);
	}

	@Override
	public List<JoiningPerk> getJoiningPerksByCardId(Integer cardId) {
		return joiningPerkDAO.getJoiningPerksByCardId(cardId);
	}
}