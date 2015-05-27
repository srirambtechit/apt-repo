package com.aptikraft.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.dal.utils.QuestionProvider;
import com.aptikraft.spring.dao.QuestionDAO;
import com.aptikraft.spring.service.QuestionService;
import com.aptikraft.spring.view.bean.QuestionBO;

@Service
public class QuestionServiceImpl implements QuestionService {

	private QuestionDAO questionDAO;

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	@Override
	@Transactional
	public void addQuestion(QuestionBO questionBO) {
		this.questionDAO.addQuestion(QuestionProvider.getQuestionFromBOToDO(questionBO));
	}

	@Override
	@Transactional
	public void updateQuestion(QuestionBO questionBO) {
		this.questionDAO.updateQuestion(QuestionProvider.getQuestionFromBOToDO(questionBO));
	}

	@Override
	@Transactional
	public List<QuestionBO> listQuestions() {
		return QuestionProvider.getQuestionsFromDOToBO(this.questionDAO.listQuestions());
	}

	@Override
	@Transactional
	public QuestionBO getQuestionById(int id) {
		return QuestionProvider.getQuestionFromDOToBO(this.questionDAO.getQuestionById(id));
	}

	@Override
	@Transactional
	public void removeQuestion(int id) {
		this.questionDAO.removeQuestion(id);
	}

}
