package com.aptikraft.spring.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aptikraft.spring.dao.ExamDAO;
import com.aptikraft.spring.model.ExamDO;

public class ExamDAOImpl implements ExamDAO {

	private static final Logger logger = LoggerFactory.getLogger(ExamDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addExam(ExamDO examDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(examDO);
		logger.info("ExamDO saved successfully, ExamDO Details=" + examDO);
	}

	@Override
	public void updateExam(ExamDO examDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(examDO);
		logger.info("ExamDO updated successfully, ExamDO Details=" + examDO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamDO> listExams() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExamDO> examDOs = session.createQuery("from ExamDO").list();
		for (ExamDO examDO : examDOs) {
			logger.info("ExamDO List::" + examDO);
		}
		return examDOs;
	}

	@Override
	public ExamDO getExamById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ExamDO examDO = (ExamDO) session.load(ExamDO.class, new Integer(id));
		logger.info("ExamDO loaded successfully, ExamDO details=" + examDO);
		return examDO;
	}

	@Override
	public void removeExam(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ExamDO examDO = (ExamDO) session.load(ExamDO.class, new Integer(id));
		if (null != examDO) {
			session.delete(examDO);
		}
		logger.info("ExamDO deleted successfully, ExamDO details=" + examDO);
	}

}
