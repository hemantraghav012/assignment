/**
 * 
 */
package com.digilytics.javaAssignment.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digilytics.javaAssignment.dao.RoleDao;
import com.digilytics.javaAssignment.service.RoleService;

/**
 * @author hemant
 *
 */

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Integer save(String name) {
		// TODO Auto-generated method stub
		return roleDao.saveRole(name);
	}

}
