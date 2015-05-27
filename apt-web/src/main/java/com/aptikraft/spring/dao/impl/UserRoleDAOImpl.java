package com.aptikraft.spring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aptikraft.spring.dao.UserRoleDAO;
import com.aptikraft.spring.model.UserDO;
import com.aptikraft.spring.model.UserRoleDO;

public class UserRoleDAOImpl implements UserRoleDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUserRole(UserRoleDO userRoleDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userRoleDO);
		logger.info("UserRoleDO saved successfully, UserRoleDO Details=" + userRoleDO);
	}

	@Override
	public void updateUserRole(UserRoleDO userRoleDO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(userRoleDO);
		logger.info("UserRoleDO updated successfully, UserRoleDO Details=" + userRoleDO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleDO> listUserRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserRoleDO> userRoleDOs = session.createQuery("from UserRoleDO").list();
		for (UserRoleDO userRoleDO : userRoleDOs) {
			logger.info("UserRoleDO List::" + userRoleDO);
		}
		return userRoleDOs;
	}

	@Override
	public UserRoleDO getUserRoleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRoleDO userRoleDO = (UserRoleDO) session.load(UserRoleDO.class, new Integer(id));
		logger.info("UserRoleDO loaded successfully, UserRoleDO details=" + userRoleDO);
		return userRoleDO;
	}

	@Override
	public void removeUserRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserRoleDO userRoleDO = (UserRoleDO) session.load(UserDO.class, new Integer(id));
		if (null != userRoleDO) {
			session.delete(userRoleDO);
		}
		logger.info("UserRoleDO deleted successfully, UserRoleDO details=" + userRoleDO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleDO> findByUserName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserRoleDO where username=?");
		List<UserRoleDO> userRoleDOs = query.setParameter(0, username).list();
		if (userRoleDOs.size() > 0) {
			return userRoleDOs;
		} else {
			return null;
		}
	}

}
