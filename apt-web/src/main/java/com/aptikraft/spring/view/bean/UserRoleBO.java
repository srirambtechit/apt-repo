package com.aptikraft.spring.view.bean;

import java.io.Serializable;

/**
 * The bean class for the user database table.
 * 
 */
public class UserRoleBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userRoleId;

	private String username;

	private String role;

	public UserRoleBO() {
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}