package com.aptikraft.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	testAnswerDO.setQuestionDO(questionDO);
	testAnswerDO.setUserDO(userDO);
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

    @Override
    @Transactional
    public List<TestAnswerBO> fetchTestAnswerByUserId(int userId) {
	return TestAnswerProvider.getTestAnswersFromDOToBO(this.testAnswerDAO.fetchTestAnswerByUserId(userId));
    }

    @Override
    @Transactional
    public void addBulkTestAnswer(List<TestAnswerBO> testAnswerBOs) {

	List<TestAnswerDO> testAnswerDOs = new ArrayList<>();

	if (testAnswerBOs != null && !testAnswerBOs.isEmpty()) {

	    int userId = 0;
	    if (testAnswerBOs.get(0) != null && testAnswerBOs.get(0).getUser() != null) {
		userId = testAnswerBOs.get(0).getUser().getId();
	    }
	    UserDAO userDAO = ApplicationContextProvider.getBean(UserDAO.class);
	    UserDO userDO = userDAO.getUserById(userId);

	    List<Integer> questionIds = new ArrayList<>();

	    for (TestAnswerBO testAnswerBO : testAnswerBOs) {
		if (testAnswerBO != null && testAnswerBO.getQuestion() != null) {
		    questionIds.add(testAnswerBO.getQuestion().getId());
		}
	    }

	    QuestionDAO questionDAO = ApplicationContextProvider.getBean(QuestionDAO.class);
	    List<QuestionDO> questionsByIds = questionDAO.listQuestionsByIds(questionIds);
	    Map<Integer, QuestionDO> qMap = new HashMap<>();

	    for (QuestionDO questionDO : questionsByIds) {
		qMap.put(questionDO.getId(), questionDO);
	    }
	    for (TestAnswerBO testAnswerBO : testAnswerBOs) {
		TestAnswerDO testAnswerDO = TestAnswerProvider.getTestAnswerFromBOToDO(testAnswerBO);
		testAnswerDO.setQuestionDO(qMap.get(testAnswerBO.getQuestion().getId()));
		testAnswerDO.setUserDO(userDO);
		testAnswerDOs.add(testAnswerDO);
	    }
	}
	this.testAnswerDAO.addBlukData(testAnswerDOs);
    }

}
