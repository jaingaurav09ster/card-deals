package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.JoiningPerk;

public interface JoiningPerkService {

	List<JoiningPerk> listAllJoiningPerks();

	JoiningPerk getJoiningPerkById(Integer id);

	void deleteJoiningPerkById(Integer id);

	void saveJoiningPerk(JoiningPerk joiningPerk);

	void updateJoiningPerk(JoiningPerk joiningPerk);

	void deleteJoiningPerk(JoiningPerk joiningPerk);

	List<JoiningPerk> getJoiningPerksByCardId(Integer cardId);
}