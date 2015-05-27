package com.aptikraft.ui.form;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private Integer id;

    private String question;

    private String answer;

    private List<String> choiceList;

    public Question() {
	this.choiceList = new ArrayList<>();
    }

    public Question(Integer id, String question, String answer, List<String> choiceList) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
	this.choiceList = choiceList;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public String getAnswer() {
	return answer;
    }

    public void setAnswer(String answer) {
	this.answer = answer;
    }

    public List<String> getChoiceList() {
	return choiceList;
    }

    public void setChoiceList(List<String> choiceList) {
	this.choiceList = choiceList;
    }

}
