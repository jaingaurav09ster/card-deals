package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.JoiningPerk;
import com.portal.deals.model.dao.JoiningPerkDAO;

/**
 * This class provides implementation to <code>JoiningPerkDAO</code> interface.
 * It will access the Database and it will perform CRUD operations on FEATURE
 * table, this class will be accessible through JoiningPerkService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("JoiningPerkDAO")
public class JoiningPerkDAOImpl extends AbstractDao<Integer, JoiningPerk> implements JoiningPerkDAO {

	@Override
	public void saveJoiningPerk(JoiningPerk JoiningPerk) {
		this.persist(JoiningPerk);
	}

	@Override
	public void updateJoiningPerk(JoiningPerk JoiningPerk) {
		this.update(JoiningPerk);
	}

	@Override
	public void deleteJoiningPerk(JoiningPerk JoiningPerk) {
		this.delete(JoiningPerk);
	}

	@Override
	public List<JoiningPerk> listAllJoiningPerks() {
		List<JoiningPerk> list = this.loadAll();
		return (List<JoiningPerk>) list;
	}

	@Override
	public JoiningPerk getJoiningPerkById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteJoiningPerkById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public List<JoiningPerk> getJoiningPerksByCardId(Integer cardId) {
		List<JoiningPerk> list = this.findAllByChildProperty("card", "id", cardId);
		return (List<JoiningPerk>) list;

	}
}