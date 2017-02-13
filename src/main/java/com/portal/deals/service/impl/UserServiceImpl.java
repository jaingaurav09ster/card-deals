package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.User;
import com.portal.deals.model.dao.UserDao;
import com.portal.deals.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByEmail(String email) {
		User user = dao.findByEmail(email);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setMobile(user.getMobile());
		}
	}

	public void updateUserByAdmin(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setEmail(user.getEmail());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setMobile(user.getMobile());
			entity.setLastName(user.getLastName());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void activateDeactivateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setEnabled(user.isEnabled());
		}
	}

	public void deleteUserByEmail(String email) {
		dao.deleteByEmail(email);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserUnique(Integer id, String email) {
		User user = findByEmail(email);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}
