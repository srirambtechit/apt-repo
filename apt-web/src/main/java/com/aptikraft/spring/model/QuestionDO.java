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
 * The persistent class for the question database table.
 * 
 */
@Entity
@Table(name = "question")
@NamedQuery(name = "QuestionDO.findAll", query = "SELECT q FROM QuestionDO q")
public class QuestionDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 100)
	private String answer;

	@Column(name = "choice_a", length = 400)
	private String choiceA;

	@Column(name = "choice_b", length = 400)
	private String choiceB;

	@Column(name = "choice_c", length = 400)
	private String choiceC;

	@Column(name = "choice_d", length = 400)
	private String choiceD;

	@Column(name = "choice_e", length = 400)
	private String choiceE;

	@Column(nullable = false, length = 2000)
	private String question;

	// bi-directional many-to-one association to TestAnswer
	@OneToMany(mappedBy = "questionDO", cascade = { CascadeType.ALL })
	private List<TestAnswerDO> testAnswerDOs;

	public QuestionDO() {
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

	public List<TestAnswerDO> getTestAnswerDOs() {
		return testAnswerDOs;
	}

	public void setTestAnswerDOs(List<TestAnswerDO> testAnswerDOs) {
		this.testAnswerDOs = testAnswerDOs;
	}

	public TestAnswerDO addTestAnswer(TestAnswerDO testAnswer) {
		getTestAnswerDOs().add(testAnswer);
		testAnswer.setQuestionDO(this);

		return testAnswer;
	}

	public TestAnswerDO removeTestAnswer(TestAnswerDO testAnswer) {
		getTestAnswerDOs().remove(testAnswer);
		testAnswer.setQuestionDO(null);

		return testAnswer;
	}

}