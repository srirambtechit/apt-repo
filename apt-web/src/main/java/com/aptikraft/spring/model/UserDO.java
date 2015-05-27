package com.aptikraft.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "UserDO.findAll", query = "SELECT u FROM UserDO u")
public class UserDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(unique = true, nullable = false, length = 15)
	private String username;

	@Column(nullable = false, length = 15)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(name = "mobile_number", length = 10)
	private String mobileNumber;

	@Column(length = 25)
	private String name;

	@Column(name = "roll_number", nullable = false, length = 15)
	private String rollNumber;

	@Column(name = "login_active", nullable = false)
	private boolean activeLogin;

	// bi-directional many-to-one association to TestAnswer
	@OneToMany(mappedBy = "userDO")
	private List<TestAnswerDO> testAnswerDOs;

	// bi-directional many-to-one association to UserRoleDO
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDO")
	private List<UserRoleDO> userRoleDOs;

	public UserDO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRollNumber() {
		return this.rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isActiveLogin() {
		return activeLogin;
	}

	public void setActiveLogin(boolean activeLogin) {
		this.activeLogin = activeLogin;
	}

	public List<TestAnswerDO> getTestAnswerDOs() {
		return testAnswerDOs;
	}

	public void setTestAnswerDOs(List<TestAnswerDO> testAnswerDOs) {
		this.testAnswerDOs = testAnswerDOs;
	}

	public List<UserRoleDO> getUserRoleDOs() {
		return userRoleDOs;
	}

	public void setUserRoleDOs(List<UserRoleDO> userRoleDOs) {
		this.userRoleDOs = userRoleDOs;
	}

	public TestAnswerDO addTestAnswer(TestAnswerDO testAnswer) {
		getTestAnswerDOs().add(testAnswer);
		testAnswer.setUserDO(this);
		return testAnswer;
	}

	public TestAnswerDO removeTestAnswer(TestAnswerDO testAnswer) {
		getTestAnswerDOs().remove(testAnswer);
		testAnswer.setUserDO(null);
		return testAnswer;
	}

}