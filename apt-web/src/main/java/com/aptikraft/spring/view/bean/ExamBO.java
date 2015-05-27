package com.aptikraft.spring.view.bean;

import java.io.Serializable;

/**
 * The bean class for the exam database table.
 * 
 */
public class ExamBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String code;

	private String description;

	private int durationMinute;

	private int noOfQuestion;

	public ExamBO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDurationMinute() {
		return this.durationMinute;
	}

	public void setDurationMinute(int durationMinute) {
		this.durationMinute = durationMinute;
	}

	public int getNoOfQuestion() {
		return this.noOfQuestion;
	}

	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

}