package com.aptikraft.test.dao;

import java.util.List;

import org.hibernate.Session;

import com.aptikraft.spring.model.UserDO;
import com.aptikraft.test.util.HibernateUtil;

public class TestUserRoleDAO {

	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List<UserDO> obj = (List<UserDO>) session.createQuery("from UserDO").list();
			System.out.println(obj.size());
			// System.out.println(obj.getRole());
			// System.out.println(obj.getUserRoleId());
			// if (obj.getUserDO() != null) {
			// System.out.println(obj.getUserDO().getUsername());
			// }
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				try {
					session.getSessionFactory().close();
				} catch (Exception e2) {
					throw e2;
				}
			}
		}
	}

}
