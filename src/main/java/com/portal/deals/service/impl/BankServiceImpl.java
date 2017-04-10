package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Bank;
import com.portal.deals.model.dao.BankDAO;
import com.portal.deals.service.BankService;

@Service("BankService")
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAO bankDAO;

	@Override
	public List<Bank> listAllBanks() {
		return bankDAO.listAllBanks();
	}

	@Override
	public Bank getBankById(Integer id) {
		return bankDAO.getBankById(id);
	}

	@Override
	public void deleteBankById(Integer id) {
		bankDAO.deleteBankById(id);
	}

	@Override
	public void saveBank(Bank bank) {
		bankDAO.saveBank(bank);
	}

	@Override
	public void updateBank(Bank bank) {
		Bank entity = bankDAO.getBankById(bank.getId());
		if (entity != null) {
			entity.setDescription(bank.getDescription());
			entity.setName(bank.getName());
			entity.setSector(bank.getSector());
			entity.setImage(entity.getImage());
			entity.setImagePath(entity.getImagePath());
		}
	}

	@Override
	public void deleteBank(Bank bank) {
		bankDAO.deleteBank(bank);
	}
}