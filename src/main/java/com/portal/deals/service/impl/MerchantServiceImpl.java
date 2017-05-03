package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Merchant;
import com.portal.deals.model.dao.MerchantDAO;
import com.portal.deals.service.MerchantService;

@Service("MerchantService")
@Transactional
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantDAO merchantDAO;

	@Override
	public List<Merchant> listAllMerchants() {
		return merchantDAO.listAllMerchants();
	}

	@Override
	public Merchant getMerchantById(Integer id) {
		return merchantDAO.getMerchantById(id);
	}

	@Override
	public void deleteMerchantById(Integer id) {
		merchantDAO.deleteMerchantById(id);
	}

	@Override
	public void saveMerchant(Merchant merchant) {
		merchantDAO.saveMerchant(merchant);
	}

	@Override
	public void updateMerchant(Merchant merchant) {
		Merchant entity = merchantDAO.getMerchantById(merchant.getId());
		if (entity != null) {
			entity.setDescription(merchant.getDescription());
			entity.setName(merchant.getName());
			entity.setImage(merchant.getImage());
			entity.setImagePath(merchant.getImagePath());
		}
	}

	@Override
	public void deleteMerchant(Merchant merchant) {
		merchantDAO.deleteMerchant(merchant);
	}
}