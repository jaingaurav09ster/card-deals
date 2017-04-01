package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Fees;
import com.portal.deals.model.dao.FeesDAO;
import com.portal.deals.service.FeesService;

@Service("FeesService")
@Transactional
public class FeesServiceImpl implements FeesService {

	@Autowired
	private FeesDAO feesDAO;

	@Override
	public List<Fees> listAllFees() {
		return feesDAO.listAllFees();
	}

	@Override
	public Fees getFeesById(Integer id) {
		return feesDAO.getFeesById(id);
	}

	@Override
	public void deleteFeesById(Integer id) {
		feesDAO.deleteFeesById(id);
	}

	@Override
	public void saveFees(Fees fees) {
		feesDAO.saveFees(fees);
	}

	@Override
	public void updateFees(Fees fees) {
		Fees entity = feesDAO.getFeesById(fees.getId());
		if (entity != null) {
			entity.setApr(fees.getApr());
			entity.setFirstYear(fees.getFirstYear());
			entity.setOnwards(fees.getOnwards());
		}
	}

	@Override
	public void deleteFees(Fees fees) {
		feesDAO.deleteFees(fees);
	}

	@Override
	public List<Fees> getFeesByCardId(Integer cardId) {
		return feesDAO.getFeesByCardId(cardId);
	}
}