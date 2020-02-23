package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Role;
import com.cyzy.dao.RoleDao;
import com.cyzy.util.DaoFactory;

public class RoleServiceImpl implements RoleService {

	@Override
	public int addRole(Role role) {
		
		return 0;
	}

	@Override
	public int updateRole(Role role) {
		
		return 0;
	}

	@Override
	public Role getRoleById(int userId) {
		
		return null;
	}

	@Override
	public int deleteUser(int userId) {
		
		return 0;
	}
	
	@Override
	public List<Role> queryRole(Role role) {
		
		RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
		return roleDao.queryRole(role);
	}

}
