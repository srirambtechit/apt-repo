package com.aptikraft.spring.view.bean;

import java.io.Serializable;
import java.util.List;

/**
 * The bean class for the question database table.
 * 
 */
public class QuestionBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String answer;

	private String choiceA;

	private String choiceB;

	private String choiceC;

	private String choiceD;

	private String choiceE;

	private String question;

	private List<TestAnswerBO> testAnswers;

	public QuestionBO() {
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

	public String getChoiceA() {
		return this.choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return this.choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return this.choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return this.choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public String getChoiceE() {
		return this.choiceE;
	}

	public void setChoiceE(String choiceE) {
		this.choiceE = choiceE;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<TestAnswerBO> getTestAnswers() {
		return this.testAnswers;
	}

	public void setTestAnswers(List<TestAnswerBO> testAnswers) {
		this.testAnswers = testAnswers;
	}

	public TestAnswerBO addTestAnswer(TestAnswerBO testAnswer) {
		getTestAnswers().add(testAnswer);
		testAnswer.setQuestion(this);

		return testAnswer;
	}

	public TestAnswerBO removeTestAnswer(TestAnswerBO testAnswer) {
		getTestAnswers().remove(testAnswer);
		testAnswer.setQuestion(null);

		return testAnswer;
	}

}