package com.aptikraft.spring.service;

import java.util.List;

import com.aptikraft.spring.view.bean.QuestionBO;

public interface QuestionService {

	public void addQuestion(QuestionBO questionBO);

	public void updateQuestion(QuestionBO questionBO);

	public List<QuestionBO> listQuestions();

	public QuestionBO getQuestionById(int id);

	public void removeQuestion(int id);

}
