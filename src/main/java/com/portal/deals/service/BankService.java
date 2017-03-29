package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Bank;

public interface BankService {

	List<Bank> listAllBanks();

	Bank getBankById(Integer id);

	void deleteBankById(Integer id);

	void saveBank(Bank bank);

	void updateBank(Bank bank);

	void deleteBank(Bank bank);
}