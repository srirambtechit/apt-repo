package com.aptikraft.spring.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aptikraft.common.utils.StringUtils;
import com.aptikraft.spring.dao.UserDAO;
import com.aptikraft.spring.model.UserDO;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(UserDO userDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userDO);
		logger.info("UserDO saved successfully, UserDO Details=" + userDO);
	}

	@Override
	public void updateUser(UserDO userDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(userDO);
		logger.info("UserDO updated successfully, UserDO Details=" + userDO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDO> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserDO> userDOs = session.createQuery("from UserDO").list();
		for (UserDO userDO : userDOs) {
			logger.info("UserDO List::" + userDO);
		}
		return userDOs;
	}

	@Override
	public UserDO getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserDO userDO = (UserDO) session.load(UserDO.class, new Integer(id));
		logger.info("UserDO loaded successfully, UserDO details=" + userDO);
		return userDO;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserDO userDO = (UserDO) session.load(UserDO.class, new Integer(id));
		if (null != userDO) {
			session.delete(userDO);
		}
		logger.info("UserDO deleted successfully, UserDO details=" + userDO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDO> fetchUser(UserDO userDO) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDO.class);
		if (userDO.getId() != 0) {
			criteria.add(Restrictions.eq("id", userDO.getId()));
		}
		if (StringUtils.isNotBlank(userDO.getEmail())) {
			criteria.add(Restrictions.eq("email", userDO.getEmail()));
		}
		if (StringUtils.isNotBlank(userDO.getMobileNumber())) {
			criteria.add(Restrictions.eq("mobileNumber", userDO.getMobileNumber()));
		}
		if (StringUtils.isNotBlank(userDO.getName())) {
			criteria.add(Restrictions.eq("name", userDO.getName()));
		}
		if (StringUtils.isNotBlank(userDO.getPassword())) {
			criteria.add(Restrictions.eq("password", userDO.getPassword()));
		}
		if (StringUtils.isNotBlank(userDO.getRollNumber())) {
			criteria.add(Restrictions.eq("rollNumber", userDO.getRollNumber()));
		}
		if (StringUtils.isNotBlank(userDO.getUsername())) {
			criteria.add(Restrictions.eq("username", userDO.getUsername()));
		}
		List<UserDO> userDOs = criteria.list();
		return userDOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDO findByUserName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserDO where username=?");
		List<UserDO> userDOs = query.setParameter(0, username).list();
		if (userDOs.size() > 0) {
			return userDOs.get(0);
		} else {
			return null;
		}
	}

}
