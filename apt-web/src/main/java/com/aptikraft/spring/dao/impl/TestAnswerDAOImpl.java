package com.aptikraft.spring.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aptikraft.spring.dao.TestAnswerDAO;
import com.aptikraft.spring.model.TestAnswerDO;

public class TestAnswerDAOImpl implements TestAnswerDAO {

    private static final Logger logger = LoggerFactory.getLogger(TestAnswerDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
	this.sessionFactory = sf;
    }

    @Override
    public void addTestAnswer(TestAnswerDO testAnswerDO) {
	Session session = this.sessionFactory.getCurrentSession();
	session.persist(testAnswerDO);
	logger.info("TestAnswerDO saved successfully, TestAnswerDO Details=" + testAnswerDO);
    }

    @Override
    public void addBlukData(List<TestAnswerDO> testAnswerDOs) {

	StatelessSession session = this.sessionFactory.openStatelessSession();

	Transaction transaction = session.beginTransaction();

	for (int index = 0; index < testAnswerDOs.size();) {
	    TestAnswerDO testAnswerDO = testAnswerDOs.get(index);
	    session.insert(testAnswerDO);
	    index++;
	    logger.info("TestAnswerDO saved successfully, TestAnswerDO Details=" + testAnswerDO);
	}
	transaction.commit();
	session.close();
    }

    @Override
    public void updateTestAnswer(TestAnswerDO testAnswerDO) {
	Session session = this.sessionFactory.getCurrentSession();
	session.update(testAnswerDO);
	logger.info("TestAnswerDO updated successfully, TestAnswerDO DetestAnswerDOils=" + testAnswerDO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestAnswerDO> listTestAnswers() {
	Session session = this.sessionFactory.getCurrentSession();
	List<TestAnswerDO> testAnswerDOs = session.createQuery("from TestAnswerDO").list();
	for (TestAnswerDO testAnswerDO : testAnswerDOs) {
	    logger.info("TestAnswerDO List::" + testAnswerDO);
	}
	return testAnswerDOs;
    }

    @Override
    public TestAnswerDO getTestAnswerById(int id) {
	Session session = this.sessionFactory.getCurrentSession();
	TestAnswerDO testAnswerDO = (TestAnswerDO) session.load(TestAnswerDO.class, new Integer(id));
	logger.info("TestAnswerDO loaded successfully, TestAnswerDO detestAnswerDOils=" + testAnswerDO);
	return testAnswerDO;
    }

    @Override
    public void removeTestAnswer(int id) {
	Session session = this.sessionFactory.getCurrentSession();
	TestAnswerDO testAnswerDO = (TestAnswerDO) session.load(TestAnswerDO.class, new Integer(id));
	if (null != testAnswerDO) {
	    session.delete(testAnswerDO);
	}
	logger.info("TestAnswerDO deleted successfully, TestAnswerDO detestAnswerDOils=" + testAnswerDO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestAnswerDO> fetchTestAnswerByUserId(int userId) {
	Session session = this.sessionFactory.getCurrentSession();
	List<TestAnswerDO> testAnswerDOs = session.createQuery("from TestAnswerDO where userDO.id = '" + userId + "'").list();
	logger.info("TestAnswerDO List::" + testAnswerDOs);
	return testAnswerDOs;
    }

}
