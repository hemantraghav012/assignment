/**
 * 
 */
package com.digilytics.javaAssignment.service;

import java.util.List;

import com.digilytics.javaAssignment.vo.UserVo;

/**
 * @author hemant
 *
 */
public interface UsersService {

	
	List<UserVo> registerUsersByCSV_File(List<UserVo> userVotemp);


}
