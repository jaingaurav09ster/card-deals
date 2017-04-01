package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Fees;

public interface FeesService {

	List<Fees> listAllFees();

	Fees getFeesById(Integer id);

	void deleteFeesById(Integer id);

	void saveFees(Fees fees);

	void updateFees(Fees fees);

	void deleteFees(Fees fees);
	
	List<Fees> getFeesByCardId(Integer cardId);
}