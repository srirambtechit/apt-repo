package com.aptikraft.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aptikraft.common.utils.PasswordEncrypter;
import com.aptikraft.common.utils.RoleType;
import com.aptikraft.common.utils.ViewNameConstants;
import com.aptikraft.spring.service.UserRoleService;
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.view.bean.UserBO;
import com.aptikraft.spring.view.bean.UserRoleBO;

@Controller
public class UserController {

	private UserService userService;
	private UserRoleService userRoleService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	@Qualifier(value = "userRoleService")
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String goToLoginPage(Model model) {
		model.addAttribute("user", new UserBO());
		return ViewNameConstants.LOGIN;
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String goToRegisterPage(Model model) {
		model.addAttribute("user", new UserBO());
		return ViewNameConstants.USER;
	}

	// Persist new user details in database
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") UserBO userBO) {
		ModelAndView model = new ModelAndView();
		if (registeredUser(userBO)) {
			model.addObject("error", "User already exists");
			model.setViewName(ViewNameConstants.USER);
		} else {
			if (userBO.getId() == 0) {
				prepareUserDetails(userBO);

				// new User, add it
				getUserService().addUser(userBO);

				// new UserRole, add it
				for (UserRoleBO userRoleBO : userBO.getUserRoles()) {
					getUserRoleService().addUserRole(userRoleBO);
				}
			}
			model.setViewName(ViewNameConstants.LOGIN);
		}
		return model;
	}

	private boolean registeredUser(UserBO userBO) {
		UserBO userData = getUserService().findByUserName(userBO.getUsername());
		if (userData == null) {
			return false;
		}
		return true;
	}

	private void prepareUserDetails(UserBO userBO) {
		// Hash password by bcrypt algorithm for Spring security
		userBO.setPassword(PasswordEncrypter.encryptPassword(userBO.getPassword()));

		// by default, user should be enabled for Spring-Security
		userBO.setEnabled(true);

		List<UserRoleBO> userRoles = new ArrayList<>();
		UserRoleBO userRoleBO = new UserRoleBO();
		userRoleBO.setUsername(userBO.getUsername());
		userRoleBO.setRole(RoleType.USER_ROLE.getRoleName());
		userRoles.add(userRoleBO);
		userBO.setUserRoles(userRoles);
	}

}
