package com.aptikraft.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.aptikraft.spring.appcontext.ApplicationContextProvider;
import com.aptikraft.spring.service.UserService;
import com.aptikraft.spring.view.bean.UserBO;

public class CurrentUser {

    public static String getCurrentUsername() {
	User currentUser = getCurrentUser();
	return currentUser.getUsername();
    }

    private static User getCurrentUser() {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	return (User) auth.getPrincipal();
    }

    public static UserBO getCurrentUserBO() {
	UserService userService = ApplicationContextProvider.getBean(UserService.class);
	return userService.findByUserName(getCurrentUsername());
    }

}
