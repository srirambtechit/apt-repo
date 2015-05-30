package com.aptikraft.spring.ui.bean;

import java.util.HashMap;
import java.util.Map;

public class Question {

    private Integer id;

    private String question;

    private String answer;

    private Map<String, String> choiceMap;

    public Question() {
	this.choiceMap = new HashMap<>();
    }

    public Question(Integer id, String question, String answer, Map<String, String> choiceMap) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
	this.choiceMap = choiceMap;
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

    public Map<String, String> getChoiceMap() {
	return choiceMap;
    }

    public void setChoiceMap(Map<String, String> choiceList) {
	this.choiceMap = choiceList;
    }

    @Override
    public String toString() {
	return "Question [id=" + id + ", question=" + question + ", answer=" + answer + ", choiceMap=" + choiceMap + "]";
    }

}
