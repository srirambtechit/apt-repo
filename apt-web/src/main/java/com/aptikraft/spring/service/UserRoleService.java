package com.aptikraft.spring.service;

import java.util.List;

import com.aptikraft.spring.view.bean.UserRoleBO;

public interface UserRoleService {

	public void addUserRole(UserRoleBO userRoleBO);

	public void updateUserRole(UserRoleBO userRoleBO);

	public List<UserRoleBO> listUserRoles();

	public UserRoleBO getUserRoleById(int id);

	public void removeUserRole(int id);

}
