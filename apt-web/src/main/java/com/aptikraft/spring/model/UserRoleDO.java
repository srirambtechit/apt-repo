package com.aptikraft.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name = "user_role")
@NamedQuery(name = "UserRoleDO.findAll", query = "SELECT u FROM UserRoleDO u")
public class UserRoleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	private int userRoleId;

	@Column(name = "ROLE", nullable = false, length = 45)
	private String role;

	// bi-directional many-to-one association to UserDO
	@ManyToOne
	@JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
	private UserDO userDO;

	public UserRoleDO() {
	}

	public UserRoleDO(UserDO userDO, String role) {
		this.userDO = userDO;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
