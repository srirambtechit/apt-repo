package com.aptikraft.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.appcontext.ApplicationContextProvider;
import com.aptikraft.spring.dal.utils.TestAnswerProvider;
import com.aptikraft.spring.dao.QuestionDAO;
import com.aptikraft.spring.dao.TestAnswerDAO;
import com.aptikraft.spring.dao.UserDAO;
import com.aptikraft.spring.model.QuestionDO;
import com.aptikraft.spring.model.TestAnswerDO;
import com.aptikraft.spring.model.UserDO;
import com.aptikraft.spring.service.TestAnswerService;
import com.aptikraft.spring.view.bean.TestAnswerBO;

@Service
public class TestAnswerServiceImpl implements TestAnswerService {

	private TestAnswerDAO testAnswerDAO;

	public void setTestAnswerDAO(TestAnswerDAO testAnswerDAO) {
		this.testAnswerDAO = testAnswerDAO;
	}

	@Override
	@Transactional
	public void addTestAnswer(TestAnswerBO testAnswerBO) {
		TestAnswerDO testAnswerDO = TestAnswerProvider.getTestAnswerFromBOToDO(testAnswerBO);

		UserDAO userDAO = ApplicationContextProvider.getBean(UserDAO.class);
		UserDO userDO = userDAO.getUserById(testAnswerBO.getUser().getId());

		QuestionDAO questionDAO = ApplicationContextProvider.getBean(QuestionDAO.class);
		QuestionDO questionDO = questionDAO.getQuestionById(testAnswerBO.getQuestion().getId());
System.out.println(questionDO.getAnswer());
System.out.println(questionDO.getId());

		testAnswerDO.setQuestionDO(questionDO);
		testAnswerDO.setUserDO(userDO);
System.out.println(userDO.getId());
System.out.println(userDO.getUsername());
		this.testAnswerDAO.addTestAnswer(testAnswerDO);
	}

	@Override
	@Transactional
	public void updateTestAnswer(TestAnswerBO testAnswerBO) {
		this.testAnswerDAO.updateTestAnswer(TestAnswerProvider.getTestAnswerFromBOToDO(testAnswerBO));
	}

	@Override
	@Transactional
	public List<TestAnswerBO> listTestAnswers() {
		return TestAnswerProvider.getTestAnswersFromDOToBO(this.testAnswerDAO.listTestAnswers());
	}

	@Override
	@Transactional
	public TestAnswerBO getTestAnswerById(int id) {
		return TestAnswerProvider.getTestAnswerFromDOToBO(this.testAnswerDAO.getTestAnswerById(id));
	}

	@Override
	@Transactional
	public void removeTestAnswer(int id) {
		this.testAnswerDAO.removeTestAnswer(id);
	}

}
