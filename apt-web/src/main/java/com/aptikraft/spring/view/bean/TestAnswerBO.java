package com.aptikraft.spring.view.bean;

import java.io.Serializable;

/**
 * The bean class for the test_answer database table.
 * 
 */
public class TestAnswerBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String answer;

	private QuestionBO question;

	private UserBO user;

	public TestAnswerBO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public QuestionBO getQuestion() {
		return this.question;
	}

	public void setQuestion(QuestionBO question) {
		this.question = question;
	}

	public UserBO getUser() {
		return this.user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

}