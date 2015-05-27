package com.aptikraft.spring.dao;

import java.util.List;

import com.aptikraft.spring.model.UserDO;

public interface UserDAO {

	public void addUser(UserDO userDO);

	public void updateUser(UserDO userDO);

	public List<UserDO> listUsers();

	public UserDO getUserById(int id);

	public void removeUser(int id);

	public List<UserDO> fetchUser(UserDO userDO);

	public UserDO findByUserName(String username);

}
