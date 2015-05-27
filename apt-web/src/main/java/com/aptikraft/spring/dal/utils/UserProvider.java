package com.aptikraft.spring.dal.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.aptikraft.spring.model.UserDO;
import com.aptikraft.spring.view.bean.UserBO;

public class UserProvider {

	private UserProvider() {
	}

	public static UserDO getUserFromBOToDO(UserBO userBO) {
		if (userBO == null)
			return null;
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userBO, userDO);
		userDO.setEnabled(true); //by default, user is enabled
		return userDO;
	}

	public static UserBO getUserFromDOToBO(UserDO userDO) {
		if (userDO == null)
			return null;
		UserBO userBO = new UserBO();
		BeanUtils.copyProperties(userDO, userBO);
		return userBO;

	}

	public static List<UserBO> getUsersFromDOToBO(List<UserDO> userDOs) {
		if (userDOs == null)
			return null;
		List<UserBO> userBOs = new ArrayList<>();
		for (UserDO userDO : userDOs) {
			userBOs.add(getUserFromDOToBO(userDO));
		}
		return userBOs;
	}

	public static List<UserDO> getUsersFromBOToDO(List<UserBO> userBOs) {
		if (userBOs == null)
			return null;
		List<UserDO> userDOs = new ArrayList<>();
		for (UserBO userBO : userBOs) {
			userDOs.add(getUserFromBOToDO(userBO));
		}
		return userDOs;
	}

}
