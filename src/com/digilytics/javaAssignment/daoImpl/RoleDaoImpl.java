/**
 * 
 */
package com.digilytics.javaAssignment.daoImpl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.digilytics.javaAssignment.common.HibernateUtil;
import com.digilytics.javaAssignment.dao.RoleDao;
import com.digilytics.javaAssignment.pojo.Role;

/**
 * @author hemant
 *
 */
@Repository
public class RoleDaoImpl implements  RoleDao{

	
	
	@Override
	public Integer saveRole(String name) {

		Session session = null;
		Transaction transaction = null;
		Role role = null;
		Integer resultCode = null;
		try {

			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
			if (role == null) {
				role = new Role();
				role.setName(name);
				session.save(role);
				resultCode = 201;
			} else {
				resultCode = 403;
			}

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
		} finally {
			session.close();
		}

		return resultCode;
	}

}
