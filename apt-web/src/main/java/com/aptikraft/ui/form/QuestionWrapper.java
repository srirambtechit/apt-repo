package com.aptikraft.ui.form;

import java.util.ArrayList;
import java.util.List;

public class QuestionWrapper {

	private List<Question> questionList;

	public QuestionWrapper() {
		this.questionList = new ArrayList<>();
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public void add(Question question) {
		this.questionList.add(question);
	}

}
