package com.aptikraft.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.dal.utils.UserRoleProvider;
import com.aptikraft.spring.dao.UserDAO;
import com.aptikraft.spring.dao.UserRoleDAO;
import com.aptikraft.spring.model.UserDO;
import com.aptikraft.spring.model.UserRoleDO;
import com.aptikraft.spring.service.UserRoleService;
import com.aptikraft.spring.view.bean.UserRoleBO;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDAO;

	private UserDAO userDAO;

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void addUserRole(UserRoleBO userRoleBO) {
		UserRoleDO userRoleDO = UserRoleProvider.getUserRoleFromBOToDO(userRoleBO);
		UserDO userDO = new UserDO();
		userDO.setUsername(userRoleBO.getUsername());
		List<UserDO> userDOs = userDAO.fetchUser(userDO);
		if (userDOs != null && !userDOs.isEmpty()) {
			userRoleDO.setUserDO(userDOs.get(0));
		}
		this.userRoleDAO.addUserRole(userRoleDO);
	}

	@Override
	@Transactional
	public void updateUserRole(UserRoleBO userRoleBO) {
		this.userRoleDAO.updateUserRole(UserRoleProvider.getUserRoleFromBOToDO(userRoleBO));
	}

	@Override
	@Transactional
	public List<UserRoleBO> listUserRoles() {
		return UserRoleProvider.getUserRoleFromDOToBOs(this.userRoleDAO.listUserRoles());
	}

	@Override
	@Transactional
	public UserRoleBO getUserRoleById(int id) {
		return UserRoleProvider.getUserRoleFromDOToBO(this.userRoleDAO.getUserRoleById(id));
	}

	@Override
	@Transactional
	public void removeUserRole(int id) {
		this.userRoleDAO.removeUserRole(id);
	}

}
