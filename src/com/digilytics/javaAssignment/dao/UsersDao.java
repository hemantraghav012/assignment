/**
 * 
 */
package com.digilytics.javaAssignment.dao;

import java.util.List;

import com.digilytics.javaAssignment.vo.UserVo;

/**
 * @author hemant
 *
 */
public interface UsersDao {

	List<UserVo> save(String emailId, String userName, String password, String[] roleName);

}
