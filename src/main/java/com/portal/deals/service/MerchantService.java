package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Merchant;

public interface MerchantService {

	List<Merchant> listAllMerchants();

	Merchant getMerchantById(Integer id);

	void deleteMerchantById(Integer id);

	void saveMerchant(Merchant merchant);

	void updateMerchant(Merchant merchant);

	void deleteMerchant(Merchant merchant);
}