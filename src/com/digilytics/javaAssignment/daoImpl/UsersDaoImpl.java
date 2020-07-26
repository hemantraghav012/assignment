/**
 * 
 */
package com.digilytics.javaAssignment.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.digilytics.javaAssignment.common.HibernateUtil;
import com.digilytics.javaAssignment.dao.UsersDao;
import com.digilytics.javaAssignment.pojo.Role;
import com.digilytics.javaAssignment.pojo.UserRole;
import com.digilytics.javaAssignment.pojo.Users;
import com.digilytics.javaAssignment.vo.UserVo;

/**
 * @author hemant
 *
 */
@Repository
public class UsersDaoImpl implements UsersDao {

	@Override
	public List<UserVo> save(String emailId, String userName, String password, String[] roleName) {
		Session session = null;
		Users userTemp = null;
		List<UserVo> userVoList = new ArrayList<>();
		Transaction tx = null;

		try {
			if (StringUtils.isEmpty(emailId) && ArrayUtils.isEmpty(roleName)) {
				return userVoList;
			}

			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			// check user is alredy saved or not
			userTemp = (Users) session.createCriteria(Users.class).add(Restrictions.eq("emailId", emailId))
					.uniqueResult();
			// saved users information
			if (userTemp == null) {
				userTemp = new Users();
				userTemp.setEmailId(emailId);
				userTemp.setName(userName);
				userTemp.setPassword(password);
				session.persist(userTemp);

				// user role are saved
				userVoList = roleAssignToUser(session, userTemp, roleName);
				// if userVoList is empty then commit the transcation
				// if one or two role in valid then whole thrancation is not commited
				// or no saved.
				if (userVoList.isEmpty())
					tx.commit();
			}
			// if user are already exsist
			else {
				String message = "already exsist";
				UserVo userVoMess = addUserVoMessInList(emailId, message, userName, roleName, password);
				userVoList.add(userVoMess);
			}

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			// ex.printStackTrace();
			String message = "Invalid email / Exception";
			UserVo userVoMess = addUserVoMessInList(emailId, message, userName, roleName, password);
			userVoList.add(userVoMess);

		} finally {
			session.close();
		}

		return userVoList;
	}

	/**
	 * 
	 * @param session
	 * @param userTemp
	 * @param roleName
	 * @return List<UserVo> is empty while no error otherwise return list with whole
	 *         data and error message
	 * @throws Exception
	 */
	private List<UserVo> roleAssignToUser(Session session, Users userTemp, String[] roleName) throws Exception {

		Role role = null;
		UserRole userRole = null;
		List<UserVo> userVoList = new ArrayList<>();
		Integer successCount = 0;
		Integer faildCount = 0;
		for (String name : roleName) {
			role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
			// role Name is not invalid
			// then save userRole
			if (role != null) {
				userRole = new UserRole();
				userRole.setRole(role);
				userRole.setUsers(userTemp);
				session.persist(userRole);
				successCount += 1;

			} else {
				// role Name is invalid then save in list with user information
				String message = "Invalid role name";
				String[] roleNameIndex = new String[] { name };
				UserVo userVoMess = new UserVo();
				userVoMess.setEmailId(userTemp.getEmailId());
				userVoMess.setMessage(message);
				userVoMess.setPassword(userTemp.getPassword());
				userVoMess.setUserName(userTemp.getName());
				userVoMess.setRoleName(roleNameIndex);
				userVoMess.setFaildCount(1);
				userVoMess.setSuccessCount(0);
				userVoList.add(userVoMess);
				faildCount++;
			}

		}
		// faildcount is equal to 0. it means no error
		// successCount is set in list
		if (faildCount == 0) {
			UserVo userVoMess = new UserVo();
			userVoMess.setSuccessCount(successCount);
			userVoMess.setFaildCount(0);
			userVoList.add(userVoMess);
		}
		return userVoList;
	}

	/**
	 * 
	 * @param emailId
	 * @param message
	 * @param userName
	 * @param roleName
	 * @param password
	 * @return value set and add in a list
	 */
	private UserVo addUserVoMessInList(String emailId, String message, String userName, String[] roleName,
			String password) {
		String roleNameArrConvertToString = "";
		UserVo userVoMess = new UserVo();
		userVoMess.setEmailId(emailId);
		userVoMess.setMessage(message);
		userVoMess.setPassword(password);
		userVoMess.setUserName(userName);
		roleNameArrConvertToString = getRoleNameToString(roleName);
		userVoMess.setRoleName(new String[] { roleNameArrConvertToString });
		userVoMess.setFaildCount(1);
		userVoMess.setSuccessCount(0);
		return userVoMess;
	}

	/**
	 * 
	 * @param roleName
	 * @return string rolename with # sparated
	 */
	private String getRoleNameToString(String[] roleName) {
		String roleNameStr = "";
		for (String roleNameTemp : roleName) {
			roleNameStr = roleNameStr.concat(roleNameTemp.concat("#"));
		}
		roleNameStr = StringUtils.chop(roleNameStr);

		return roleNameStr;
	}

}
