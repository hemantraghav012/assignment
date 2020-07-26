/**
 * 
 */
package com.digilytics.javaAssignment.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digilytics.javaAssignment.dao.UsersDao;
import com.digilytics.javaAssignment.service.UsersService;
import com.digilytics.javaAssignment.vo.UserVo;

/**
 * @author hemant
 *
 */
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao userdao;

	@Override
	public List<UserVo> registerUsersByCSV_File(List<UserVo> userVo) {

		List<UserVo> userVoList = new ArrayList<>();

		try {
			// save row wise user data with userrole
			for (UserVo userVoTemp : userVo) {
				List<UserVo> userMess = userdao.save(userVoTemp.getEmailId(), userVoTemp.getUserName(),
						userVoTemp.getPassword(), userVoTemp.getRoleName());
				userVoList.addAll(userMess);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return userVoList;
	}

}
