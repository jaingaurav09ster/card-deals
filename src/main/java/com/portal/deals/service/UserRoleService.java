package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.UserRole;

public interface UserRoleService {

	UserRole findById(int id);

	UserRole findByType(String type);

	List<UserRole> findAll();

}
