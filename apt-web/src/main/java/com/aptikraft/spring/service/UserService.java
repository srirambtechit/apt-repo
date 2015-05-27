package com.aptikraft.spring.service;

import java.util.List;

import com.aptikraft.spring.view.bean.UserBO;

public interface UserService {

	public void addUser(UserBO userBO);

	public void updateUser(UserBO userBO);

	public List<UserBO> listUsers();

	public UserBO getUserById(int id);

	public void removeUser(int id);
	
	public UserBO findByUserName(String username);

}
