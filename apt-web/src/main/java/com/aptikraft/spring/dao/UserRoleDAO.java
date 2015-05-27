package com.aptikraft.spring.dao;

import java.util.List;

import com.aptikraft.spring.model.UserRoleDO;

public interface UserRoleDAO {

	public void addUserRole(UserRoleDO userRoleDO);

	public void updateUserRole(UserRoleDO userRoleDO);

	public List<UserRoleDO> listUserRoles();

	public UserRoleDO getUserRoleById(int id);

	public void removeUserRole(int id);

	public List<UserRoleDO> findByUserName(String username);

}
