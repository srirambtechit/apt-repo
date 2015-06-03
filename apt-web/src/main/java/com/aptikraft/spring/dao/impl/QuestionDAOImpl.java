package com.aptikraft.spring.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aptikraft.spring.dao.QuestionDAO;
import com.aptikraft.spring.model.QuestionDO;

public class QuestionDAOImpl implements QuestionDAO {

    private static final Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
	this.sessionFactory = sf;
    }

    @Override
    public void addQuestion(QuestionDO questionDO) {
	Session session = this.sessionFactory.getCurrentSession();
	session.persist(questionDO);
	logger.info("QuestionDO saved successfully, QuestionDO Details=" + questionDO);
    }

    @Override
    public void updateQuestion(QuestionDO questionDO) {
	Session session = this.sessionFactory.getCurrentSession();
	session.update(questionDO);
	logger.info("QuestionDO updated successfully, QuestionDO Details=" + questionDO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDO> listQuestions() {
	Session session = this.sessionFactory.getCurrentSession();
	List<QuestionDO> questionDOs = session.createQuery("from QuestionDO").list();
	for (QuestionDO questionDO : questionDOs) {
	    logger.info("QuestionDO List::" + questionDO);
	}
	return questionDOs;
    }

    @Override
    public QuestionDO getQuestionById(int id) {
	Session session = this.sessionFactory.getCurrentSession();
	QuestionDO questionDO = (QuestionDO) session.load(QuestionDO.class, new Integer(id));
	logger.info("QuestionDO loaded successfully, QuestionDO details=" + questionDO);
	return questionDO;
    }

    @Override
    public void removeQuestion(int id) {
	Session session = this.sessionFactory.getCurrentSession();
	QuestionDO questionDO = (QuestionDO) session.load(QuestionDO.class, new Integer(id));
	if (null != questionDO) {
	    session.delete(questionDO);
	}
	logger.info("QuestionDO deleted successfully, QuestionDO details=" + questionDO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDO> listQuestionsByIds(List<Integer> ids) {
	Session session = this.sessionFactory.getCurrentSession();
	Criteria criteria = session.createCriteria(QuestionDO.class);
	List<QuestionDO> questionDOs = criteria.add(Restrictions.in("id", ids)).list();
	logger.info("QuestionDO List::" + questionDOs);
	return questionDOs;
    }

}
