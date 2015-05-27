package com.aptikraft.common.utils;

public enum RoleType {
	USER_ROLE(1, "ROLE_USER"), ADMIN_ROLE(2, "ROLE_ADMIN");

	private int id;
	private String roleName;

	private RoleType(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

}
