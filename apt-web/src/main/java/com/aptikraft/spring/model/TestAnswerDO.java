package com.aptikraft.spring.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
 * The persistent class for the test_answer database table.
 * 
 */
@Entity
@Table(name = "test_answer")
@NamedQuery(name = "TestAnswerDO.findAll", query = "SELECT t FROM TestAnswerDO t")
public class TestAnswerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = true, length = 100)
    private String answer;

    @Column(nullable = true, length = 10)
    private float weightage;

    // bi-directional many-to-one association to Question
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionDO questionDO;

    // bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDO userDO;

    public TestAnswerDO() {
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

    public float getWeightage() {
	return weightage;
    }

    public void setWeightage(float weightage) {
	this.weightage = weightage;
    }

    public QuestionDO getQuestionDO() {
	return questionDO;
    }

    public void setQuestionDO(QuestionDO questionDO) {
	this.questionDO = questionDO;
    }

    public UserDO getUserDO() {
	return userDO;
    }

    public void setUserDO(UserDO userDO) {
	this.userDO = userDO;
    }

}