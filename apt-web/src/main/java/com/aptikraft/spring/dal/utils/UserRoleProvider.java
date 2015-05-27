package com.aptikraft.spring.dal.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.aptikraft.spring.model.UserRoleDO;
import com.aptikraft.spring.view.bean.UserRoleBO;

public class UserRoleProvider {

	private UserRoleProvider() {
	}

	public static UserRoleDO getUserRoleFromBOToDO(UserRoleBO userRoleBO) {
		if (userRoleBO == null)
			return null;
		UserRoleDO userRoleDO = new UserRoleDO();
		userRoleDO.setRole(userRoleBO.getRole());
		BeanUtils.copyProperties(userRoleBO, userRoleDO);
		return userRoleDO;
	}

	public static UserRoleBO getUserRoleFromDOToBO(UserRoleDO userRoleDO) {
		if (userRoleDO == null)
			return null;
		UserRoleBO userRoleBO = new UserRoleBO();
		BeanUtils.copyProperties(userRoleDO, userRoleBO);
		return userRoleBO;

	}

	public static List<UserRoleBO> getUserRoleFromDOToBOs(List<UserRoleDO> userRoleDOs) {
		if (userRoleDOs == null)
			return null;
		List<UserRoleBO> userRoleBOs = new ArrayList<>();
		for (UserRoleDO userRoleDO : userRoleDOs) {
			userRoleBOs.add(getUserRoleFromDOToBO(userRoleDO));
		}
		return userRoleBOs;
	}

	public static List<UserRoleDO> getUserRoleFromBOToDOs(List<UserRoleBO> userRoleBOs) {
		if (userRoleBOs == null)
			return null;
		List<UserRoleDO> userRoleDOs = new ArrayList<>();
		for (UserRoleBO userRoleBO : userRoleBOs) {
			userRoleDOs.add(getUserRoleFromBOToDO(userRoleBO));
		}
		return userRoleDOs;
	}

}
