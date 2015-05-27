package com.aptikraft.spring.dao;

import java.util.List;

import com.aptikraft.spring.model.QuestionDO;

public interface QuestionDAO {

	public void addQuestion(QuestionDO questionDO);

	public void updateQuestion(QuestionDO questionDO);

	public List<QuestionDO> listQuestions();

	public QuestionDO getQuestionById(int id);

	public void removeQuestion(int id);

}
