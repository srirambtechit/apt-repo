package com.aptikraft.spring.view.bean;

import java.io.Serializable;
import java.util.List;

/**
 * The bean class for the user database table.
 * 
 */
public class UserBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String email;

	private String mobileNumber;

	private String name;

	private String password;

	private String cpassword;

	private String rollNumber;

	private String username;

	private boolean enabled;

	private boolean activeLogin;

	private List<TestAnswerBO> testAnswers;

	private List<UserRoleBO> userRoles;

	public UserBO() {
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

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
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
		return enabled;
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

	public List<TestAnswerBO> getTestAnswers() {
		return this.testAnswers;
	}

	public void setTestAnswers(List<TestAnswerBO> testAnswers) {
		this.testAnswers = testAnswers;
	}

	public List<UserRoleBO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleBO> userRoles) {
		this.userRoles = userRoles;
	}

	public TestAnswerBO addTestAnswer(TestAnswerBO testAnswer) {
		getTestAnswers().add(testAnswer);
		testAnswer.setUser(this);
		return testAnswer;
	}

	public TestAnswerBO removeTestAnswer(TestAnswerBO testAnswer) {
		getTestAnswers().remove(testAnswer);
		testAnswer.setUser(null);
		return testAnswer;
	}

}