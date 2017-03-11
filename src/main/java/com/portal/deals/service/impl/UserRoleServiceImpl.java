package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.UserRole;
import com.portal.deals.model.dao.UserRoleDao;
import com.portal.deals.service.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao dao;

	@Override
	public UserRole findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserRole findByType(String type) {
		return dao.findByType(type);
	}

	@Override
	public List<UserRole> findAll() {
		return dao.findAll();
	}
}
