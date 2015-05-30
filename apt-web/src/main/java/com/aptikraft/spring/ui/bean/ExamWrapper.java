package com.aptikraft.spring.ui.bean;

import java.util.ArrayList;
import java.util.List;

public class ExamWrapper {

    private int noOfQuestion;

    private int duration;

    private List<Question> questionList;

    public ExamWrapper() {
	this.questionList = new ArrayList<>();
    }

    public int getNoOfQuestion() {
	return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
	this.noOfQuestion = noOfQuestion;
    }

    public int getDuration() {
	return duration;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public List<Question> getQuestionList() {
	return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
	this.questionList = questionList;
    }

    public void addQuestion(Question question) {
	this.questionList.add(question);
    }

}
