package com.aptikraft.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.dal.utils.UserProvider;
import com.aptikraft.spring.dao.UserDAO;
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.view.bean.UserBO;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void addUser(UserBO userBO) {
		this.userDAO.addUser(UserProvider.getUserFromBOToDO(userBO));
	}

	@Override
	@Transactional
	public void updateUser(UserBO userBO) {
		this.userDAO.updateUser(UserProvider.getUserFromBOToDO(userBO));
	}

	@Override
	@Transactional
	public List<UserBO> listUsers() {
		return UserProvider.getUsersFromDOToBO(this.userDAO.listUsers());
	}

	@Override
	@Transactional
	public UserBO getUserById(int id) {
		return UserProvider.getUserFromDOToBO(this.userDAO.getUserById(id));
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

	@Override
	@Transactional
	public UserBO findByUserName(String username) {
		return UserProvider.getUserFromDOToBO(this.userDAO.findByUserName(username));
	}

}
